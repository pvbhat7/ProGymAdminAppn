package com.progym.common.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.progym.api.DateApi;
import com.progym.api.Identity;
import com.progym.api.ProjectContext;
import com.progym.api.ServerApi;
import com.progym.common.constants.ProjectConstants;
import com.progym.common.fcm.PushNotificationRequest;
import com.progym.common.model.CollectionDashboardPVO;
import com.progym.common.model.*;
import com.progym.common.utils.*;
import com.progym.tavros.ServerCom;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class UserDaoImpl implements UserDao {

    String jsonString = "";

    public static TaskExecutor getTaskExecutor() {
        ThreadPoolTaskExecutor t = new ThreadPoolTaskExecutor();
        t.setCorePoolSize(1);
        t.setMaxPoolSize(5);
        t.initialize();
        return t;
    }

    public static final long MILLISECONDS_IN_DAY = (long) (1000 * 60 * 60 * 24);
    public static final String RED = "#FF0000";
    public static final String YELLOW = "#FAF201";
    public static final String GREEN = "#1CFF00";
    public static final String ACTIVITY_TYPE_ADD_NEW_MEMBER = "Add New Client";
    public static final String ACTIVITY_TYPE_ADD_NEW_PACKAGE = "Create New Package";
    public static final String ACTIVITY_TYPE_ASSIGN_PACKAGE_TO_CLIENT = "Assign New Package";
    public static final String ACTIVITY_TYPE_NEW_PAYMENT = "New Payment";
    public static final String ACTIVITY_TYPE_UPDATE_CLIENT_EXISTING_PACKAGE = "Update Package";
    public static final String ACTIVITY_TYPE_DELETE_CLIENT_EXISTING_PACKAGE = "Delete Package";
    public static final String ACTIVITY_TYPE_DELETE_CLIENT_PROFILE = "Delete Client Profile";
    public static final String ACTIVITY_TYPE_UPDATE_MEMBER_PROFILE = "Upadate Client Profilet";


    /*
     * @Autowired DataSource datasource;
     */
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void register() {
        System.out.println("registering...");
    }

    public User validateUser(User user) {
        User u = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        u = (User) session.createCriteria(User.class)
                .add(Restrictions.eq("password", user.getPassword()))
                .add(Restrictions.eq("username", user.getUsername()))
                .uniqueResult();
        session.close();
        return u;
    }

    @Override
    public void updateMemberToDatabase(Client c, User u) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Client c1 = getClientById(c.getId());
        c1.setAddress(c.getAddress());
        c1.setName(c.getName());
        c1.setEmail(c.getEmail());
        c1.setMobile(c.getMobile());
        c1.setAddress(c.getAddress());
        c1.setBloodGroup(c.getBloodGroup());
        c1.setPreviousGym(c.getPreviousGym());
        c1.setDiscontinue("false");
        c1.setWeight(c.getWeight());
        c1.setHeight(c.getHeight());
        session.saveOrUpdate(c1);
        session.getTransaction().commit();
        if (ProjectContext.isOnlineMode())
            ServerCom.sendSingleObjectToServer(ServerApi.UPDATE_CLIENT_API, c1);
        logActivity(session, c, u, ACTIVITY_TYPE_UPDATE_MEMBER_PROFILE, null);
        session.close();
    }


    public List<MemberStatPVO> getMembersBy(String filter, String zone, String enableDisable) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria crit = session.createCriteria(Client.class).add(Restrictions.eq("discontinue", "false")).add(Restrictions.eq("profileActiveFlag", enableDisable));
        Collection<Client> collection = new LinkedHashSet(crit.list());

        // clients list as per zone selection
        List<Client> cList = new ArrayList<Client>();
        for (Client c : collection) {
            if (filter.equals("all"))
                cList.add(c);
            else {
                if (filter.equals("male") && c.getGender().equals("male"))
                    cList.add(c);
                if (filter.equals("female") && c.getGender().equals("female"))
                    cList.add(c);
            }
        }

        // creating display object
        List<MemberStatPVO> membersStatPVOs = new ArrayList<>();
        for (Client c : cList) {
            MemberStatPVO m = new MemberStatPVO();
            m.setId(c.getId());
            m.setName(c.getName());
            m.setGender(c.getGender());
            m.setReferPoints(c.getReferPoints());
            m.setProfileActiveFlag(c.getProfileActiveFlag());
            m.setName(c.getName());
            m.setEmail(c.getEmail());
            m.setMobile(c.getMobile());

            // get recent package record and set days remaining
            Criteria c1 = session.createCriteria(PackageDetails.class);
            c1.add(Restrictions.eq("client.id", c.getId()));
            c1.add(Restrictions.eq("discontinue", "false"));
            c1.addOrder(Order.desc("id"));
            c1.setMaxResults(1);


            PackageDetails pd = ((PackageDetails) c1.uniqueResult());

            if (pd != null) {
                m.setPaymentStatus(pd.getClientPackageStatus().toUpperCase());
                try {
                    m.setPackageDuration(pd.getPackageStartDate() + " - " + pd.getPackageEndDate());
                    m.setPackageName(pd.getPackageName());
                    m.setFeesPaid(String.valueOf(pd.getAmountPaid()));
                    m.setPackageTotalFees(String.valueOf(pd.getPackageFees()));
                    m.setPendingFees(String.valueOf(pd.getPackageFees() - pd.getAmountPaid()));
                    Date firstDate = new SimpleDateFormat("dd/MM/yyyy").parse(pd.getPackageStartDate());
                    Date secondDate = new SimpleDateFormat("dd/MM/yyyy").parse(pd.getPackageEndDate());
                    m.setDaysRemaining(String.valueOf(daysDiff(new Date(), secondDate)));
                    m.setPackagePaymentStatus(pd.getClientPackageStatus());
                } catch (ParseException e) {
                }
            } else {
                m.setDaysRemaining("-");
                m.setPaymentStatus("-");
            }

            // Based on days remaining , set display color
            if (m.getDaysRemaining().equals("-"))
                m.setColor(GREEN);
            else {
                //if(Integer.parseInt(m.getDaysRemaining())<5 && isFeesNotPaid(c))
                if (Integer.parseInt(m.getDaysRemaining()) < 5)
                    m.setColor(RED);
                else if (Integer.parseInt(m.getDaysRemaining()) >= 5 && Integer.parseInt(m.getDaysRemaining()) < 10)
                    m.setColor(YELLOW);
                else
                    m.setColor(GREEN);
            }

            membersStatPVOs.add(m);
        }
        session.close();

        // Filtering display object based on selected color by end user
        List<MemberStatPVO> membersStatPVOByZones = null;
        if (zone.equalsIgnoreCase("none")) {
            membersStatPVOByZones = membersStatPVOs;
        } else if (zone.equalsIgnoreCase("green")) {
            membersStatPVOByZones = getMembersByZones(membersStatPVOs, GREEN);
        } else if (zone.equalsIgnoreCase("red")) {
            membersStatPVOByZones = getMembersByZones(membersStatPVOs, RED);
        } else if (zone.equalsIgnoreCase("yellow")) {
            membersStatPVOByZones = getMembersByZones(membersStatPVOs, YELLOW);
        }

        // pushing no package members at end of list
        return pushingNoPackageMembersToEnd(membersStatPVOByZones);
    }

    private List<MemberStatPVO> pushingNoPackageMembersToEnd(List<MemberStatPVO> membersStatPVOByZones) {
        List<MemberStatPVO> noPackageMembers = new ArrayList<>();
        List<MemberStatPVO> withPackageMembers = new ArrayList<>();
        List<MemberStatPVO> notPaidPackageMembers = new ArrayList<>();
        for (MemberStatPVO member : membersStatPVOByZones) {
            if (member.getDaysRemaining().equals("-"))
                noPackageMembers.add(member);
            else if (Long.valueOf(member.getDaysRemaining()) > 10 && member.getPaymentStatus().equals("NOT PAID")) {
                notPaidPackageMembers.add(member);
            } else
                withPackageMembers.add(member);
        }

        // sorting as per days remaining
        Collections.sort(withPackageMembers);

        withPackageMembers.addAll(noPackageMembers);
        withPackageMembers.addAll(notPaidPackageMembers);
        return withPackageMembers;
    }

    private boolean isFeesNotPaid(Client c) {
        boolean flag = false;
        List<PackageDetails> pdList = getClientPackagesByClient(c);
        if (pdList != null) {
            if (!pdList.get(0).getClientPackageStatus().equalsIgnoreCase("fully-paid"))
                flag = true;
        }
        return flag;
    }

    private PackageDetails getLastPackagePaymentStatus(Client c) {
        List<PackageDetails> pdList = getClientPackagesByClient(c);
        if (pdList.size() == 0)
            return null;
        return (PackageDetails) pdList.get(0);
    }

    public List<MemberStatPVO> getMembersByZones(List<MemberStatPVO> list, String color) {
        List<MemberStatPVO> membersStatPVOByZones = new ArrayList<>();
        for (MemberStatPVO m : list) {
            if (m.getColor().equalsIgnoreCase(color))
                membersStatPVOByZones.add(m);
        }
        return membersStatPVOByZones;
    }

    @Override
    public void addPackageToDatabase(AddPackageObject addPackageObject, User user) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        CPackage pkg = new CPackage(addPackageObject.getFees(), addPackageObject.getPackageName(), addPackageObject.getDays(), addPackageObject.getGender(), "false");
        session.save(pkg);
        session.beginTransaction();
        session.getTransaction().commit();
        logActivity(session, null, user, ACTIVITY_TYPE_ADD_NEW_PACKAGE, null);
        session.close();
    }

    @Override
    public List<CPackage> getPackagesByFilter(String filter) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<CPackage> pkg = new ArrayList<CPackage>();
        Criteria crit = session.createCriteria(CPackage.class);
        if (!filter.equals("all"))
            crit.add(Restrictions.eq("gender", filter));
        crit.add(Restrictions.eq("discontinue", "false"));
        pkg = crit.list();
        session.close();
        return pkg;
    }

    public void addPackageForClientToDatabase(AddClientPackageForm o, User user) {
        Session session = HibernateUtils.getSessionFactory().openSession();

        // get current list & add to existing list - pkgdetails
        //
        session.beginTransaction();
        PackageDetails pd1 = new PackageDetails();
        pd1.setClientPackageStatus("not paid");
        CPackage c = (CPackage) session.load(CPackage.class, Integer.parseInt(o.getCpackageId()));
        pd1.setPackageStartDate(DateApi.getDdMmYyyyDate(o.getStartDate()));
        pd1.setPackageEndDate(DateApi.getDdMmYyyyDate(addDaysToDate(o.getStartDate(), c.getDays())));
        pd1.setcPackageId(Integer.parseInt(o.getCpackageId()));
        pd1.setDiscontinue("false");
        pd1.setAmountPaid(0.0);
        pd1.setPackageName(c.getPackageName());

        // apply discount on fees
        //int discountedFees = (int)(c.getFees()*(Float.parseFloat(o.getDiscountPercentage())/100.0f));
        int finalDiscountedFees = c.getFees().intValue() - Integer.parseInt(o.getDiscountPercentage());
        pd1.setPackageFees(Double.valueOf(finalDiscountedFees));

        Client c1 = getClientById(o.getClientId());
        pd1.setClient(c1);
        session.save(pd1);
        session.getTransaction().commit();
        logActivity(session, c1, user, ACTIVITY_TYPE_ASSIGN_PACKAGE_TO_CLIENT, String.valueOf(pd1.getPackageFees()));
        session.close();
    }

    @Override
    public Client getClientById(int clientId) {
        // build whole client object
        Client c = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        c = (Client) session.createCriteria(Client.class).add(Restrictions.eq("id", clientId)).uniqueResult();
        session.close();
        return c;
    }

    @Override
    public CPackage getPackageById(int id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        CPackage p = null;
        Criteria crit = session.createCriteria(CPackage.class);
        crit.add(Restrictions.eq("id", id));
        crit.add(Restrictions.eq("discontinue", "false"));
        p = (CPackage) crit.uniqueResult();
        session.close();
        return p;
    }

    @Override
    public void createTransaction(PaymentTransaction paymentTransaction, Boolean isAuthorized, User user) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        EmailDataObject eObj = new EmailDataObject();
        session.beginTransaction();
        String isAuth = "NO";
        if (isAuthorized)
            isAuth = "YES";

        PaymentTransaction p = new PaymentTransaction(new SimpleDateFormat("dd/MM/yyyy").format(new Date()), paymentTransaction.getPackageDetailsId(), paymentTransaction.getFeesPaid(), isAuth, "false", paymentTransaction.getPaymentMode());
        PackageDetails pd = (PackageDetails) session.createCriteria(PackageDetails.class).add(Restrictions.eq("id", paymentTransaction.getPackageDetailsId())).uniqueResult();
        pd.setAmountPaid(pd.getAmountPaid() + paymentTransaction.getFeesPaid());
        CPackage c = (CPackage) session.load(CPackage.class, pd.getcPackageId());
        if (pd.getPackageFees() > pd.getAmountPaid())
            pd.setClientPackageStatus("partial-paid");
        else
            pd.setClientPackageStatus("fully-paid");
        pd.setPackagePaymentDate(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        logActivity(session, pd.getClient(), user, ACTIVITY_TYPE_NEW_PAYMENT, String.valueOf(p.getFeesPaid()));
        session.save(p);
        eObj.setClientEmail(pd.getClient().getEmail());
        eObj.setClientName(pd.getClient().getName());
        eObj.setPackageName(pd.getPackageName());
        eObj.setPaymentDate(new SimpleDateFormat("dd/MM/YYYY hh:mm:ss").format(new Date()));
        eObj.setDuration(pd.getPackageStartDate() + " - " + pd.getPackageEndDate());
        eObj.setRemainingAmount(String.valueOf(pd.getPackageFees() - pd.getAmountPaid()));
        eObj.setAmount(String.valueOf(paymentTransaction.getFeesPaid()));

        PushNotificationRequest noti = new PushNotificationRequest();
        noti.setTitle("Payment Received");
        noti.setMessage("Hi " + eObj.getClientName() + "\nWe have received your payment of Rs." + eObj.getAmount()
                + " for package - " + eObj.getPackageName() + "\n" + eObj.getDuration());
        noti.setNotificationType(ProjectConstants.SIMPLE);
        noti.setTargetClass(ProjectConstants.MAIN);
        noti.setMobile(pd.getClient().getMobile());
        noti.setImage("");
        noti.setClientName(eObj.getClientName());

        List<String> tok = new ArrayList<>();
        List<FcmTokenModal> tokens = ServerCom.getAllTokensByMobile(noti.getMobile());
        if (tokens != null && tokens.size() > 0) {
            for (FcmTokenModal t : tokens) {
                tok.add(t.getToken());
            }
            noti.setTokensList(tok);

            ServerCom.sendSameMessageToAllTokens(noti);
        }


        // get send receipt flag
        eObj.setPaymentTransactionId(p.getId());
        if (isModuleEnabled(ProjectConstants.EMAIL_INVOICE_FLAG)) {
            eObj.setIsReceiptSent("TRUE");
            session.save(eObj);
            getTaskExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    // your background task here
                    EmailUtil.sendEmail2(eObj);
                    String mobile = pd.getClient().getMobile();
                    String body = "Hi " + pd.getClient().getName() + "\n ProGym has received your payment of Rs." + paymentTransaction.getFeesPaid() + " for package " + pd.getPackageName();
                    if (mobile.length() == 10) {
                        System.out.println("sending payment transaction sms \n mobile : " + mobile + "\n Body : " + body);
                        SendSmsUtil.triggerSms(mobile, body);
                    }
                }
            });

        } else {
            eObj.setIsReceiptSent("FALSE");
            session.save(eObj);
        }

        session.getTransaction().commit();
        session.close();

    }

    @Override
    public List<PackageDetails> getClientPackagesByClient(Client client) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        List<PackageDetails> pkgList = new ArrayList<PackageDetails>();
        Collection<PackageDetails> p = new LinkedHashSet(session.createCriteria(PackageDetails.class)
                .add(Restrictions.eq("client.id", client.getId()))
                .add(Restrictions.eq("discontinue", "false"))
                .addOrder(Order.desc("id"))
                .list());
        for (PackageDetails pd : p) {
            List<PaymentTransaction> tl = new ArrayList<PaymentTransaction>();
            List<PaymentTransaction> ptl = session.createCriteria(PaymentTransaction.class).list();
            for (PaymentTransaction pt : ptl) {
                if (pt.getPackageDetailsId() == pd.getId())
                    tl.add(pt);
            }
            pd.setPaymentTransactions(tl);
        }


        List<PackageDetails> aList = new ArrayList<PackageDetails>(p);
        session.getTransaction().commit();
        session.close();
        return aList;

    }

    @Override
    public List<PaymentDataPVO> getPaymentData(String type, String gender) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<PaymentDataPVO> list = new ArrayList<PaymentDataPVO>();
        Collection<PackageDetails> collection = null;
        if (type.equals("fully-paid")) {
            collection = new LinkedHashSet(session.createCriteria(PackageDetails.class).add(Restrictions.eq("clientPackageStatus", type)).list());
        } else {
            Criteria crit = session.createCriteria(PackageDetails.class);
            Criterion c1 = Restrictions.eq("clientPackageStatus", "not paid");
            Criterion c2 = Restrictions.eq("clientPackageStatus", "partial-paid");
            LogicalExpression orExp = Restrictions.or(c1, c2);
            crit.add(orExp);
            collection = new LinkedHashSet(crit.list());
        }

        List<PackageDetails> newList = new ArrayList<PackageDetails>();
        for (PackageDetails p : collection) {
            if (gender.equals("all"))
                newList.add(p);
            else {
                if (gender.equals("male") && p.getClient().getGender().equals("male"))
                    newList.add(p);
                else if (gender.equals("female") && p.getClient().getGender().equals("female"))
                    newList.add(p);
            }
        }

        for (PackageDetails p : newList) {
            if (p.getClient() != null) {
                if (p.getClient().getDiscontinue().equalsIgnoreCase("false"))
                    list.add(new PaymentDataPVO(p.getClient().getName(), p.getPackageName(), p.getPackageFees(), p.getPackageStartDate(), p.getPackageEndDate(), p.getPackagePaymentDate(), p.getClient().getGender(), p.getClientPackageStatus(), p.getClient().getId()));
            }
        }
        session.close();
        return list;
    }


    @Override
    public List<CollectionPVO> getCollectionBy(FilterCollectionObject filter) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<CollectionPVO> collectionPVOList = new ArrayList<CollectionPVO>();
        Collection<PackageDetails> packagePaymentCollection = new LinkedHashSet(session.createCriteria(PackageDetails.class).add(Restrictions.ne("clientPackageStatus", "not paid")).add(Restrictions.eq("discontinue", "false")).list());
        if (filter.getFilter().equals("GMY")) {
            for (PackageDetails p : packagePaymentCollection) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Calendar c = Calendar.getInstance();
                try {
                    c.setTime(sdf.parse(p.getPackagePaymentDate()));
                } catch (ParseException e) {
                }

                if (filter.getGender().equalsIgnoreCase("all")) {
                    if (String.valueOf(c.get(Calendar.MONTH)).equals(filter.getMonth()) && String.valueOf(c.get(Calendar.YEAR)).equals(filter.getYear()))
                        collectionPVOList.add(new CollectionPVO(p.getClient().getName(), p.getAmountPaid(), p.getPackageName(), p.getClientPackageStatus(), p.getPackagePaymentDate()));
                } else {
                    if (filter.getGender().equals(p.getClient().getGender()) && String.valueOf(c.get(Calendar.MONTH)).equals(filter.getMonth()) && String.valueOf(c.get(Calendar.YEAR)).equals(filter.getYear()))
                        collectionPVOList.add(new CollectionPVO(p.getClient().getName(), p.getAmountPaid(), p.getPackageName(), p.getClientPackageStatus(), p.getPackagePaymentDate()));
                }
            }
        } else if (filter.getFilter().equals("GD")) {
            for (PackageDetails p : packagePaymentCollection) {

                if (filter.getGender().equalsIgnoreCase("all")) {
                    if (p.getPackagePaymentDate().equals(DateApi.getDdMmYyyyDate(filter.getDate())))
                        collectionPVOList.add(new CollectionPVO(p.getClient().getName(), p.getAmountPaid(), p.getPackageName(), p.getClientPackageStatus(), p.getPackagePaymentDate()));
                } else {
                    if (p.getPackagePaymentDate().equals(DateApi.getDdMmYyyyDate(filter.getDate())) && filter.getGender().equals(p.getClient().getGender()))
                        collectionPVOList.add(new CollectionPVO(p.getClient().getName(), p.getAmountPaid(), p.getPackageName(), p.getClientPackageStatus(), p.getPackagePaymentDate()));
                }
            }
        } else if (filter.getFilter().equals("G")) {
            for (PackageDetails p : packagePaymentCollection) {

                if (filter.getGender().equalsIgnoreCase("all")) {
                    collectionPVOList.add(new CollectionPVO(p.getClient().getName(), p.getAmountPaid(), p.getPackageName(), p.getClientPackageStatus(), p.getPackagePaymentDate()));
                } else {
                    if (p.getClient().getGender().equals(filter.getGender()))
                        collectionPVOList.add(new CollectionPVO(p.getClient().getName(), p.getAmountPaid(), p.getPackageName(), p.getClientPackageStatus(), p.getPackagePaymentDate()));
                }
            }
        }
        session.close();
        return collectionPVOList;
    }

    @Override
    public CollectionDashboardPVO getDashboardCollection() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Collection<PackageDetails> packagePaymentCollection = new LinkedHashSet(session.createCriteria(PackageDetails.class).add(Restrictions.eq("discontinue", "false")).list());
        String currentMonthName;
        String lastMonthName;
        Long enableMembersCount = 0L;
        Long disableMembersCount = 0L;


        enableMembersCount = (Long) ((Criteria) session.createCriteria(Client.class).add(Restrictions.eq("discontinue", "false")).add(Restrictions.eq("profileActiveFlag", "enable")).setProjection(Projections.rowCount())).list().get(0);
        disableMembersCount = (Long) ((Criteria) session.createCriteria(Client.class).add(Restrictions.eq("discontinue", "false")).add(Restrictions.eq("profileActiveFlag", "disable")).setProjection(Projections.rowCount())).list().get(0);

        Double male = 0.00;
        Double female = 0.00;
        Double total = 0.00;
        Double steam = 0.00;

        Double currentMonthMaleCollection = 0.00;
        Double currentMonthFemaleCollection = 0.00;
        Double currentMonthSteamCollection = 0.00;
        Double currentMonthTotalCollection = 0.00;

        Double lastMonthMaleCollection = 0.00;
        Double lastMonthFemaleCollection = 0.00;
        Double lastMonthSteamCollection = 0.00;
        Double lastMonthTotalCollection = 0.00;

        int maleFullyPaid = 0;
        int malePartialPaid = 0;
        int maleNotPaid = 0;
        int femaleFullyPaid = 0;
        int femalePartialPaid = 0;
        int femaleNotPaid = 0;
        java.time.LocalDate today = java.time.LocalDate.now();
        int systemMonth = today.getMonthValue();
        int systemYear = today.getYear();
        currentMonthName = getMonthName(systemMonth);
        lastMonthName = getMonthName(systemMonth - 1);
        if (packagePaymentCollection != null) {
            for (PackageDetails p : packagePaymentCollection) {

                if (p.getClient() != null) {
                    if (p.getClient().getDiscontinue().equalsIgnoreCase("false")) {
                        if (p.getClient().getGender().equals("male")) {
                            // collect total amount in male variable
                            male = male + p.getAmountPaid();

                            int packagePaymentMonth = getMonthFromDate(p.getPackagePaymentDate());
                            int year = getYearFromDate(p.getPackagePaymentDate());
                            // collect current month & last month collection
                            if (systemMonth == packagePaymentMonth && systemYear == year)
                                currentMonthMaleCollection = currentMonthMaleCollection + p.getAmountPaid();
                            else if ((systemMonth - 1) == packagePaymentMonth && systemYear == year) {
                                lastMonthMaleCollection = lastMonthMaleCollection + p.getAmountPaid();
                            }


                            if (p.getClientPackageStatus().equalsIgnoreCase("fully-paid") && p.getDiscontinue().equalsIgnoreCase("false"))
                                maleFullyPaid++;
                            else if (p.getClientPackageStatus().equalsIgnoreCase("partial-paid") && p.getDiscontinue().equalsIgnoreCase("false"))
                                malePartialPaid++;
                            else if (p.getClientPackageStatus().equalsIgnoreCase("not paid") && p.getDiscontinue().equalsIgnoreCase("false"))
                                maleNotPaid++;
                        } else if (p.getClient().getGender().equals("female")) {
                            // collect total amount in female variable
                            female = female + p.getAmountPaid();

                            int packagePaymentMonth = getMonthFromDate(p.getPackagePaymentDate());
                            int year = getYearFromDate(p.getPackagePaymentDate());
                            // collect current month & last month collection
                            if (systemMonth == packagePaymentMonth && systemYear == year)
                                currentMonthFemaleCollection = currentMonthFemaleCollection + p.getAmountPaid();
                            else if ((systemMonth - 1) == packagePaymentMonth && systemYear == year) {
                                lastMonthFemaleCollection = lastMonthFemaleCollection + p.getAmountPaid();
                            }

                            if (p.getClientPackageStatus().equalsIgnoreCase("fully-paid") && p.getDiscontinue().equalsIgnoreCase("false"))
                                femaleFullyPaid++;
                            else if (p.getClientPackageStatus().equalsIgnoreCase("partial-paid") && p.getDiscontinue().equalsIgnoreCase("false"))
                                femalePartialPaid++;
                            else if (p.getClientPackageStatus().equalsIgnoreCase("not paid") && p.getDiscontinue().equalsIgnoreCase("false"))
                                femaleNotPaid++;
                        }
                    }
                }    // end if

            }// end for
        }

        total = male + female;
        currentMonthTotalCollection = currentMonthMaleCollection + currentMonthFemaleCollection + currentMonthSteamCollection;
        lastMonthTotalCollection = lastMonthMaleCollection + lastMonthFemaleCollection + lastMonthSteamCollection;

        CollectionDashboardPVO obj = new CollectionDashboardPVO(male, female, total, steam,
                new LinkedHashSet(session.createCriteria(Client.class).add(Restrictions.eq("discontinue", "false")).add(Restrictions.eq("gender", "male")).list()).size(),
                new LinkedHashSet(session.createCriteria(Client.class).add(Restrictions.eq("discontinue", "false")).add(Restrictions.eq("gender", "female")).list()).size(),
                new LinkedHashSet(session.createCriteria(Client.class).add(Restrictions.eq("discontinue", "false")).list()).size(),
                maleFullyPaid, malePartialPaid, maleNotPaid,
                femaleFullyPaid, femalePartialPaid, femaleNotPaid,
                currentMonthMaleCollection, currentMonthFemaleCollection, currentMonthSteamCollection, currentMonthTotalCollection,
                lastMonthMaleCollection, lastMonthFemaleCollection, lastMonthSteamCollection, lastMonthTotalCollection,
                currentMonthName, lastMonthName, getTodaysBirthdays(), enableMembersCount, disableMembersCount);
        session.close();

        return obj;

    }

    @Override
    public void approveTransaction(String txnId) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        PaymentTransaction txn = (PaymentTransaction) session.createCriteria(PaymentTransaction.class).add(Restrictions.eq("id", Integer.parseInt(txnId))).uniqueResult();
        txn.setIsApproved("YES");
        session.save(txn);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateClientAssignedPackage(String u_pkgId, String u_startdate, String u_enddate, String u_fees, User user) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        PackageDetails pd = (PackageDetails) session.createCriteria(PackageDetails.class).add(Restrictions.eq("id", Integer.parseInt(u_pkgId))).uniqueResult();
        if (pd.getAmountPaid() < Double.valueOf(u_fees) && pd.getAmountPaid() > 0)
            pd.setClientPackageStatus("partial-paid");
        else if (pd.getAmountPaid() < Double.valueOf(u_fees) && pd.getAmountPaid() == 0)
            pd.setClientPackageStatus("not-paid");
        else if (pd.getAmountPaid() > Double.valueOf(u_fees) || pd.getAmountPaid() == Double.valueOf(u_fees))
            pd.setClientPackageStatus("fully-paid");
        pd.setPackageFees(Double.parseDouble(u_fees));
        pd.setPackageStartDate(DateApi.getDdMmYyyyDate(u_startdate));
        CPackage c = (CPackage) session.load(CPackage.class, pd.getcPackageId());
        //pd.setPackageEndDate(DateApi.getDdMmYyyyDate(addDaysToDate(u_startdate ,  c.getDays())));
        pd.setPackageEndDate(DateApi.getDdMmYyyyDate(u_enddate));
        session.save(pd);
        logActivity(session, pd.getClient(), user, ACTIVITY_TYPE_UPDATE_CLIENT_EXISTING_PACKAGE, null);
        session.getTransaction().commit();
        session.close();
    }


    @Override
    public void deleteClientAssignedPackage(String u_pkgId, User user) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        PackageDetails pd = (PackageDetails) session.createCriteria(PackageDetails.class).add(Restrictions.eq("id", Integer.parseInt(u_pkgId))).uniqueResult();
        pd.setDiscontinue("true");
        session.save(pd);
        logActivity(session, pd.getClient(), user, ACTIVITY_TYPE_DELETE_CLIENT_EXISTING_PACKAGE, null);
        session.getTransaction().commit();
        session.close();

    }

    public static int daysDiff(Date from, Date until) {
        Calendar cFrom = Calendar.getInstance();
        cFrom.setTime(getDateAtNoon(from));
        int cFromDSTOffset = cFrom.get(Calendar.DST_OFFSET);
        long cFromTime = cFrom.getTime().getTime() + (long) cFromDSTOffset;
        Calendar cUntil = Calendar.getInstance();
        cUntil.setTime(getDateAtNoon(until));
        int cUntilDSTOffset = cUntil.get(Calendar.DST_OFFSET);
        long cUntilTime = cUntil.getTime().getTime() + (long) cUntilDSTOffset;
        return (int) ((cUntilTime - cFromTime) / MILLISECONDS_IN_DAY);
    }

    public static Date getDateAtNoon(Date datetime) {
        if (datetime == null)
            return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(datetime);
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    @Override
    public List<ReferenceVO> getReferenceList() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        //
        List<Client> list = new ArrayList<Client>();
        List<ReferenceVO> refList = new ArrayList<ReferenceVO>();
        Criteria crit = session.createCriteria(Client.class);
        crit.add(Restrictions.eq("discontinue", "false"));
        list = crit.list();
        for (Client c : list) {
            refList.add(new ReferenceVO(c.getId(), c.getName()));
        }
        session.close();

        return refList;
    }

    private void logActivity(Session session, Client c, User user, String activity, String amount) {
        Notifications noti = null;
        if (c != null)
            noti = new Notifications(user.getName(), activity, c.getName(), amount, "false", c.getGender(), c.getId(), new SimpleDateFormat("dd/MM/YYYY hh:mm:ss").format(new Date()));
        else
            noti = new Notifications(user.getName(), activity, "", amount, "false", "", -1, new SimpleDateFormat("dd/MM/YYYY hh:mm:ss").format(new Date()));
        session.save(noti);
    }

    @Override
    public List<Notifications> getNotifications(User user) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<Notifications> notiList = new ArrayList<Notifications>();

        Criteria crit = session.createCriteria(Notifications.class);
        if (user.getAuthorizedToApprovePayment().equals("NO"))
            crit.add(Restrictions.eq("trainer", "Swati Hadpad"));
        crit.add(Restrictions.eq("discontinue", "false"));
        crit.setMaxResults(50);
        crit.addOrder(Order.desc("id"));
        notiList = crit.list();
        session.close();
        return notiList;
    }

    @Override
    public void discardNotification(String notiId) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Notifications noti = (Notifications) session.createCriteria(Notifications.class).add(Restrictions.eq("id", Integer.parseInt(notiId))).uniqueResult();
        noti.setDiscontinue("true");
        session.save(noti);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void submitFemaleAditionalDataForm(FemaleMemberAdditionalDataVO obj, User u) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        String isAuth = "NO";
        if (u.getAuthorizedToApprovePayment().equalsIgnoreCase("true"))
            isAuth = "YES";

        obj.setDate(obj.getDay() + "/" + obj.getMonth() + "/" + obj.getYear());
        Calendar mCalendar = Calendar.getInstance();
        String month = mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        obj.setMonth(month);
        obj.setDiscontinue("false");

        session.save(obj);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<FemaleMemberAdditionalDataVO> getFemaleAditionalDataListByClientId(int clientId) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Collection<FemaleMemberAdditionalDataVO> list = new LinkedHashSet(session.createCriteria(FemaleMemberAdditionalDataVO.class)
                .add(Restrictions.eq("clientId", clientId))
                .add(Restrictions.eq("discontinue", "false"))
                .addOrder(Order.desc("id"))
                .list());
        session.close();
        return new ArrayList<FemaleMemberAdditionalDataVO>(list);
    }

    @Override
    public void deleteClientProfile(String clientid, User user) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Client client =getClientById(Integer.parseInt(clientid));
        //client.setDiscontinue("true");
        for (PackageDetails pd : client.getPackageDetails()) {
            pd.setDiscontinue("true");
            for (PaymentTransaction ptxn : pd.getPaymentTransactions()) {
                ptxn.setDiscontinue("true");
            }
        }
        client.setDiscontinue("true");
        session.save(client);
        logActivity(session, client, user, ACTIVITY_TYPE_DELETE_CLIENT_PROFILE, null);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deletePackage(String pkgid, User user) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        CPackage pd = (CPackage) session.createCriteria(CPackage.class).add(Restrictions.eq("id", Integer.parseInt(pkgid))).uniqueResult();
        pd.setDiscontinue("true");
        session.save(pd);
        //logActivity(session ,null , user , ACTIVITY_TYPE_DELETE_CLIENT_PROFILE , null);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteFemaleClientAdditionalDetails(String id, User u) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        FemaleMemberAdditionalDataVO vo = (FemaleMemberAdditionalDataVO) session.createCriteria(FemaleMemberAdditionalDataVO.class).add(Restrictions.eq("id", Integer.parseInt(id))).uniqueResult();
        vo.setDiscontinue("true");
        session.save(vo);
        //logActivity(session ,null , user , ACTIVITY_TYPE_DELETE_CLIENT_PROFILE , null);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<MemberStatPVO> getMembersBySearchCriteria(String searchCriteria) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        String zone = "none";
        String filter = "all";


        Criteria crit = session.createCriteria(Client.class).add(Restrictions.eq("discontinue", "false")).add(Restrictions.like("name", searchCriteria, MatchMode.ANYWHERE));
        Collection<Client> collection = new LinkedHashSet(crit.list());
        List<Client> cList = new ArrayList<Client>();
        for (Client c : collection) {
            cList.add(c);
        }
        List<MemberStatPVO> membersStatPVOs = new ArrayList<>();
        for (Client c : cList) {
            MemberStatPVO m = new MemberStatPVO();
            m.setId(c.getId());
            m.setName(c.getName());
            m.setGender(c.getGender());
            m.setReferPoints(c.getReferPoints());
            m.setProfileActiveFlag(c.getProfileActiveFlag());
            m.setEmail(c.getEmail());
            m.setMobile(c.getMobile());
            //m.setPaymentStatus(getLastPackagePaymentStatus(c));

            // get recent record
            Criteria c1 = session.createCriteria(PackageDetails.class);
            c1.add(Restrictions.eq("client.id", c.getId()));
            c1.add(Restrictions.eq("discontinue", "false"));
            c1.addOrder(Order.desc("id"));
            c1.setMaxResults(1);
            PackageDetails pd = ((PackageDetails) c1.uniqueResult());
            if (pd != null) {
                m.setPaymentStatus(pd.getClientPackageStatus());
                try {
                    Date firstDate = new SimpleDateFormat("dd/MM/yyyy").parse(pd.getPackageStartDate());
                    Date secondDate = new SimpleDateFormat("dd/MM/yyyy").parse(pd.getPackageEndDate());
                    m.setDaysRemaining(String.valueOf(daysDiff(new Date(), secondDate)));
                    m.setPackageDuration(pd.getPackageStartDate() + " - " + pd.getPackageEndDate());
                    m.setPackageName(pd.getPackageName());
                    m.setFeesPaid(String.valueOf(pd.getAmountPaid()));
                    m.setPackageTotalFees(String.valueOf(pd.getPackageFees()));
                    m.setPendingFees(String.valueOf(pd.getPackageFees() - pd.getAmountPaid()));
                    m.setPackagePaymentStatus(pd.getClientPackageStatus());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                m.setDaysRemaining("-");
                m.setPaymentStatus("-");
            }

            if (m.getDaysRemaining().equals("-"))
                m.setColor(GREEN);
            else {
                //if(Integer.parseInt(m.getDaysRemaining())<5 && isFeesNotPaid(c))
                if (Integer.parseInt(m.getDaysRemaining()) < 0)
                    m.setColor(RED);
                else if (Integer.parseInt(m.getDaysRemaining()) >= 0 && Integer.parseInt(m.getDaysRemaining()) <= 5)
                    m.setColor(YELLOW);
                else
                    m.setColor(GREEN);
            }

            membersStatPVOs.add(m);
        }
        List<MemberStatPVO> membersStatPVOByZones = null;

        if (zone.equalsIgnoreCase("none")) {
            membersStatPVOByZones = membersStatPVOs;
        } else if (zone.equalsIgnoreCase("green")) {
            membersStatPVOByZones = getMembersByZones(membersStatPVOs, GREEN);
        } else if (zone.equalsIgnoreCase("red")) {
            membersStatPVOByZones = getMembersByZones(membersStatPVOs, RED);
        } else if (zone.equalsIgnoreCase("yellow")) {
            membersStatPVOByZones = getMembersByZones(membersStatPVOs, YELLOW);
        }
        session.close();

        return membersStatPVOByZones;
    }

    @Override
    public List<Notifications> getMobileNotifications() {
        Session session = HibernateUtils.getSessionFactory().openSession();

        List<Notifications> notiList = new ArrayList<Notifications>();
        Criteria crit = session.createCriteria(Notifications.class);
        crit.add(Restrictions.eq("discontinue", "false"));
        crit.addOrder(Order.desc("id"));
        notiList = crit.list();
        List<Notifications> mobileNotification = new ArrayList<Notifications>();
        for (Notifications n : notiList) {
            if (n.getActivity().equalsIgnoreCase(ACTIVITY_TYPE_NEW_PAYMENT) ||
                    n.getActivity().equalsIgnoreCase(ACTIVITY_TYPE_ADD_NEW_MEMBER)) {
                mobileNotification.add(n);
            }
        }
        session.close();

        return mobileNotification;
    }

    @Override
    public void sendPendingInvoices() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<EmailDataObject> pendingInvoiceList = new ArrayList<EmailDataObject>();
        session.beginTransaction();
        Criteria crit = session.createCriteria(EmailDataObject.class);
        crit.add(Restrictions.eq("isReceiptSent", "FALSE"));
        pendingInvoiceList = crit.list();
        for (EmailDataObject obj : pendingInvoiceList) {
            if (isModuleEnabled(ProjectConstants.EMAIL_INVOICE_FLAG)) {
                getTaskExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        // your background task here
                        EmailUtil.sendEmail2(obj);
                    }
                });
                obj.setIsReceiptSent("TRUE");
            }
            session.saveOrUpdate(obj);
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void sendInvoice(String txnId, String email) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        List<EmailDataObject> pendingInvoiceList = new ArrayList<EmailDataObject>();

        EmailDataObject eObj = (EmailDataObject) session
                .createCriteria(EmailDataObject.class)
                .add(Restrictions.eq("paymentTransactionId", Integer.parseInt(txnId)))
                .add(Restrictions.eq("clientEmail", email))
                .uniqueResult();
        eObj.setClientEmail(email);

        if (isModuleEnabled(ProjectConstants.EMAIL_INVOICE_FLAG)) {
            getTaskExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    // your background task here
                    EmailUtil.sendEmail2(eObj);
                }
            });
        }
        session.saveOrUpdate(eObj);
        session.getTransaction().commit();
        session.close();
    }

    public int getMonthFromDate(String dt) {
        if (dt == null || dt.equals(""))
            return -1;
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy").withLocale(Locale.UK);
        LocalDate date = formatter.parseLocalDate(dt);
        return date.getMonthOfYear();
    }

    public int getYearFromDate(String dt) {
        if (dt == null || dt.equals(""))
            return -1;
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy").withLocale(Locale.UK);
        LocalDate date = formatter.parseLocalDate(dt);
        return date.getYear();
    }

    public String getMonthName(int monthId) {
        String month = null;
        if (monthId == 1)
            month = "January";
        if (monthId == 2)
            month = "February";
        if (monthId == 3)
            month = "March";
        if (monthId == 4)
            month = "April";
        if (monthId == 5)
            month = "May";
        if (monthId == 6)
            month = "June";
        if (monthId == 7)
            month = "July";
        if (monthId == 8)
            month = "August";
        if (monthId == 9)
            month = "September";
        if (monthId == 10)
            month = "October";
        if (monthId == 11)
            month = "November";
        if (monthId == 12)
            month = "December";

        return month;
    }

    @Override
    public String getReferralName(String cliendId) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        String space = "\\n";
        String referrals = "\'";
        List<Client> pkgList = new ArrayList<Client>();
        Collection<Client> clients = new LinkedHashSet(session.createCriteria(Client.class)
                .add(Restrictions.eq("reference", cliendId))
                .list());
        for (Client c : clients) {
            referrals = referrals + c.getName() + space;
        }
        referrals = referrals + "\'";
        session.close();
        return referrals;
    }

    @Override
    public void redeemReferPoints(String clientid) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Client client = getClientById(Integer.parseInt(clientid));
        client.setReferPoints("0");
        session.save(client);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateProfileActiveFlag(String clientid, String selectflag) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Client client = getClientById(Integer.parseInt(clientid));
        client.setProfileActiveFlag(selectflag);
        session.save(client);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void triggerEnableDisableProfileBatch() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria crit = session.createCriteria(Client.class).add(Restrictions.eq("discontinue", "false"));
        Collection<Client> collection = new LinkedHashSet(crit.list());
        List<Client> cList = new ArrayList<>();
        cList.addAll(collection);

        // creating display object
        for (Client c : cList) {

            // get recent package record and set days remaining
            Criteria c1 = session.createCriteria(PackageDetails.class);
            c1.add(Restrictions.eq("client.id", c.getId()));
            c1.add(Restrictions.eq("discontinue", "false"));
            c1.addOrder(Order.desc("id"));
            c1.setMaxResults(1);

            PackageDetails pd = ((PackageDetails) c1.uniqueResult());

            if (pd != null) {
                try {
                    if (pd.getPackageStartDate() != null && pd.getPackageEndDate() != null) {
                        Date firstDate = new SimpleDateFormat("dd/MM/yyyy").parse(pd.getPackageStartDate());
                        Date secondDate = new SimpleDateFormat("dd/MM/yyyy").parse(pd.getPackageEndDate());
                        int daysLeft = daysDiff(new Date(), secondDate);
                        if (daysLeft < -31) {
                            c.setProfileActiveFlag("disable");
                            session.saveOrUpdate(c);
                        }
                    }
                } catch (ParseException e) {
                }
            } else {
            }
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void sendFeesReminder(String clientid) {
        Session session = HibernateUtils.getSessionFactory().openSession();

        // get recent package record and set days remaining
        Criteria c1 = session.createCriteria(PackageDetails.class);
        c1.add(Restrictions.eq("client.id", clientid));
        c1.add(Restrictions.eq("discontinue", "false"));
        c1.addOrder(Order.desc("id"));
        c1.setMaxResults(1);


        PackageDetails pd = ((PackageDetails) c1.uniqueResult());

        if (pd != null) {
            String packageStatus = pd.getClientPackageStatus().toUpperCase();
            try {
                Date firstDate = new SimpleDateFormat("dd/MM/yyyy").parse(pd.getPackageStartDate());
                Date secondDate = new SimpleDateFormat("dd/MM/yyyy").parse(pd.getPackageEndDate());
                String daysRemaining = String.valueOf(daysDiff(new Date(), secondDate));
            } catch (ParseException e) {
            }
        } else {
            // no days
        }

        session.close();
    }

    public List<Client> getTodaysBirthdays() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        String todaysDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        java.time.LocalDate today = java.time.LocalDate.now();
        int systemMonth = today.getMonthValue();
        int systemDate = today.getDayOfMonth();

        Collection clients = new LinkedHashSet(session.createCriteria(Client.class)
                .add(Restrictions.eq("discontinue", "false"))
                .list());
        List<Client> list = new ArrayList<>();
        List<Client> listNames = new ArrayList<>();
        list.addAll(clients);
        for (Client c : list) {
            Date bday;
            try {
                bday = new SimpleDateFormat("dd/MM/yyyy").parse(c.getBirthDate());
                int month_ = bday.getMonth() + 1;
                int date = bday.getDate();
                if (date == systemDate && month_ == systemMonth)
                    listNames.add(c);
            } catch (ParseException e) {
            }
        }
        session.close();
        return listNames;
    }

    @Override
    public void createNewEmail(String emailSubject, String filter, String image) {
        Session session = HibernateUtils.getSessionFactory().openSession();

        List<String> clientMobileList = new ArrayList<>();
        List<PushNotificationRequest> pushRequest = new ArrayList<>();
        List<String> emailsList = new ArrayList<>();
        Criteria crit = session.createCriteria(Client.class);
        if (filter.equals("sendToEnable"))
            crit.add(Restrictions.eq("profileActiveFlag", "enable"));
        if (filter.equals("sendToDisable"))
            crit.add(Restrictions.eq("profileActiveFlag", "disable"));
        if (filter.equals("sendToEnableDisable"))
            crit.add(Restrictions.eq("discontinue", "false"));

        Collection<Client> collection = new LinkedHashSet(crit.list());
        List<Client> cList = new ArrayList<>();
        cList.addAll(collection);
        int counter = 0;
        for (Client c : cList) {
            clientMobileList.add(c.getMobile());
            pushRequest.add(new PushNotificationRequest("Hi " + c.getName(), "", image, c.getName(), c.getMobile(), ProjectConstants.EXPANDED, ProjectConstants.MAIN));
            getTaskExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    PromotionalEmailTemplate.sendEmail(c.getEmail(), c.getName(), emailSubject, image);
                }
            });
        }

        // prepare fcm payload for same msgs to all clients
        Map<String, List<String>> map = ServerCom.getAllTokensByMobileList(clientMobileList);
        List<PushNotificationRequest> finalRequestList = new ArrayList<>();
        for (PushNotificationRequest obj : pushRequest) {
            List<String> tokens = map.get(obj.getMobile());
            if (tokens != null) {
                obj.setTokensList(tokens);
                finalRequestList.add(obj);
            }
            obj.setTokensList(tokens);
        }

        // sent push notifications to fcm
        ServerCom.sentBatchFcmNotification(finalRequestList);
        session.close();
    }

    @Override
    public void createNewSms(String smsContent, String filter) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        List<String> clientMobileList = new ArrayList<>();
        List<PushNotificationRequest> pushRequest = new ArrayList<>();
        System.out.println("method called: createNewSms");
        Criteria crit = session.createCriteria(Client.class);
        if (filter.equals("sendToEnable")) {
            crit.add(Restrictions.eq("profileActiveFlag", "enable"));
        }

        if (filter.equals("sendToDisable")) {
            crit.add(Restrictions.eq("profileActiveFlag", "disable"));
        }

        if (filter.equals("sendToEnableDisable")) {
            crit.add(Restrictions.eq("discontinue", "false"));
        }

        if (filter.equals("sendToOneTwoThreeDaysMembers")) {
        }

        if (filter.equals("sendToMinusDaysMembers")) {
        }

        Iterator var6;
        if (filter.equalsIgnoreCase("sendToAll") || filter.equalsIgnoreCase("sendToEnable") || filter.equalsIgnoreCase("sendToDisable") || filter.equalsIgnoreCase("sendToEnableDisable")) {
            List<Client> cList = new ArrayList();
            cList.addAll(new LinkedHashSet(crit.list()));
            var6 = cList.iterator();

            while (var6.hasNext()) {
                Client c = (Client) var6.next();
                if (c.getMobile() != null && c.getMobile().length() == 10) {
                    SmsLogs smsLogs = new SmsLogs(c.getName(), c.getMobile(), "Hi " + c.getName() + "\n" + smsContent, DateApi.getDdMmYyyyDate(""), "0", filter, c.getGender());
                    pushRequest.add(new PushNotificationRequest("Hi " + c.getName(), smsContent, "", c.getName(), c.getMobile(), ProjectConstants.SIMPLE, ProjectConstants.MAIN));
                    clientMobileList.add(c.getMobile());
                    session.save(smsLogs);
                }
            }

        }

        if (filter.equalsIgnoreCase("sendToOneTwoThreeDaysMembers") || filter.equalsIgnoreCase("sendToMinusDaysMembers")) {
            List<MemberStatPVO> memberStatPVOs = this.getMembersBy("all", "none", "enable");
            var6 = memberStatPVOs.iterator();

            while (var6.hasNext()) {
                MemberStatPVO m = (MemberStatPVO) var6.next();
                if (!m.getDaysRemaining().equals("-")) {
                    int daysRemaining = Integer.parseInt(m.getDaysRemaining());
                    if (filter.equalsIgnoreCase("sendToOneTwoThreeDaysMembers") && daysRemaining > 0 && daysRemaining < 6) {
                        if (!m.getPaymentStatus().equalsIgnoreCase("fully-paid")) {
                            String title = "Hi " + m.getName();
                            String msg = "Your package - " + m.getPackageName() + " will expire in " + m.getDaysRemaining() + " days\nKindly pay remaining fees Rs." + m.getPendingFees() + " by clicking on this link mpay.me/8796655176 \n ProGym Kolhapur";
                            pushRequest.add(new PushNotificationRequest(title, msg, "", m.getName(), m.getMobile(), ProjectConstants.SIMPLE, ProjectConstants.MAIN));
                            clientMobileList.add(m.getMobile());
                            session.save(new SmsLogs(m.getName(), m.getMobile(), title + "\n" + msg, DateApi.getDdMmYyyyDate(""), "0", filter, m.getGender()));
                        } else {
                            String title = "Hi " + m.getName();
                            String msg = "Your package - " + m.getPackageName() + " will expire in " + m.getDaysRemaining() + " days\nKindly renew your package.\n ProGym Kolhapur";
                            pushRequest.add(new PushNotificationRequest(title, msg, "", m.getName(), m.getMobile(), ProjectConstants.SIMPLE, ProjectConstants.MAIN));
                            clientMobileList.add(m.getMobile());
                            session.save(new SmsLogs(m.getName(), m.getMobile(), title + "\n" + msg, DateApi.getDdMmYyyyDate(""), "0", filter, m.getGender()));
                        }
                    }

                    if (filter.equalsIgnoreCase("sendToMinusDaysMembers")) {
                        if (daysRemaining == 0) {
                            if (!m.getPaymentStatus().equalsIgnoreCase("fully-paid")) {
                                String title = "Hi " + m.getName();
                                String msg = "Your package - " + m.getPackageName() + " will expire today\nKindly pay remaining fees Rs." + m.getPendingFees() + " by clicking on this link mpay.me/8796655176 \n ProGym Kolhapur";
                                pushRequest.add(new PushNotificationRequest(title, msg, "", m.getName(), m.getMobile(), ProjectConstants.SIMPLE, ProjectConstants.MAIN));
                                clientMobileList.add(m.getMobile());
                                session.save(new SmsLogs(m.getName(), m.getMobile(), title + "\n" + msg, DateApi.getDdMmYyyyDate(""), "0", filter, m.getGender()));
                            } else {
                                String title = "Hi " + m.getName();
                                String msg = "Your package - " + m.getPackageName() + " will expire today\nKindly renew your package\n ProGym Kolhapur";
                                pushRequest.add(new PushNotificationRequest(title, msg, "", m.getName(), m.getMobile(), ProjectConstants.SIMPLE, ProjectConstants.MAIN));
                                clientMobileList.add(m.getMobile());
                                session.save(new SmsLogs(m.getName(), m.getMobile(), title + "\n" + msg, DateApi.getDdMmYyyyDate(""), "0", filter, m.getGender()));
                            }
                        }

                        if (daysRemaining < 0 && daysRemaining > -13) {
                            String title = "Hi " + m.getName();
                            String msg = "Your package - " + m.getPackageName() + " expired " + Math.abs(Long.valueOf(m.getDaysRemaining())) + " days ago\nKindly renew your package\n ProGym Kolhapur";
                            pushRequest.add(new PushNotificationRequest(title, msg, "", m.getName(), m.getMobile(), ProjectConstants.SIMPLE, ProjectConstants.MAIN));
                            clientMobileList.add(m.getMobile());
                            session.save(new SmsLogs(m.getName(), m.getMobile(), title + "\n" + msg, DateApi.getDdMmYyyyDate(""), "0", filter, m.getGender()));
                        }
                    }
                }
            }
        }

        // prepare fcm payload for same msgs to all clients
        Map<String, List<String>> map = ServerCom.getAllTokensByMobileList(clientMobileList);
        List<PushNotificationRequest> finalRequestList = new ArrayList<>();
        for (PushNotificationRequest obj : pushRequest) {
            List<String> tokens = map.get(obj.getMobile());
            if (tokens != null) {
                obj.setTokensList(tokens);
                finalRequestList.add(obj);
            }
            obj.setTokensList(tokens);
        }

        // sent push notifications to fcm
        ServerCom.sentBatchFcmNotification(finalRequestList);

        session.getTransaction().commit();
        session.close();
    }

    public Boolean checkIsBatchCompleted(String batchName, String date) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        BatchLogs batch = (BatchLogs) session.createCriteria(BatchLogs.class).add(Restrictions.eq("batchName", batchName)).add(Restrictions.eq("date", date)).uniqueResult();
        session.close();
        return batch != null ? true : false;
    }


    @Override
    public void sendReminderToSingleClient(String clientname, String clientid, String daysLeft, String packageName,
                                           String packageDuration, String pendingFees, String feesPaid, String packageTotalFees) {
        System.out.println("method : sendReminderToSingleClient");
        Client c = (Client) getClientById(Integer.parseInt(clientid));
        String messageLine = "Kindly pay your pending fees";
        if (isModuleEnabled(ProjectConstants.EMAIL_INVOICE_FLAG)) {
            // send email reminder
            String email = c.getEmail();
            if (email.contains("@")) ;
            {
                String subject = "ProGym Fee's reminder";
                getTaskExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        FeesReminderEmail.sendEmail(c.getEmail(), subject, c.getName(), packageName, packageDuration, daysLeft, feesPaid, pendingFees, packageTotalFees, messageLine);
                    }
                });
            }
        }

    }

    @Override
    public void triggerFeesPaymentReminderBatch() {
        if (isModuleEnabled(ProjectConstants.EMAIL_INVOICE_FLAG)) {
            System.out.println("Batch Started : triggerFeesPaymentReminderBatch");
            List<EmailPVO> emailPVOList = new ArrayList<>();
            String todaysDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
            String batchName = "FEES_REMINDER_BATCH";
            if (!checkIsBatchCompleted(batchName, todaysDate)) {
                List<MemberStatPVO> memberStatPVOs = getMembersBy("all", "none", "enable");
                for (MemberStatPVO m : memberStatPVOs) {
                    String packagePaymentStatus = m.getPackagePaymentStatus();

                    String email = m.getEmail();
                    if (email.contains("@")) ;
                    {
                        String subject = "ProGym Fee's reminder";
                        if (!m.getDaysRemaining().equalsIgnoreCase("-")) {
                            if (Integer.parseInt(m.getDaysRemaining()) > 0 && Integer.parseInt(m.getDaysRemaining()) <= 5) {
                                String messageLine = "";
                                if (!packagePaymentStatus.equalsIgnoreCase("fully-paid"))
                                    messageLine = "Kindly pay your pending fees";
                                else
                                    messageLine = "PRO GYM KOP \n\nYour package will expire in " + m.getDaysRemaining();
                                emailPVOList.add(new EmailPVO(m.getEmail(), subject, m.getName(), m.getPackageName(), m.getPackageDuration(), m.getDaysRemaining(), m.getFeesPaid(), m.getPendingFees(), m.getPackageTotalFees(), messageLine));

                            } else if (Integer.parseInt(m.getDaysRemaining()) < 0 && Integer.parseInt(m.getDaysRemaining()) > -8) {
                                final String messageLine = "PRO GYM KOP \n\nYour gym package is expired , Please renew your packages & pay if you have any pending fees by click on this link mpay.me/8796655176";
                                emailPVOList.add(new EmailPVO(m.getEmail(), subject, m.getName(), m.getPackageName(), m.getPackageDuration(), m.getDaysRemaining(), m.getFeesPaid(), m.getPendingFees(), m.getPackageTotalFees(), messageLine));
                            }
                        }
                    }

                }// end for

            }// end if

            // send emails
            System.out.println("email pvo size" + emailPVOList.size());
            sendEmails(emailPVOList);
            System.out.println("Batch finished : triggerFeesPaymentReminderBatch");

        }
    }

    public void sendEmails(List<EmailPVO> emailPVOList) {
        if(isConnected()){
            if(emailPVOList.size() > 0){
                for (EmailPVO m : emailPVOList) {
                    System.out.println("sending email to :" + m.getEmail());
                    getTaskExecutor().execute(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("sending payment reminder email :" + m.getEmail());
                            FeesReminderEmail.sendEmail(m.getEmail(), m.getSubject(), m.getName(), m.getPackageName(), m.getPackageDuration(), m.getDaysRemaining(), m.getFeesPaid(), m.getPendingFees(), m.getPackageTotalFees(), m.getMessageLine());
                        }
                    });
                }
                updateBatchCompletedStatus("FEES_REMINDER_BATCH", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            }
        }
    }

    public static boolean isConnected() {
        try {
            final URL url = new URL("http://www.google.com");
            final URLConnection conn = url.openConnection();
            conn.connect();
            conn.getInputStream().close();
            return true;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            return false;
        }
    }


    private void updateBatchCompletedStatus(String batchName, String todaysDate) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        BatchLogs batchObject = new BatchLogs(batchName, todaysDate, "completed");
        session.save(batchObject);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<SmsLogs> getSmsLogs() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<SmsLogs> smsList = new ArrayList<SmsLogs>();
        Criteria crit = session.createCriteria(SmsLogs.class);
        crit.setMaxResults(50);
        crit.addOrder(Order.desc("id"));
        smsList = crit.list();
        session.getTransaction().commit();
        session.close();
        return smsList;
    }

    @Override
    public void renewPackage(String clientid, User user) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        // get current list & add to existing list - pkgdetails
        //
        session.beginTransaction();

        Criteria c1 = session.createCriteria(PackageDetails.class);
        c1.add(Restrictions.eq("client.id", Integer.parseInt(clientid)));
        c1.add(Restrictions.eq("discontinue", "false"));
        c1.addOrder(Order.desc("id"));
        c1.setMaxResults(1);
        PackageDetails pd = ((PackageDetails) c1.uniqueResult());
        if (pd != null) {


            PackageDetails pd1 = new PackageDetails();
            pd1.setClientPackageStatus("not paid");
            CPackage c = (CPackage) session.load(CPackage.class, pd.getcPackageId());
            pd1.setPackageStartDate(addDaysToDateToddmmyyyy(pd.getPackageEndDate(), 1));
            pd1.setPackageEndDate(addDaysToDateToddmmyyyy(pd1.getPackageStartDate(), c.getDays()));
            pd1.setcPackageId(pd.getcPackageId());
            pd1.setDiscontinue("false");
            pd1.setAmountPaid(0.0);
            pd1.setPackageName(c.getPackageName());
            pd1.setPackageFees(c.getFees());

            Client client = getClientById(Integer.parseInt(clientid));
            pd1.setClient(client);
            session.save(pd1);
            logActivity(session, client, user, ACTIVITY_TYPE_ASSIGN_PACKAGE_TO_CLIENT, String.valueOf(pd1.getPackageFees()));
        }
        session.getTransaction().commit();
        session.close();
    }

    public String addDaysToDate(String oldDate, int daysToAdd) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            //Setting the date to the given date
            c.setTime(sdf.parse(oldDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //Number of Days to add
        c.add(Calendar.DAY_OF_MONTH, daysToAdd);
        //Date after adding the days to the given date
        String newDate = sdf.format(c.getTime());

        return newDate;
    }

    public String addDaysToDateToddmmyyyy(String oldDate, int daysToAdd) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        try {
            //Setting the date to the given date
            c.setTime(sdf.parse(oldDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //Number of Days to add
        c.add(Calendar.DAY_OF_MONTH, daysToAdd);
        //Date after adding the days to the given date
        String newDate = sdf.format(c.getTime());

        return newDate;
    }

    @Override
    public void updatePhotoInfo(Integer cid, String uploadedImagePath) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Client client = getClientById(cid);
        client.setPhoto(uploadedImagePath);
        session.save(client);
        ServerCom.sendSingleObjectToServer(ServerApi.UPDATE_CLIENT_API, client);
        session.getTransaction().commit();
        session.close();
    }

    public List<SmsLogs> getSmsByFilter(String gender) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Criteria crit = session.createCriteria(SmsLogs.class);
        crit.add(Restrictions.eq("timestamp", DateApi.getDdMmYyyyDate("")));
        crit.add(Restrictions.eq("delivered", "0"));
        crit.add(Restrictions.eq("gender", gender));
        List<SmsLogs> smsList = crit.list();
        session.close();
        return smsList;
    }

    public void updateSmsDeliveryStatus(String id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        boolean success = true;

        try {
            String[] arr = id.split(",");
            Integer[] arrNew = new Integer[arr.length - 1];
            int c = 0;

            for (int i = 1; i < arr.length; ++i) {
                arrNew[c] = Integer.parseInt(arr[i]);
                ++c;
            }

            List<SmsLogs> list = session.createCriteria(SmsLogs.class).add(Restrictions.in("id", arrNew)).list();

            for (int i = 0; i < list.size(); ++i) {
                SmsLogs sms = (SmsLogs) list.get(i);
                sms.setDelivered("1");
                session.save(sms);
            }
        } catch (Exception var13) {
            System.out.println(var13.toString());
            success = false;
        } finally {
            if (success) {
                session.getTransaction().commit();
            }

        }
        session.close();

    }

    @Override
    public BrandImages getImageObjectByBrand(String brandName) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        BrandImages object = (BrandImages) session.createCriteria(BrandImages.class).add(Restrictions.eq("brandName", brandName)).uniqueResult();
        session.close();
        return object;
    }

    @Override
    public void addWorkoutObjectToDatabase(String clientId, String workoutDate, String mtid) {
        WorkoutScheduleObject object = new WorkoutScheduleObject(Integer.parseInt(clientId), DateApi.getDdMmYyyyDate(workoutDate), "false", mtid);
        ServerCom.sendSingleObjectToServer(ServerApi.CREATE_WORKOUT_SCHEDULE_OBJECT_API, object);

    }

    @Override
    public void deleteSubType(String subTypeId) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        WorkoutSubType object = (WorkoutSubType) session.createCriteria(WorkoutSubType.class).add(Restrictions.eq("id", Integer.parseInt(subTypeId))).uniqueResult();
        object.setDiscontinue("true");
        session.save(object);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void addMainWorkoutToDatabase(String workoutName) {
        T_workoutMainType object = new T_workoutMainType(workoutName, "false");
        ServerCom.sendSingleObjectToServer(ServerApi.CREATE_T_MAIN_WORKOUT_TYPE_API, object);
    }

    @Override
    public void deleteMainWorkoutType(Integer id) {
        T_workoutMainType obj = getTMainWorkoutTypeById(String.valueOf(id));
        obj.setDiscontinue("true");
        ServerCom.sendSingleObjectToServer(ServerApi.UPDATE_T_MAIN_WORKOUT_TYPE_API, obj);
    }

    @Override
    public void deleteSubWorkoutType(Integer id) {

        T_workoutSubType object = getT_workoutSubTypeById(String.valueOf(id));
        object.setDiscontinue("true");
        ServerCom.sendSingleObjectToServer(ServerApi.UPDATE_T_MAIN_WORKOUT_TYPE_API, object);
    }

    @Override
    public void addSubWorkoutToDatabase(String mainWorkoutId, String subWorkoutName, Integer sets, Integer reps) {
        T_workoutMainType mainType = (T_workoutMainType) getTMainWorkoutTypeById(mainWorkoutId);
        T_workoutSubType object = new T_workoutSubType(subWorkoutName, mainType, "false", sets, reps);
        object.setMtid(Integer.parseInt(mainWorkoutId));
        ServerCom.sendSingleObjectToServer(ServerApi.CREATE_T_SUB_WORKOUT_TYPE_API, object);
    }

    public List<Client> getAllClients() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        ArrayList<Client> list = new ArrayList<Client>(new LinkedHashSet((session.createCriteria(Client.class).list())));
        session.close();
        return list;
    }

    public List<String> getMissedClientRecords() throws Exception {
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<String> jsonStringsList = new ArrayList<>();
        String response = ServerCom.sendGetRequestToServer(ServerApi.GET_ALL_CLIENT_EXTERNAL_CODES_API);
        if (!response.isEmpty()) {
            String externalCodes = new ObjectMapper().readValue(response, String.class);
            List<Integer> arr = new ArrayList<>();
            for (String s : externalCodes.split(",")) {
                arr.add(Integer.parseInt(s));
            }
            System.out.println("Records found in server :" + arr.size());
            List<Client> list = new ArrayList<Client>(new LinkedHashSet((session.createCriteria(Client.class).add(Restrictions.not(Restrictions.in("id", arr))).list())));
            System.out.println("Missing Records found :" + list.size());
            for (Client c : list) {
                jsonStringsList.add(new ObjectMapper().writeValueAsString(c));
            }
        } else {
            System.out.println("Sending all client records to server");
            for (Client c : getAllClients()) {
                jsonStringsList.add(new ObjectMapper().writeValueAsString(c));
            }
        }
        session.close();
        return jsonStringsList;
    }


    public List<String> getMissedWorkoutScheduleObjectRecords() throws Exception {
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<String> jsonStringsList = new ArrayList<>();
        String response = ServerCom.sendGetRequestToServer(ServerApi.GET_ALL_WORKOUT_SCHEDULE_OBJECT_EXTERNAL_CODES_API);
        if (!response.isEmpty()) {
            String externalCodes = new ObjectMapper().readValue(response, String.class);
            List<Integer> arr = new ArrayList<>();
            for (String s : externalCodes.split(",")) {
                arr.add(Integer.parseInt(s));
            }
            System.out.println("Workout schedule object records found in server :" + arr.size());

            List<WorkoutScheduleObject> list = new ArrayList<WorkoutScheduleObject>(new LinkedHashSet((session.createCriteria(WorkoutScheduleObject.class).add(Restrictions.not(Restrictions.in("id", arr))).list())));
            System.out.println("Workout schedule object missing Records found :" + list.size());
            for (WorkoutScheduleObject c : list) {
                c.setCid(c.getCid());
                c.setMtid(c.getMainWorkoutType().getName());
                jsonStringsList.add(new ObjectMapper().writeValueAsString(c));
            }
        } else {
            /*System.out.println("Sending all Workout schedule object records to server");
            for(Client c : getAllClients()){
                jsonStringsList.add(new ObjectMapper().writeValueAsString(c));
            }*/
        }
        session.close();
        return jsonStringsList;
    }

    public List<String> getMissedWorkoutSubTypeObjectRecords() throws Exception {
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<String> jsonStringsList = new ArrayList<>();
        String response = ServerCom.sendGetRequestToServer(ServerApi.GET_ALL_WORKOUT_SUB_TYPE_EXTERNAL_CODES_API);
        if (!response.isEmpty()) {
            String externalCodes = new ObjectMapper().readValue(response, String.class);
            List<Integer> arr = new ArrayList<>();
            for (String s : externalCodes.split(",")) {
                arr.add(Integer.parseInt(s));
            }
            System.out.println("Workout sub type object Records found in server :" + arr.size());

            List<WorkoutSubType> list = new ArrayList<WorkoutSubType>(new LinkedHashSet((session.createCriteria(WorkoutSubType.class).add(Restrictions.not(Restrictions.in("id", arr))).list())));
            System.out.println("Workout sub type object Missing Records found :" + list.size());
            for (WorkoutSubType c : list) {
                c.setWsoid(c.getWorkoutScheduleObject().getId());
                c.setTwsid(c.getSubType().getName());
                c.setImage(c.getSubType().getGifFilePath());
                jsonStringsList.add(new ObjectMapper().writeValueAsString(c));
            }
        } else {
            /*System.out.println("Sending all Workout sub type object records to server");
            for(Client c : getAllClients()){
                jsonStringsList.add(new ObjectMapper().writeValueAsString(c));
            }*/
        }
        session.close();
        return jsonStringsList;
    }


    @Override
    public void syncClientData() throws Exception {
        SendSmsUtil.triggerSms("9421902653", "hello , welcome to pro gym");
        //ServerCom.sendSingleObjectToServer(CREATE_CLIENT_API,new ObjectMapper().writeValueAsString(getClientById(17)));
        //System.out.print(new ObjectMapper().writeValueAsString(getDietByDate("2021-02-19","124")));


        //ServerCom.sendMultipleObjectToServer(CREATE_DIET_TEMPLATE_API,getAllDietPlanTemplatesJsonString());

        //ServerCom.sendMultipleObjectToServer(UPDATE_DIET_PLAN_API,getAllDietPlanObjectsJsonString());

        //ServerCom.sendMultipleObjectToServer(CREATE_T_SUB_WORKOUT_TYPE_API,getAllSubWorkoutTypeListAsJsonString());

        //ServerCom.sendMultipleObjectToServer(CREATE_WORKOUT_SCHEDULE_OBJECT_API,getAllWorkoutScheduleObjectListAsJsonString());

        //ServerCom.sendMultipleObjectToServer(CREATE_T_SUB_WORKOUT_TYPE_API,getAllSubWorkoutTypeListAsJsonString());

        //ServerCom.sendMultipleObjectToServer(CREATE_WORKOUT_SUB_TYPE_SET_API,getAllWorkoutSubTypeSetAsJsonString());
    }

    @Override
    public void reconcileContacts() {
        // RECONCILE CLIENT RECORDS WITH SERVER
        try {
            List<String> records = getMissedClientRecords();
            if (!records.isEmpty())
                ServerCom.sendMultipleObjectToServer(ServerApi.CREATE_CLIENT_API, records);
        } catch (Exception e) {
        }
    }

    @Override
    public void reconcileWorkoutData() {
        // RECONCILE WORKOUT SCHEDULE OBJECT RECORDS WITH SERVER
        try {
            List<String> records = getMissedWorkoutScheduleObjectRecords();
            if (!records.isEmpty())
                ServerCom.sendMultipleObjectToServer(ServerApi.CREATE_WORKOUT_SCHEDULE_OBJECT_API, records);
        } catch (Exception e) {
        }

        // RECONCILE WORKOUT SUB TYPE RECORDS WITH SERVER
        try {
            List<String> records = getMissedWorkoutSubTypeObjectRecords();
            if (!records.isEmpty())
                ServerCom.sendMultipleObjectToServer(ServerApi.CREATE_WORKOUT_SUB_TYPE_API, records);
        } catch (Exception e) {
        }
    }

    @Override
    public List<BloodGroupDetails> getBloodGroupDetails(String bg) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        if (bg.equalsIgnoreCase("A_plus"))
            bg = "A+";
        if (bg.equalsIgnoreCase("A_minus"))
            bg = "A-";
        if (bg.equalsIgnoreCase("B_plus"))
            bg = "B+";
        if (bg.equalsIgnoreCase("B_minus"))
            bg = "B-";
        if (bg.equalsIgnoreCase("AB_plus"))
            bg = "AB+";
        if (bg.equalsIgnoreCase("AB_minus"))
            bg = "AB-";
        if (bg.equalsIgnoreCase("O_plus"))
            bg = "O+";
        if (bg.equalsIgnoreCase("O_minus"))
            bg = "O-";
        Collection<Client> coll = new LinkedHashSet((session.createCriteria(Client.class)
                .add(Restrictions.eq("gender", "male"))
                .add(Restrictions.eq("bloodGroup", bg))).list());
        List<BloodGroupDetails> bgList = new ArrayList<>();
        for (Client c : coll) {
            bgList.add(new BloodGroupDetails(c.getName(), c.getBloodGroup(), c.getMobile(), c.getEmail(), c.getAddress()));
        }
        session.close();
        return bgList;

    }

    @Override
    public void setDefaultWorkoutPlan(String clientId, String workoutPlan) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Client object = getClientById(Integer.parseInt(clientId));
        object.setAwp(workoutPlan);
        session.saveOrUpdate(object);
        ServerCom.sendSingleObjectToServer(ServerApi.UPDATE_CLIENT_API, object);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<T_workoutMainType> getActiveWorkoutPlansList() {
        /*return new ArrayList<T_workoutMainType>(new LinkedHashSet((session.createCriteria(T_workoutMainType.class)
                .add(Restrictions.eq("discontinue", "false"))
                .list())));*/
        List<T_workoutMainType> list = new ArrayList<>();
        String response = ServerCom.sendGetRequestToServer(ServerApi.GET_ALL_T_MAIN_WORKOUT_TYPE_API);
        if (!response.isEmpty()) {
            try {
                Gson gson = new Gson();
                T_workoutMainType array[] = gson.fromJson(response, T_workoutMainType[].class);
                list = Arrays.asList(array);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return list;
    }

    private List<T_workoutSubType> getSubWorkoutListForMuscles(T_workoutMainType mainWorkoutObject, WorkoutScheduleObject scheduleObject) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<T_workoutSubType> finalList = new ArrayList<>();
        MuscleWorkout obj = (MuscleWorkout) ((session.createCriteria(MuscleWorkout.class)
                .add(Restrictions.eq("day", DateApi.getDayName()))
                .add(Restrictions.eq("mainWorkoutName", mainWorkoutObject.getName())))
                .uniqueResult());

        if (obj != null) {
            T_workoutMainType mainType = (T_workoutMainType) ((session.createCriteria(T_workoutMainType.class)
                    .add(Restrictions.eq("discontinue", "false"))
                    .add(Restrictions.eq("name", obj.getSubWorkoutName())))
                    .uniqueResult());
            scheduleObject.setMainWorkoutType(mainType);
            List<T_workoutSubType> list = new ArrayList<T_workoutSubType>(new LinkedHashSet((session.createCriteria(T_workoutSubType.class)
                    .add(Restrictions.eq("discontinue", "false"))
                    .add(Restrictions.eq("mainType.id", mainType.getId()))
                    .list())));
            for (T_workoutSubType w : list) {
                if (w.getMainType().getName().equalsIgnoreCase(obj.getSubWorkoutName()))
                    finalList.add(w);
            }
        }
        session.close();
        return finalList;
    }


    @Override
    public void updateTSubworkoutType(String subWorkoutId, String serverimagePath) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        T_workoutSubType obj = (T_workoutSubType) session.createCriteria(T_workoutSubType.class).add(Restrictions.eq("id", Integer.parseInt(subWorkoutId))).uniqueResult();
        obj.setGifFilePath(serverimagePath);
        session.saveOrUpdate(obj);
        //ServerCom.sendSingleObjectToServer(UPDATE_T_SUB_WORKOUT_TYPE_API,obj);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateTSubWorkoutName(String subWorkoutId, String name, Integer sets, Integer reps) {
        T_workoutSubType obj = getT_workoutSubTypeById(subWorkoutId);
        obj.setName(name);
        obj.setSets(sets);
        obj.setReps(reps);
        ServerCom.sendSingleObjectToServer(ServerApi.UPDATE_T_SUB_WORKOUT_TYPE_API, obj);
    }

    @Override
    public List<Supplements> viewSupplements() {
        List<Supplements> list = new ArrayList<>();
        String response = ServerCom.sendGetRequestToServer(ServerApi.GET_ALL_SUPPLEMENTS);
        if (!response.isEmpty()) {
            try {
                Gson gson = new Gson();
                Supplements array[] = gson.fromJson(response, Supplements[].class);
                list = Arrays.asList(array);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return list;
    }

    @Override
    public List<Merchandise> viewMerchandise() {
        List<Merchandise> list = new ArrayList<>();
        String response = ServerCom.sendGetRequestToServer(ServerApi.GET_ALL_MERCHANDISE);
        if (!response.isEmpty()) {
            try {
                Gson gson = new Gson();
                Merchandise array[] = gson.fromJson(response, Merchandise[].class);
                list = Arrays.asList(array);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return list;
    }


    @Override
    public void updateProductToServer(String type, String productId, String productName, String oldPrice,
                                      String newPrice, String productPhoto,String productPhotoDesc, String discontinue) {
        if (type.equalsIgnoreCase("Supplements")) {
            Supplements obj = new Supplements(productId, productName, oldPrice, newPrice, productPhoto,productPhotoDesc, discontinue);
            ServerCom.sendSingleObjectToServer(ServerApi.UPDATE_SUPPLEMENTS, obj);
        }
        if (type.equalsIgnoreCase("merchandise")) {
            Merchandise obj = new Merchandise(productId, productName, oldPrice, newPrice, productPhoto, discontinue);
            ServerCom.sendSingleObjectToServer(ServerApi.UPDATE_MERCHANDISE, obj);
        }
    }



    @Override
    public void updateBrandImageToDB(String mobile, String imgCol, String newDbImageName) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        BrandImages obj = (BrandImages) session.createCriteria(BrandImages.class).add(Restrictions.eq("id", 1)).uniqueResult();
        if (imgCol.equalsIgnoreCase("appBanner_1")) {
            if (!newDbImageName.isEmpty())
                obj.setAppBanner_1(newDbImageName);
            obj.setAppBanner_1Contact(mobile);
        }
        if (imgCol.equalsIgnoreCase("appBanner_2")) {
            if (!newDbImageName.isEmpty())
                obj.setAppBanner_2(newDbImageName);
            obj.setAppBanner_2Contact(mobile);
        }
        if (imgCol.equalsIgnoreCase("appBanner_3")) {
            if (!newDbImageName.isEmpty())
                obj.setAppBanner_3(newDbImageName);
            obj.setAppBanner_3Contact(mobile);
        }
        if (imgCol.equalsIgnoreCase("appBanner_4")) {
            if (!newDbImageName.isEmpty())
                obj.setAppBanner_4(newDbImageName);
            obj.setAppBanner_4Contact(mobile);
        }
        if (imgCol.equalsIgnoreCase("appAdvertise_1")) {
            if (!newDbImageName.isEmpty())
                obj.setAppAdvertise_1(newDbImageName);
            obj.setAppAdvertise_1Contact(mobile);
        }
        if (imgCol.equalsIgnoreCase("appAdvertise_2")) {
            if (!newDbImageName.isEmpty())
                obj.setAppAdvertise_2(newDbImageName);
            obj.setAppAdvertise_2Contact(mobile);
        }
        if (imgCol.equalsIgnoreCase("appAdvertise_3")) {
            if (!newDbImageName.isEmpty())
                obj.setAppAdvertise_3(newDbImageName);
            obj.setAppAdvertise_3Contact(mobile);
        }
        if (imgCol.equalsIgnoreCase("appAdvertise_4")) {
            if (!newDbImageName.isEmpty())
                obj.setAppAdvertise_4(newDbImageName);
            obj.setAppAdvertise_4Contact(mobile);
        }

        if (imgCol.equalsIgnoreCase("login_brand_logo"))
            obj.setLogin_brand_logo(newDbImageName);
        if (imgCol.equalsIgnoreCase("banner_1"))
            obj.setBanner_1(newDbImageName);
        if (imgCol.equalsIgnoreCase("trainer_2"))
            obj.setTrainer_2(newDbImageName);
        if (imgCol.equalsIgnoreCase("trainer_1"))
            obj.setTrainer_1(newDbImageName);
        if (imgCol.equalsIgnoreCase("owner_2"))
            obj.setOwner_2(newDbImageName);
        if (imgCol.equalsIgnoreCase("owner_1"))
            obj.setOwner_1(newDbImageName);

        if (imgCol.equalsIgnoreCase("h1"))
            obj.setH1(newDbImageName);
        if (imgCol.equalsIgnoreCase("h2"))
            obj.setH2(newDbImageName);
        if (imgCol.equalsIgnoreCase("h3"))
            obj.setH3(newDbImageName);
        if (imgCol.equalsIgnoreCase("h4"))
            obj.setH4(newDbImageName);
        if (imgCol.equalsIgnoreCase("h5"))
            obj.setH5(newDbImageName);
        if (imgCol.equalsIgnoreCase("upgradePlan1_img"))
            obj.setUpgradePlan1_img(newDbImageName);
        if (imgCol.equalsIgnoreCase("upgradePlan2_img"))
            obj.setUpgradePlan2_img(newDbImageName);
        if (imgCol.equalsIgnoreCase("upgradePlan3_img"))
            obj.setUpgradePlan3_img(newDbImageName);

        session.saveOrUpdate(obj);
        ServerCom.sendSingleObjectToServer(ServerApi.UPDATE_BRAND_IMAGES_API, obj);
        session.getTransaction().commit();
        session.close();
    }


    @Override
    public List<OrderModal> getAllOrders(String filter) {
        List<OrderModal> list = new ArrayList<>();
        if (filter.isEmpty())
            filter = "all";
        String response = ServerCom.sendGetRequestToServer(ServerApi.GET_ALL_ORDERS + filter);
        if (!response.isEmpty()) {
            try {
                Gson gson = new Gson();
                OrderModal array[] = gson.fromJson(response, OrderModal[].class);
                list = (List<OrderModal>) Arrays.asList(array);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return list;
    }

    @Override
    public void syncClientUpdatedData() {
        List<ClientSyncModal> list = new ArrayList<>();
        String response = ServerCom.sendGetRequestToServer(ServerApi.GET_CLIENT_UPDATED_SYNC_RECORDS);
        if (!response.isEmpty()) {
            try {
                Gson gson = new Gson();
                ClientSyncModal array[] = gson.fromJson(response, ClientSyncModal[].class);
                for (int i = 0; i < array.length; i++) {
                    updateClientDataFromServerToOfflineDb(array[i]);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    private void updateClientDataFromServerToOfflineDb(ClientSyncModal modal) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Client c = (Client) (session.createCriteria(Client.class).add(Restrictions.eq("id", Integer.parseInt(modal.getExternalCode())))).uniqueResult();
        if (c != null) {
            c.setEmail(modal.getEmail());
            c.setBirthDate(modal.getBirthDate());
            c.setWeight(Double.valueOf(modal.getWeight()));
            c.setHeight(Double.valueOf(modal.getHeight()));
            c.setAddress(modal.getAddress());
            c.setPhoto(modal.getPhoto());
            session.saveOrUpdate(c);
            session.getTransaction().commit();
            session.close();
        }

    }

    private void updateSubWorkoutFromServerToOfflineDb(String externalCode, String clientPerformance) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        WorkoutSubType w_offline = (WorkoutSubType) (session.createCriteria(WorkoutSubType.class).add(Restrictions.eq("id", Integer.parseInt(externalCode)))).uniqueResult();
        w_offline.setClientPerformance(clientPerformance);
        session.saveOrUpdate(w_offline);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateOrderStatus(String orderId, String status) {
        String o = "order_id=" + orderId;
        String s = "status=" + status;
        ServerCom.sendGetRequestToServer(ServerApi.UPDATE_ORDER_STATUS + o + "&" + s);
    }

    public void updateExtCode(String serverId, String desktopId) {
        String o = "serverId=" + serverId;
        String s = "desktopId=" + desktopId;
        ServerCom.sendGetRequestToServer(ServerApi.UPDATE_EXT_CODE_ON_SERVER + o + "&" + s);
    }

    @Override
    public void addMemberToDatabase(AddMemberObject addMemberObject, User user, String userType) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        String photo = "";
        String serverId = "";
        String desktopId = "";
        try {
            String mobileExistsResponse = ServerCom.sendGetRequestToServer(ServerApi.GET_IS_USER_MOBILE_EXISTS + addMemberObject.getMobile());
            if (!mobileExistsResponse.isEmpty()) {
                isMobileExists result = new ObjectMapper().readValue(mobileExistsResponse, isMobileExists.class);
                String clientObjResponse = ServerCom.sendGetRequestToServer(ServerApi.SINGLE_READ_CLIENT_API + result.getResult());
                if (!clientObjResponse.isEmpty()) {
                    Gson gson = new Gson();
                    Client c = gson.fromJson(clientObjResponse, Client.class);
                    addMemberObject.setName(c.getName());
                    addMemberObject.setEmail(c.getEmail());
                    addMemberObject.setHeight(c.getHeight());
                    addMemberObject.setWeight(c.getWeight());
                    addMemberObject.setAddress(c.getAddress());
                    addMemberObject.setGender(c.getGender());
                    photo = c.getPhoto();
                    serverId = String.valueOf(c.getId());
                }
            }

        } catch (Exception e) {

        }
        String refererId = addMemberObject.getReference();

        Client c = new Client(addMemberObject.getName(), addMemberObject.getMobile(), addMemberObject.getGender(), DateApi.getDdMmYyyyDate(addMemberObject.getBirthDate()), addMemberObject.getRemarks(), "false", null, "0"
                , addMemberObject.getEmail(), addMemberObject.getAddress(), addMemberObject.getBloodGroup(), addMemberObject.getReference(), addMemberObject.getPreviousGym(),
                addMemberObject.getHeight(), addMemberObject.getWeight(), addMemberObject.getOccupation(), "enable", photo, "admin");
        session.save(c);
        if (!refererId.equalsIgnoreCase("none")) {
            Client c1 =getClientById(Integer.parseInt(refererId));
            if (c1.getReferPoints() != null) {
                int referPoints = Integer.parseInt(c1.getReferPoints());
                referPoints++;
                c1.setReferPoints(String.valueOf(referPoints));
            } else
                c1.setReferPoints("1");
            session.saveOrUpdate(c1);
        }

        logActivity(session, c, user, ACTIVITY_TYPE_ADD_NEW_MEMBER, null);
        if (!serverId.equalsIgnoreCase("")) {
            updateExtCode(serverId, String.valueOf(c.getId()));
        } else
            ServerCom.sendSingleObjectToServer(ServerApi.CREATE_CLIENT_API, c);
        session.getTransaction().commit();
        session.close();

    }


    @Override
    public void fetchUserDetailsFromServer(AddMemberObject obj) {
        try {
            String mobileExistsResponse = ServerCom.sendGetRequestToServer(ServerApi.GET_IS_USER_MOBILE_EXISTS + obj.getMobile());
            if (!mobileExistsResponse.isEmpty()) {
                isMobileExists result = new ObjectMapper().readValue(mobileExistsResponse, isMobileExists.class);
                String clientObjResponse = ServerCom.sendGetRequestToServer(ServerApi.SINGLE_READ_CLIENT_API + result.getResult());
                if (!clientObjResponse.isEmpty()) {
                    Gson gson = new Gson();
                    Client c = gson.fromJson(clientObjResponse, Client.class);
                    obj.setName(c.getName());
                    obj.setEmail(c.getEmail());
                    obj.setHeight(c.getHeight());
                    obj.setWeight(c.getWeight());
                    obj.setAddress(c.getAddress());
                    obj.setBirthDate(c.getBirthDate());
                    obj.setGender(c.getGender());
                }
            }
        } catch (Exception e) {

        }
    }

    @Override
    public List<T_workoutSubType> getWorkoutSubTypeList() {
        List<T_workoutSubType> list = new ArrayList<>();
        String response = ServerCom.sendGetRequestToServer(ServerApi.GET_ALL_T_SUB_WORKOUT_TYPE);
        if (!response.isEmpty()) {
            try {
                Gson gson = new Gson();
                T_workoutSubType array[] = gson.fromJson(response, T_workoutSubType[].class);
                list = Arrays.asList(array);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return list;
    }

    @Override
    public List<T_workoutMainType> getWorkoutMainTypeList() {
        return getActiveWorkoutPlansList();
    }

    @Override
    public List<WorkoutScheduleObject> getWorkoutListByClientId(String cliendId) {

        List<WorkoutScheduleObject> list = new ArrayList<>();
        String response = ServerCom.sendGetRequestToServer(ServerApi.GET_ALL_WORKOUT_SCHEDULE_OBJECT_BY_CLIENT_ID + cliendId);
        if (!response.isEmpty()) {
            try {
                Gson gson = new Gson();
                WorkoutScheduleObject array[] = gson.fromJson(response, WorkoutScheduleObject[].class);
                list = Arrays.asList(array);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        if (!list.isEmpty()) {
            for (WorkoutScheduleObject obj : list) {

                obj.setMainWorkoutType(getTMainWorkoutTypeById(obj.getMtid()));

                // get sub workout
                obj.setSubTypesStaticList(getSubWorkoutStaticListByMainType(Integer.parseInt(obj.getMtid())));

            }
        }
        return list;
    }

    private T_workoutMainType getTMainWorkoutTypeById(String mtid) {
        T_workoutMainType obj = null;
        // get main workout
        String resp = ServerCom.sendGetRequestToServer(ServerApi.SINGLE_READ_T_MAIN_WORKOUT_TYPE_API + mtid);
        if (!resp.isEmpty()) {
            Gson gson = new Gson();
            obj = gson.fromJson(resp, T_workoutMainType.class);
        }
        return obj;
    }

    private T_workoutSubType getT_workoutSubTypeById(String id) {
        T_workoutSubType obj = null;
        // get main workout
        String resp = ServerCom.sendGetRequestToServer(ServerApi.SINGLE_READ_T_SUB_WORKOUT_TYPE_API + id);
        if (!resp.isEmpty()) {
            Gson gson = new Gson();
            obj = gson.fromJson(resp, T_workoutSubType.class);
        }
        return obj;
    }

    @Override
    public List<WorkoutSubType> getSubWorkoutListByParentObject(int wsoid) {

        List<WorkoutSubType> list = new ArrayList<>();
        String response = ServerCom.sendGetRequestToServer(ServerApi.GET_ALL_WORKOUT_SUB_TYPE_BY_WOS_ID + wsoid);
        if (!response.isEmpty()) {
            try {
                Gson gson = new Gson();
                WorkoutSubType array[] = gson.fromJson(response, WorkoutSubType[].class);
                list = Arrays.asList(array);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return list;
    }

    @Override
    public List<T_workoutSubType> getSubWorkoutStaticListByMainType(int mainTypeId) {

        List<T_workoutSubType> list = new ArrayList<>();
        String response = ServerCom.sendGetRequestToServer(ServerApi.GET_ALL_T_SUB_WORKOUT_TYPE_BY_MAIN_TYPE_ID + mainTypeId);
        if (!response.isEmpty()) {
            try {
                Gson gson = new Gson();
                T_workoutSubType array[] = gson.fromJson(response, T_workoutSubType[].class);
                list = Arrays.asList(array);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return list;

    }

    @Override
    public void submitWorkoutSubTypeData(String workoutObjectId, String workoutSubTypeId, String sets, String maxReps) {
        T_workoutSubType obj = getT_workoutSubTypeById(workoutSubTypeId);
        WorkoutSubType object = new WorkoutSubType();
        object.setWsoid(Integer.parseInt(workoutObjectId));
        object.setTwsid(obj.getName());
        object.setSets(Integer.parseInt(sets));
        object.setMaxReps(maxReps);
        object.setDiscontinue("false");
        object.setClientPerformance("no");
        object.setImage(obj.getGifFilePath());
        ServerCom.sendSingleObjectToServer(ServerApi.CREATE_WORKOUT_SUB_TYPE_API, object);
    }

    @Override
    public void updateModuleState(String key, String newValue) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        String mac = Identity.getMacAddress();
        String api = ServerApi.UPDATE_MODULE_EMAIL_BY_MAC + mac + "&";

        ModuleObject object = (ModuleObject) session.createCriteria(ModuleObject.class).add(Restrictions.eq("mac", mac)).uniqueResult();
        if (key.equalsIgnoreCase(ProjectConstants.EMAIL_INVOICE_FLAG)) {
            api = api + "key=email&value=" + newValue;
            object.setEmail(newValue);
        }
        if (key.equalsIgnoreCase(ProjectConstants.SMS_FLAG)) {
            api = api + "key=sms&value=" + newValue;
            object.setSms(newValue);
        }
        session.saveOrUpdate(object);

        // update to server
        ServerCom.sendGetRequestToServer(api);
        session.getTransaction().commit();
        session.close();

    }

    public Boolean isModuleEnabled(String key) {
        ModuleObject object = getModuleObject();
        if (object == null) {
            updateModuleDataFromServer();
            object = getModuleObject();
        }

        return object != null ? object.isEnabled(key) : false;
    }

    public ModuleObject getModuleObject() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        ModuleObject object = null;

        String mac = Identity.getMacAddress();
        object = (ModuleObject) session.createCriteria(ModuleObject.class).add(Restrictions.eq("mac", mac)).uniqueResult();
        session.close();
        return object;
    }

    @Override
    public void updateModuleDataFromServer() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        System.out.println("isModuleEnabled called");
        String mac = Identity.getMacAddress();
        ModuleObject obj = null;
        // get server object
        String resp = ServerCom.sendGetRequestToServer(ServerApi.GET_MODULE_DATA_BY_MAC + mac);
        if (!resp.isEmpty()) {
            Gson gson = new Gson();
            obj = gson.fromJson(resp, ModuleObject.class);
        }

        if (obj != null) {

            ModuleObject object = (ModuleObject) session.createCriteria(ModuleObject.class).add(Restrictions.eq("mac", mac)).uniqueResult();
            if (object == null)
                object = new ModuleObject();
            object.setMac(mac);
            object.setEmail(obj.getEmail());
            object.setSms(obj.getSms());
            object.setDiet(obj.getDiet());
            object.setWorkout(obj.getWorkout());
            object.setMonthlyData(obj.getMonthlyData());

            session.saveOrUpdate(object);

        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public isMobileExists initiateActivateProductOperation(String mac, String secret_key) {
        isMobileExists obj = new isMobileExists();
        obj.setResult("-1");
        try {
            String resp = ServerCom.sendGetRequestToServer(ServerApi.ACTIVATE_PRODUCT_API + "mac=" + mac + "&secret_key=" + secret_key);
            if (!resp.isEmpty()) {
                Gson gson = new Gson();
                obj = gson.fromJson(resp, isMobileExists.class);
            }
        } catch (Exception e) {
        }

        return obj;
    }

    @Override
    public boolean getMacActivationStatus() {
        List<LicenseModal> list = new ArrayList<>();
        String resp = ServerCom.sendGetRequestToServer(ServerApi.CHECK_MAC_ACTIVATION_STATUS_API + Identity.getMacAddress());
        try {
            if (!resp.isEmpty()) {
                Gson gson = new Gson();
                LicenseModal array[] = gson.fromJson(resp, LicenseModal[].class);
                list = Arrays.asList(array);
            }
        } catch (Exception e) {

        }

        return list.size() > 0 ? true : false;
    }

    @Override
    public void sendBdayWish(String id) {
        Client client = getClientById(Integer.parseInt(id));

        if(isModuleEnabled(ProjectConstants.EMAIL_INVOICE_FLAG)){
            getTaskExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    BdayEmailTemplate.sendEmail2(client.getEmail(), client.getName());
                }
            });
        }
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(new SmsLogs(client.getName(), client.getMobile(), "Wishing you happy birthday , "+client.getName()+"\nfrom - Pro Gym , Kolhapur", DateApi.getDdMmYyyyDate(""), "0", "bday", client.getGender()));
        session.getTransaction().commit();
        session.close();


        PushNotificationRequest noti = new PushNotificationRequest();
        noti.setTitle("Happy Birthday " + client.getName());
        noti.setMessage("");
        noti.setImage("https://tavrostechinfo.com/PROGYM/img/birthday_3.jpg");
        noti.setNotificationType(ProjectConstants.COLLAPSED);
        noti.setTargetClass(ProjectConstants.MAIN);
        noti.setMobile(client.getMobile());
        noti.setClientName(client.getName());

        List<String> tok = new ArrayList<>();
        List<FcmTokenModal> tokens = ServerCom.getAllTokensByMobile(noti.getMobile());
        if (tokens != null && tokens.size() > 0) {
            for (FcmTokenModal t : tokens) {
                tok.add(t.getToken());
            }
            noti.setTokensList(tok);

            ServerCom.sendSameMessageToAllTokens(noti);


        }
    }

    @Override
    public void updateProductPhotoToServer(String type, String productId, String productPhoto) {
        if (type.equalsIgnoreCase("Supplements")) {
            Supplements obj = new Supplements(productId, productPhoto);
            ServerCom.sendSingleObjectToServer(ServerApi.UPDATE_PHOTO_SUPPLEMENTS, obj);
        }
        if (type.equalsIgnoreCase("merchandise")) {
            Merchandise obj = new Merchandise(productId, productPhoto);
            ServerCom.sendSingleObjectToServer(ServerApi.UPDATE_PHOTO_MERCHANDISE, obj);
        }
    }

    @Override
    public void updateProductPhotoDescToServer(String type, String productId, String productPhotoDesc) {
        if (type.equalsIgnoreCase("Supplements")) {
            Supplements obj = new Supplements();
            obj.setId(productId);
            obj.setProductPhotoDesc(productPhotoDesc);
            ServerCom.sendSingleObjectToServer(ServerApi.UPDATE_PHOTO_DESC_SUPPLEMENTS, obj);
        }
    }
}
 

