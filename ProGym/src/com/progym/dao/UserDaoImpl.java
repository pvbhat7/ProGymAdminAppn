package com.progym.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.progym.model.AddClientPackageForm;
import com.progym.model.AddMemberObject;
import com.progym.model.AddPackageObject;
import com.progym.model.BatchLogs;
import com.progym.model.CPackage;
import com.progym.model.Client;
import com.progym.model.CollectionDashboardPVO;
import com.progym.model.CollectionPVO;
import com.progym.model.EmailDataObject;
import com.progym.model.EmailPVO;
import com.progym.model.FemaleMemberAdditionalDataVO;
import com.progym.model.FilterCollectionObject;
import com.progym.model.FlagData;
import com.progym.model.MemberStatPVO;
import com.progym.model.Notifications;
import com.progym.model.User;
import com.progym.model.SmsLogs;
import com.progym.model.PackageDetails;
import com.progym.model.PaymentDataPVO;
import com.progym.model.PaymentTransaction;
import com.progym.model.ReferenceVO;
import com.progym.utils.BdayEmailTemplate;
import com.progym.utils.EmailUtil;
import com.progym.utils.FeesReminderEmail;
import com.progym.utils.HibernateUtils;
import com.progym.utils.SendSmsUtil;
import com.progym.utils.PromotionalEmailTemplate;

import java.util.*;
import org.joda.time.*;
import org.joda.time.format.*;

@Repository
public class UserDaoImpl implements UserDao {
	
	
	@Autowired  
	 private TaskExecutor taskExecutor;  
	
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
  
	Session session = null;
  
  public void register() {
		session = HibernateUtils.getSessionFactory().openSession();
	  session.beginTransaction();  
	  System.out.println("registering...");
		Criteria crit = session.createCriteria(User.class);
		Collection<User> collection = new LinkedHashSet(crit.list());
		if(collection.size() == 0) {
			/*User u1 = new User("djpranav77", "pooja.418", "Pranav Patil","YES");
			  User u2 = new User("swati", "sairaj0909","Swati HadPad", "NO");
			  session.save(u1);
			  session.save(u2);
			  session.getTransaction().commit();*/
		}
	  	  
    }
  
    public User validateUser(User user) {
    	session = HibernateUtils.getSessionFactory().openSession();
  	  session.beginTransaction();  
  return (User)session.createCriteria(User.class).add(Restrictions.eq("password", user.getPassword())).add(Restrictions.eq("username", user.getUsername())).uniqueResult();
  	
  			}
    
    @Override
    public void updateMemberToDatabase(Client c, User u) {
		session = HibernateUtils.getSessionFactory().openSession();
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
		session.saveOrUpdate(c1);
		
		logActivity(session , c , u , ACTIVITY_TYPE_UPDATE_MEMBER_PROFILE , null);		
		session.getTransaction().commit();
    }

	@Override
	public void addMemberToDatabase(AddMemberObject addMemberObject , User user, String userType) {
		String refererId = addMemberObject.getReference();
		session = HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		Client c = new Client(addMemberObject.getName(), addMemberObject.getMobile(), addMemberObject.getGender(), getDdMmYyyyDate(addMemberObject.getBirthDate()), addMemberObject.getRemarks(),"false",null,"0"
				,addMemberObject.getEmail() , addMemberObject.getAddress() , addMemberObject.getBloodGroup() ,addMemberObject.getReference(), addMemberObject.getPreviousGym(),
				addMemberObject.getHeight(),addMemberObject.getWeight(),addMemberObject.getOccupation(),"enable","NA");
		session.save(c);
		if(!refererId.equalsIgnoreCase("none")){
			Client c1 = (Client) session.get(Client.class, Integer.parseInt(refererId));
			if(c1.getReferPoints() != null)
			{int referPoints = Integer.parseInt(c1.getReferPoints());
			referPoints++;
			c1.setReferPoints(String.valueOf(referPoints));}
			else
				c1.setReferPoints("1");
			session.saveOrUpdate(c1);
		}
		
		logActivity(session , c , user , ACTIVITY_TYPE_ADD_NEW_MEMBER , null);
		
		session.getTransaction().commit();
	}

	public List<MemberStatPVO> getMembersBy(String filter , String zone , String enableDisable) {
		session = HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria crit = session.createCriteria(Client.class).add(Restrictions.eq("discontinue", "false")).add(Restrictions.eq("profileActiveFlag", enableDisable));
		Collection<Client> collection = new LinkedHashSet(crit.list());
		
		// clients list as per zone selection
		List<Client> cList = new ArrayList<Client>();
		for(Client c : collection) {
			if(filter.equals("all"))
				cList.add(c);
			else {
				if(filter.equals("male") && c.getGender().equals("male"))
					cList.add(c);
				if(filter.equals("female") && c.getGender().equals("female"))
					cList.add(c);
			}
		}
		
		// creating display object
		List<MemberStatPVO> membersStatPVOs = new ArrayList<>();
		for(Client c : cList){
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
			if(session == null || !session.isOpen())
			session = HibernateUtils.getSessionFactory().openSession();
			Criteria c1 = session.createCriteria(PackageDetails.class);
			c1.add(Restrictions.eq("client.id", c.getId()));
			c1.add(Restrictions.eq("discontinue", "false"));
			c1.addOrder(Order.desc("id"));
			c1.setMaxResults(1);
			
			
			PackageDetails pd = ((PackageDetails)c1.uniqueResult());
			
			if(pd != null){
				m.setPaymentStatus(pd.getClientPackageStatus().toUpperCase());
				try {		
					m.setPackageDuration(pd.getPackageStartDate()+" - "+pd.getPackageEndDate());
					m.setPackageName(pd.getPackageName());
					m.setFeesPaid(String.valueOf(pd.getAmountPaid()));
					m.setPackageTotalFees(String.valueOf(pd.getPackageFees()));
					m.setPendingFees(String.valueOf(pd.getPackageFees()-pd.getAmountPaid()));
					Date firstDate=new SimpleDateFormat("dd/MM/yyyy").parse(pd.getPackageStartDate());
					Date secondDate = new SimpleDateFormat("dd/MM/yyyy").parse(pd.getPackageEndDate());
					m.setDaysRemaining(String.valueOf(daysDiff(new Date(), secondDate)));
					m.setPackagePaymentStatus(pd.getClientPackageStatus());
				} catch (ParseException e) {}	
			}
			else{
				m.setDaysRemaining("-");
				m.setPaymentStatus("-");
			}
			
			// Based on days remaining , set display color
			if(m.getDaysRemaining().equals("-"))
				m.setColor(GREEN);
			else{
				//if(Integer.parseInt(m.getDaysRemaining())<5 && isFeesNotPaid(c))
				if(Integer.parseInt(m.getDaysRemaining())<5)
					m.setColor(RED);
				else if(Integer.parseInt(m.getDaysRemaining()) > 5 && Integer.parseInt(m.getDaysRemaining()) < 10)
					m.setColor(YELLOW);
				else
					m.setColor(GREEN);	
			}	
			
			membersStatPVOs.add(m);
		}
		//session.beginTransaction();
		//session.getTransaction().commit();
		session.close();
		
		// Filtering display object based on selected color by end user
		List<MemberStatPVO> membersStatPVOByZones = null;		
		if(zone.equalsIgnoreCase("none")){
			membersStatPVOByZones = membersStatPVOs;
		}
		  else if(zone.equalsIgnoreCase("green")){
			  membersStatPVOByZones = getMembersByZones(membersStatPVOs,GREEN);  
		  }
		  else if(zone.equalsIgnoreCase("red")){
			  membersStatPVOByZones = getMembersByZones(membersStatPVOs,RED);
		  }
		  else if(zone.equalsIgnoreCase("yellow")){
			  membersStatPVOByZones = getMembersByZones(membersStatPVOs,YELLOW);
		  }
		
		// pushing no package members at end of list
		return pushingNoPackageMembersToEnd(membersStatPVOByZones);
	}
	
	private List<MemberStatPVO> pushingNoPackageMembersToEnd(List<MemberStatPVO> membersStatPVOByZones) {
		List<MemberStatPVO> noPackageMembers = new ArrayList<>();
		List<MemberStatPVO> withPackageMembers = new ArrayList<>();
		for(MemberStatPVO member : membersStatPVOByZones){
			if(member.getDaysRemaining().equals("-"))
				noPackageMembers.add(member);
			else
				withPackageMembers.add(member);
		}
		
		// sorting as per days remaining
		Collections.sort(withPackageMembers);
				
		withPackageMembers.addAll(noPackageMembers);
		return withPackageMembers;
	}

	private boolean isFeesNotPaid(Client c) {
		boolean flag = false;
		List<PackageDetails> pdList = getClientPackagesByClient(c);
		if(pdList != null){
			if(!pdList.get(0).getClientPackageStatus().equalsIgnoreCase("fully-paid"))
				flag =  true;				
		}
		return flag;
	}
	
	private PackageDetails getLastPackagePaymentStatus(Client c) {
		List<PackageDetails> pdList = getClientPackagesByClient(c);
		if(pdList.size() == 0)
			return null;
		return (PackageDetails)pdList.get(0);
	}

	public List<MemberStatPVO> getMembersByZones(List<MemberStatPVO> list, String color){
		List<MemberStatPVO> membersStatPVOByZones = new ArrayList<>();
		for(MemberStatPVO m : list)
		{
			if(m.getColor().equalsIgnoreCase(color))
				membersStatPVOByZones.add(m);
		}		
		return membersStatPVOByZones;
	}

	@Override
	public void addPackageToDatabase(AddPackageObject addPackageObject , User user) {
		session = HibernateUtils.getSessionFactory().openSession();
		CPackage pkg = new CPackage(addPackageObject.getFees(), addPackageObject.getPackageName(), addPackageObject.getDays(), addPackageObject.getGender(),"false");
		session.save(pkg);
		logActivity(session ,null , user , ACTIVITY_TYPE_ADD_NEW_PACKAGE , null);
		session.beginTransaction();
		session.getTransaction().commit();
	}

	@Override
	public List<CPackage> getPackagesByFilter(String filter) {
		List<CPackage> pkg = new ArrayList<CPackage>();
		session = HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria crit = session.createCriteria(CPackage.class);
		if(!filter.equals("all"))
		crit.add(Restrictions.eq("gender",filter));
		crit.add(Restrictions.eq("discontinue","false"));
		pkg = crit.list();
		session.close();
		return pkg;		
	}
	
	public void addPackageForClientToDatabase(AddClientPackageForm o , User user) {
		
		// get current list & add to existing list - pkgdetails
		// 
		session = HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		PackageDetails pd1 = new PackageDetails();
		pd1.setClientPackageStatus("not paid");
		CPackage c = (CPackage) session.load(CPackage.class, Integer.parseInt(o.getCpackageId()));
		pd1.setPackageStartDate(getDdMmYyyyDate(o.getStartDate()));
		pd1.setPackageEndDate(getDdMmYyyyDate(addDaysToDate(o.getStartDate() ,  c.getDays())));
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
		logActivity(session , c1 , user, ACTIVITY_TYPE_ASSIGN_PACKAGE_TO_CLIENT , String.valueOf(pd1.getPackageFees()));
		session.getTransaction().commit();
	}

	@Override
	public Client getClientById(int clientId) {
		// build whole client object
		Client c = null;
		Session session = HibernateUtils.getSessionFactory().openSession();
		Criteria crit = session.createCriteria(Client.class).add(Restrictions.eq("id", clientId));
		c = (Client) crit.uniqueResult();
		session.close();
		return c;
	}
	
	@Override
	public CPackage getPackageById(int id) {
		CPackage p = null;
		session = getSession();
		if(!session.getTransaction().isActive())
		session.beginTransaction();
		Criteria crit = session.createCriteria(CPackage.class);
		crit.add(Restrictions.eq("id",id));
		crit.add(Restrictions.eq("discontinue","false"));
		p = (CPackage) crit.uniqueResult();
		return p;
	}
	
	public Session getSession() {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		if(factory.getCurrentSession() != null) {
			return factory.getCurrentSession();
			
			}
		else {
			return factory.openSession();}		
	}
	
	@Override
	public void createTransaction(PaymentTransaction paymentTransaction ,Boolean isAuthorized, User user) {
		EmailDataObject eObj = new EmailDataObject();
		session =  HibernateUtils.getSessionFactory().openSession();
			session.beginTransaction();
			String isAuth = "NO";
			if(isAuthorized)
				isAuth= "YES";
			
		PaymentTransaction p = new PaymentTransaction(new SimpleDateFormat("dd/MM/yyyy").format(new Date()), paymentTransaction.getPackageDetailsId(), paymentTransaction.getFeesPaid(),isAuth,"false",paymentTransaction.getPaymentMode());
		PackageDetails pd = (PackageDetails) session.createCriteria(PackageDetails.class).add(Restrictions.eq("id", paymentTransaction.getPackageDetailsId())).uniqueResult();
		pd.setAmountPaid(pd.getAmountPaid()+paymentTransaction.getFeesPaid());
		CPackage c = (CPackage) session.load(CPackage.class, pd.getcPackageId());
		if(pd.getPackageFees() > pd.getAmountPaid())
			pd.setClientPackageStatus("partial-paid");
		else
			pd.setClientPackageStatus("fully-paid");
		pd.setPackagePaymentDate(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		logActivity(session , pd.getClient() , user , ACTIVITY_TYPE_NEW_PAYMENT , String.valueOf(p.getFeesPaid()));
		session.save(p);
		eObj.setClientEmail(pd.getClient().getEmail());
		eObj.setClientName(pd.getClient().getName());
		eObj.setPackageName(pd.getPackageName());
		eObj.setPaymentDate(new SimpleDateFormat("dd/MM/YYYY hh:mm:ss").format(new Date()));
		eObj.setDuration(pd.getPackageStartDate()+" - "+pd.getPackageEndDate());
		eObj.setRemainingAmount(String.valueOf(pd.getPackageFees()-pd.getAmountPaid()));
		eObj.setAmount(String.valueOf(paymentTransaction.getFeesPaid()));
		
		// get send receipt flag
		FlagData emailInvoiceFlag = (FlagData) session.createCriteria(FlagData.class).add(Restrictions.eq("flagKey", "EMAIL_INVOICE_FLAG")).uniqueResult();
		eObj.setPaymentTransactionId(p.getId());
		if(emailInvoiceFlag.getFlagValue().equalsIgnoreCase("TRUE")){
			eObj.setIsReceiptSent("TRUE");
			session.save(eObj);
			 taskExecutor.execute(new Runnable() {  
				   @Override  
				   public void run() {  
				     // your background task here  
					   EmailUtil.sendEmail2(eObj);
					   String mobile = pd.getClient().getMobile();
					   String body = "Hi "+pd.getClient().getName()+"\n ProGym has received your payment of Rs."+paymentTransaction.getFeesPaid()+" for package "+pd.getPackageName();
					   if(mobile.length() == 10){
						   System.out.println("sending payment transaction sms \n mobile : "+mobile+"\n Body : "+body);
						   SendSmsUtil.triggerSms(mobile, body);   
						   SmsLogs smsLogs = new SmsLogs(pd.getClient().getName(),pd.getClient().getMobile(),body,String.valueOf(new Date()));
						   session.save(smsLogs);
					   }
					   
				   }  
				 }); 
			
		}
		else{
			eObj.setIsReceiptSent("FALSE");
			session.save(eObj);
		}
				
		session.getTransaction().commit();
		session.close();
		
	}

	@Override
	public List<PackageDetails> getClientPackagesByClient(Client client) {
		List<PackageDetails> pkgList = new ArrayList<PackageDetails>();
		session = HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		Collection<PackageDetails> p = new LinkedHashSet(session.createCriteria(PackageDetails.class)
				.add(Restrictions.eq("client.id", client.getId()))
				.add(Restrictions.eq("discontinue","false"))
				.addOrder(Order.desc("id"))
				.list());
		for(PackageDetails pd : p) {
			List<PaymentTransaction> tl = new ArrayList<PaymentTransaction>();
			List<PaymentTransaction> ptl = session.createCriteria(PaymentTransaction.class).list();
			for(PaymentTransaction pt : ptl) {
				if(pt.getPackageDetailsId() == pd.getId())
					tl.add(pt);
			}
			pd.setPaymentTransactions(tl);
		}	
		
		
	    List<PackageDetails> aList = new ArrayList<PackageDetails>(p);
	    session.getTransaction().commit();
	    return aList;
		
	}

	@Override
	public List<PaymentDataPVO> getPaymentData(String type,String gender) {
		session = HibernateUtils.getSessionFactory().openSession();
		List<PaymentDataPVO> list = new ArrayList<PaymentDataPVO>();
		Collection<PackageDetails> collection = null;
		if(type.equals("fully-paid")) {
				collection = new LinkedHashSet(session.createCriteria(PackageDetails.class).add(Restrictions.eq("clientPackageStatus", type)).list());
		}
			
		else {
			Criteria crit = session.createCriteria(PackageDetails.class);
			Criterion c1 = Restrictions.eq("clientPackageStatus", "not paid");
			Criterion c2 = Restrictions.eq("clientPackageStatus", "partial-paid");
			LogicalExpression orExp = Restrictions.or(c1,c2);
			crit.add(orExp);
			collection = new LinkedHashSet(crit.list());
		}
		
		List<PackageDetails> newList = new ArrayList<PackageDetails>();
		for(PackageDetails p : collection) {
			if(gender.equals("all"))
				newList.add(p);
				else {
					if(gender.equals("male") && p.getClient().getGender().equals("male"))
						newList.add(p);
					else if(gender.equals("female") && p.getClient().getGender().equals("female"))
						newList.add(p);
				}
		}
				
		for(PackageDetails p : newList) {
			if(p.getClient() != null){
				if(p.getClient().getDiscontinue().equalsIgnoreCase("false"))
					list.add(new PaymentDataPVO(p.getClient().getName(), p.getPackageName(), p.getPackageFees(), p.getPackageStartDate(), p.getPackageEndDate(), p.getPackagePaymentDate(), p.getClient().getGender(),p.getClientPackageStatus(),p.getClient().getId()));
			}			
		}
		return list;
	}
	
	
	
	
	@Override
	public List<CollectionPVO> getCollectionBy(FilterCollectionObject filter) {
		session = HibernateUtils.getSessionFactory().openSession();
		List<CollectionPVO> collectionPVOList = new ArrayList<CollectionPVO>();
		 Collection<PackageDetails> packagePaymentCollection = new LinkedHashSet(session.createCriteria(PackageDetails.class).add(Restrictions.ne("clientPackageStatus", "not paid")).add(Restrictions.eq("discontinue", "false")).list());
		if(filter.getFilter().equals("GMY")) {
			for(PackageDetails p : packagePaymentCollection) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Calendar c = Calendar.getInstance();
				try{c.setTime(sdf.parse(p.getPackagePaymentDate()));}catch(ParseException e){}	
				
				if(filter.getGender().equalsIgnoreCase("all")){
					if(String.valueOf(c.get(Calendar.MONTH)).equals(filter.getMonth()) && String.valueOf(c.get(Calendar.YEAR)).equals(filter.getYear()))
						collectionPVOList.add(new CollectionPVO(p.getClient().getName(), p.getAmountPaid(), p.getPackageName(), p.getClientPackageStatus(), p.getPackagePaymentDate()));					
				}
				else{
					if(filter.getGender().equals(p.getClient().getGender()) && String.valueOf(c.get(Calendar.MONTH)).equals(filter.getMonth()) && String.valueOf(c.get(Calendar.YEAR)).equals(filter.getYear()))
						collectionPVOList.add(new CollectionPVO(p.getClient().getName(), p.getAmountPaid(), p.getPackageName(), p.getClientPackageStatus(), p.getPackagePaymentDate()));
				}
			}
		}
		else if(filter.getFilter().equals("GD")) {
			for(PackageDetails p : packagePaymentCollection) {
				
				if(filter.getGender().equalsIgnoreCase("all")){
					if(p.getPackagePaymentDate().equals(getDdMmYyyyDate(filter.getDate())))
						collectionPVOList.add(new CollectionPVO(p.getClient().getName(), p.getAmountPaid(), p.getPackageName(), p.getClientPackageStatus(), p.getPackagePaymentDate()));					
				}
				else{
					if(p.getPackagePaymentDate().equals(getDdMmYyyyDate(filter.getDate())) && filter.getGender().equals(p.getClient().getGender()))
						collectionPVOList.add(new CollectionPVO(p.getClient().getName(), p.getAmountPaid(), p.getPackageName(), p.getClientPackageStatus(), p.getPackagePaymentDate()));
				}
			}
		}else if(filter.getFilter().equals("G")) {
			for(PackageDetails p : packagePaymentCollection) {				
				
				if(filter.getGender().equalsIgnoreCase("all")){
						collectionPVOList.add(new CollectionPVO(p.getClient().getName(), p.getAmountPaid(), p.getPackageName(), p.getClientPackageStatus(), p.getPackagePaymentDate()));					
				}
				else{
					if(p.getClient().getGender().equals(filter.getGender()))
						collectionPVOList.add(new CollectionPVO(p.getClient().getName(), p.getAmountPaid(), p.getPackageName(), p.getClientPackageStatus(), p.getPackagePaymentDate()));
				}
			}
		} 
			
		return collectionPVOList;
	}

	@Override
	public CollectionDashboardPVO getDashboardCollection() {
		session = HibernateUtils.getSessionFactory().openSession();
		Collection<PackageDetails> packagePaymentCollection = new LinkedHashSet(session.createCriteria(PackageDetails.class).add(Restrictions.eq("discontinue", "false")).list());
		String currentMonthName;
		String lastMonthName;
		Long enableMembersCount = 0L;
		Long disableMembersCount = 0L;
		
		
		enableMembersCount = (Long)((Criteria)session.createCriteria(Client.class).add( Restrictions.eq("discontinue", "false")).add( Restrictions.eq("profileActiveFlag", "enable")).setProjection(Projections.rowCount())).list().get(0);
		disableMembersCount = (Long)((Criteria)session.createCriteria(Client.class).add( Restrictions.eq("discontinue", "false")).add( Restrictions.eq("profileActiveFlag", "disable")).setProjection(Projections.rowCount())).list().get(0);
		
		Double male=0.00;
		Double female=0.00;
		Double total=0.00;
		Double steam=0.00;
		
		Double currentMonthMaleCollection=0.00;
		Double currentMonthFemaleCollection=0.00;
		Double currentMonthSteamCollection=0.00;
		Double currentMonthTotalCollection=0.00;
		
		Double lastMonthMaleCollection=0.00;
		Double lastMonthFemaleCollection=0.00;
		Double lastMonthSteamCollection=0.00;
		Double lastMonthTotalCollection=0.00;
		
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
		lastMonthName = getMonthName(systemMonth-1);
		if(packagePaymentCollection != null ){
			for(PackageDetails p : packagePaymentCollection) {
				
				if(p.getClient() != null){
					if(p.getClient().getDiscontinue().equalsIgnoreCase("false")){
						if(p.getClient().getGender().equals("male")){
							// collect total amount in male variable
							male = male + p.getAmountPaid();
							
							int packagePaymentMonth = getMonthFromDate(p.getPackagePaymentDate());
							int year = getYearFromDate(p.getPackagePaymentDate());
							// collect current month & last month collection
							if(systemMonth == packagePaymentMonth && systemYear == year)
								currentMonthMaleCollection = currentMonthMaleCollection + p.getAmountPaid();
							else if((systemMonth-1) == packagePaymentMonth && systemYear == year){
								lastMonthMaleCollection = lastMonthMaleCollection + p.getAmountPaid();
							}
							
							
							if(p.getClientPackageStatus().equalsIgnoreCase("fully-paid") && p.getDiscontinue().equalsIgnoreCase("false"))
								maleFullyPaid++;
							else if(p.getClientPackageStatus().equalsIgnoreCase("partial-paid") && p.getDiscontinue().equalsIgnoreCase("false"))
								malePartialPaid++;
							else if(p.getClientPackageStatus().equalsIgnoreCase("not paid") && p.getDiscontinue().equalsIgnoreCase("false"))
								maleNotPaid++;
						}				
						else if(p.getClient().getGender().equals("female")){
							// collect total amount in female variable
							female = female + p.getAmountPaid();
							
							int packagePaymentMonth = getMonthFromDate(p.getPackagePaymentDate());
							int year = getYearFromDate(p.getPackagePaymentDate());
							// collect current month & last month collection
							if(systemMonth == packagePaymentMonth && systemYear == year)
								currentMonthFemaleCollection = currentMonthFemaleCollection + p.getAmountPaid();
							else if((systemMonth-1) == packagePaymentMonth && systemYear == year){
								lastMonthFemaleCollection = lastMonthFemaleCollection + p.getAmountPaid();
							}
							
							if(p.getClientPackageStatus().equalsIgnoreCase("fully-paid") && p.getDiscontinue().equalsIgnoreCase("false"))
								femaleFullyPaid++;
							else if(p.getClientPackageStatus().equalsIgnoreCase("partial-paid") && p.getDiscontinue().equalsIgnoreCase("false"))
								femalePartialPaid++;
							else if(p.getClientPackageStatus().equalsIgnoreCase("not paid") && p.getDiscontinue().equalsIgnoreCase("false"))
								femaleNotPaid++;
						}
					}	
				}	// end if
				
			}// end for
		}
		
		total = male + female;
		currentMonthTotalCollection = currentMonthMaleCollection + currentMonthFemaleCollection + currentMonthSteamCollection;
		lastMonthTotalCollection = lastMonthMaleCollection + lastMonthFemaleCollection + lastMonthSteamCollection;
		
		
		return new CollectionDashboardPVO(male, female, total, steam,
				new LinkedHashSet(session.createCriteria(Client.class).add(Restrictions.eq("discontinue", "false")).add(Restrictions.eq("gender", "male")).list()).size(),
				new LinkedHashSet(session.createCriteria(Client.class).add(Restrictions.eq("discontinue", "false")).add(Restrictions.eq("gender", "female")).list()).size(),
				new LinkedHashSet(session.createCriteria(Client.class).add(Restrictions.eq("discontinue", "false")).list()).size(),
				maleFullyPaid,malePartialPaid,maleNotPaid,
				femaleFullyPaid,femalePartialPaid,femaleNotPaid,
				currentMonthMaleCollection,currentMonthFemaleCollection,currentMonthSteamCollection,currentMonthTotalCollection,
				lastMonthMaleCollection,lastMonthFemaleCollection,lastMonthSteamCollection,lastMonthTotalCollection,
				currentMonthName , lastMonthName,getTodaysBirthdays(),enableMembersCount,disableMembersCount);
		
	}

	@Override
	public void approveTransaction(String txnId) {
		session =  HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		PaymentTransaction txn = (PaymentTransaction) session.get(PaymentTransaction.class, Integer.parseInt(txnId));
		txn.setIsApproved("YES");
		session.save(txn);
		session.getTransaction().commit();
		session.close();
	}
	
	@Override
	public void updateClientAssignedPackage(String u_pkgId, String u_startdate,String u_enddate, String u_fees , User user) {
		session =  HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		PackageDetails pd = (PackageDetails) session.get(PackageDetails.class, Integer.parseInt(u_pkgId));
		pd.setPackageFees(Double.parseDouble(u_fees));
		pd.setPackageStartDate(getDdMmYyyyDate(u_startdate));
		CPackage c = (CPackage) session.load(CPackage.class, pd.getcPackageId());
		//pd.setPackageEndDate(getDdMmYyyyDate(addDaysToDate(u_startdate ,  c.getDays())));
		pd.setPackageEndDate(getDdMmYyyyDate(u_enddate));
		session.save(pd);
		logActivity(session , pd.getClient() , user , ACTIVITY_TYPE_UPDATE_CLIENT_EXISTING_PACKAGE , null);
		session.getTransaction().commit();
		session.close();
	}
	
  
	@Override
	public void deleteClientAssignedPackage(String u_pkgId , User user) {
		session =  HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		PackageDetails pd = (PackageDetails) session.get(PackageDetails.class, Integer.parseInt(u_pkgId));
		pd.setDiscontinue("true");
		session.save(pd);
		logActivity(session , pd.getClient() , user , ACTIVITY_TYPE_DELETE_CLIENT_EXISTING_PACKAGE , null);
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
    	List<Client> list = new ArrayList<Client>();
    	List<ReferenceVO> refList = new ArrayList<ReferenceVO>();
		session = HibernateUtils.getSessionFactory().openSession();
		//session.beginTransaction();
		Criteria crit = session.createCriteria(Client.class);
		crit.add(Restrictions.eq("discontinue","false"));
		list = crit.list();
		//session.close();
		for(Client c : list){
			refList.add(new ReferenceVO(c.getId() , c.getName()));
		}
		return refList;		
    }

    private void logActivity(Session session , Client c , User user , String activity ,String amount) {
    	Notifications noti = null;
    	if(c != null)
		 noti = new Notifications(user.getName(), activity, c.getName(), amount, "false", c.getGender(), c.getId(), new SimpleDateFormat("dd/MM/YYYY hh:mm:ss").format(new Date()));
    	else 
    		noti = new Notifications(user.getName(), activity, "", amount, "false", "", -1, new SimpleDateFormat("dd/MM/YYYY hh:mm:ss").format(new Date()));
		session.save(noti);		
	}
    
    @Override
    public List<Notifications> getNotifications(User user) {
    	List<Notifications> notiList = new ArrayList<Notifications>();
		session = HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria crit = session.createCriteria(Notifications.class);
		if(user.getAuthorizedToApprovePayment().equals("NO"))
			crit.add(Restrictions.eq("trainer","Swati Hadpad"));	
		crit.add(Restrictions.eq("discontinue","false"));
		crit.setMaxResults(50);
		crit.addOrder(Order.desc("id"));
		notiList = crit.list();
		session.close();		
		return notiList;	
    }
    
    @Override
    public void discardNotification(String notiId) {
    	session =  HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		Notifications noti = (Notifications) session.get(Notifications.class, Integer.parseInt(notiId));
		noti.setDiscontinue("true");
		session.save(noti);
		session.getTransaction().commit();
		session.close();  	
    }
    
    @Override
    public void submitFemaleAditionalDataForm(FemaleMemberAdditionalDataVO femaleMemberAdditionalDataVO, User u) {
    
    	session =  HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		String isAuth = "NO";
		if(u.getAuthorizedToApprovePayment().equalsIgnoreCase("true"))
			isAuth= "YES";
	femaleMemberAdditionalDataVO.setDate(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
	Calendar mCalendar = Calendar.getInstance();    
	String month = mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
	femaleMemberAdditionalDataVO.setMonth(month);
	femaleMemberAdditionalDataVO.setDiscontinue("false");	
	
	session.save(femaleMemberAdditionalDataVO);
	session.getTransaction().commit();
	session.close();
    }
    
    @Override
    public List<FemaleMemberAdditionalDataVO> getFemaleAditionalDataListByClientId(int clientId) {
		session = HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		Collection<FemaleMemberAdditionalDataVO> list = new LinkedHashSet(session.createCriteria(FemaleMemberAdditionalDataVO.class)
				.add(Restrictions.eq("clientId", clientId))
				.add(Restrictions.eq("discontinue","false"))
				.addOrder(Order.desc("id"))
				.list());
		session.getTransaction().commit();
	    return new ArrayList<FemaleMemberAdditionalDataVO>( list ); 
    }
    
    @Override
    public void deleteClientProfile(String clientid, User user) {
    	session =  HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		Client client = (Client) session.get(Client.class, Integer.parseInt(clientid));
		//client.setDiscontinue("true");
		for(PackageDetails pd : client.getPackageDetails()){
			pd.setDiscontinue("true");
			for(PaymentTransaction ptxn : pd.getPaymentTransactions()){
				ptxn.setDiscontinue("true");
			}
		}
		client.setDiscontinue("true");
		session.save(client);
		logActivity(session , client , user , ACTIVITY_TYPE_DELETE_CLIENT_PROFILE , null);
		session.getTransaction().commit();
		session.close();
    }
    
    @Override
    public void deletePackage(String pkgid, User user) {
    	session =  HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		CPackage pd = (CPackage) session.get(CPackage.class, Integer.parseInt(pkgid));
		pd.setDiscontinue("true");
		session.save(pd);
		//logActivity(session ,null , user , ACTIVITY_TYPE_DELETE_CLIENT_PROFILE , null);
		session.getTransaction().commit();
		session.close();    	
    }
    
    @Override
    public void deleteFemaleClientAdditionalDetails(String id, User u) {
    	session =  HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		FemaleMemberAdditionalDataVO vo = (FemaleMemberAdditionalDataVO) session.get(FemaleMemberAdditionalDataVO.class, Integer.parseInt(id));
		vo.setDiscontinue("true");
		session.save(vo);
		//logActivity(session ,null , user , ACTIVITY_TYPE_DELETE_CLIENT_PROFILE , null);
		session.getTransaction().commit();
		session.close();
    }
    
    @Override
    public List<MemberStatPVO> getMembersBySearchCriteria(String searchCriteria) {
    	String zone = "none";
    	String filter= "all";
    	session = HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria crit = session.createCriteria(Client.class).add(Restrictions.eq("discontinue", "false")).add(Restrictions.like("name", searchCriteria,MatchMode.ANYWHERE));
		Collection<Client> collection = new LinkedHashSet(crit.list());
		List<Client> cList = new ArrayList<Client>();
		for(Client c : collection) {
			cList.add(c);
		}
		List<MemberStatPVO> membersStatPVOs = new ArrayList<>();
		for(Client c : cList){
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
			if(!session.isOpen())
				HibernateUtils.getSessionFactory().openSession();
			PackageDetails pd = ((PackageDetails)c1.uniqueResult());
			if(pd != null){
				m.setPaymentStatus(pd.getClientPackageStatus());
				try {					
					Date firstDate=new SimpleDateFormat("dd/MM/yyyy").parse(pd.getPackageStartDate());
					Date secondDate = new SimpleDateFormat("dd/MM/yyyy").parse(pd.getPackageEndDate());
					m.setDaysRemaining(String.valueOf(daysDiff(new Date(), secondDate)));
					m.setPackageDuration(pd.getPackageStartDate()+" - "+pd.getPackageEndDate());
					m.setPackageName(pd.getPackageName());
					m.setFeesPaid(String.valueOf(pd.getAmountPaid()));
					m.setPackageTotalFees(String.valueOf(pd.getPackageFees()));
					m.setPendingFees(String.valueOf(pd.getPackageFees()-pd.getAmountPaid()));	
					m.setPackagePaymentStatus(pd.getClientPackageStatus());
				} catch (ParseException e) {
					e.printStackTrace();
				}	
			}
			else{
				m.setDaysRemaining("-");
				m.setPaymentStatus("-");
			}
			
			if(m.getDaysRemaining().equals("-"))
				m.setColor(GREEN);
			else{
				//if(Integer.parseInt(m.getDaysRemaining())<5 && isFeesNotPaid(c))
				if(Integer.parseInt(m.getDaysRemaining()) < 0)
					m.setColor(RED);
				else if(Integer.parseInt(m.getDaysRemaining()) >= 0 && Integer.parseInt(m.getDaysRemaining()) <= 5)
					m.setColor(YELLOW);
				else
					m.setColor(GREEN);	
			}	
			
			membersStatPVOs.add(m);
		}
		//session.beginTransaction();
		//session.getTransaction().commit();
		session.close();
		List<MemberStatPVO> membersStatPVOByZones = null;
		
		if(zone.equalsIgnoreCase("none")){
			membersStatPVOByZones = membersStatPVOs;
		}
		  else if(zone.equalsIgnoreCase("green")){
			  membersStatPVOByZones = getMembersByZones(membersStatPVOs,GREEN);  
		  }
		  else if(zone.equalsIgnoreCase("red")){
			  membersStatPVOByZones = getMembersByZones(membersStatPVOs,RED);
		  }
		  else if(zone.equalsIgnoreCase("yellow")){
			  membersStatPVOByZones = getMembersByZones(membersStatPVOs,YELLOW);
		  }
		return membersStatPVOByZones;
    }
    
    @Override
    public List<Notifications> getMobileNotifications() {
    	List<Notifications> notiList = new ArrayList<Notifications>();
		session = HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria crit = session.createCriteria(Notifications.class);
		crit.add(Restrictions.eq("discontinue","false"));
		crit.addOrder(Order.desc("id"));
		notiList = crit.list();
		session.close();		
		List<Notifications> mobileNotification = new ArrayList<Notifications>();
		for(Notifications n : notiList){
			if(n.getActivity().equalsIgnoreCase(ACTIVITY_TYPE_NEW_PAYMENT) || 
					n.getActivity().equalsIgnoreCase(ACTIVITY_TYPE_ADD_NEW_MEMBER)){
				mobileNotification.add(n);
			}
		}
		return mobileNotification;
    }
    
    @Override
    public void sendPendingInvoices() {
    	List<EmailDataObject> pendingInvoiceList = new ArrayList<EmailDataObject>();
		session = HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		FlagData emailInvoiceFlag = (FlagData) session.createCriteria(FlagData.class).add(Restrictions.eq("flagKey", "EMAIL_INVOICE_FLAG")).uniqueResult();
		Criteria crit = session.createCriteria(EmailDataObject.class);
		crit.add(Restrictions.eq("isReceiptSent","FALSE"));
		pendingInvoiceList = crit.list();
		for(EmailDataObject obj : pendingInvoiceList){
			if(emailInvoiceFlag.getFlagValue().equalsIgnoreCase("TRUE")){
				taskExecutor.execute(new Runnable() {  
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
    }
    
    @Override
    public void sendInvoice(String txnId,String email) {
    	List<EmailDataObject> pendingInvoiceList = new ArrayList<EmailDataObject>();
		session = HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		FlagData emailInvoiceFlag = (FlagData) session.createCriteria(FlagData.class).add(Restrictions.eq("flagKey", "EMAIL_INVOICE_FLAG")).uniqueResult();
		EmailDataObject eObj = (EmailDataObject)session.createCriteria(EmailDataObject.class).add(Restrictions.eq("paymentTransactionId",Integer.parseInt(txnId))).uniqueResult();
		eObj.setClientEmail(email);
		
		if(emailInvoiceFlag.getFlagValue().equalsIgnoreCase("TRUE")){
			taskExecutor.execute(new Runnable() {  
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
    
    @Override
    public String getToggleInvoiceFlag() {
		session = HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		String returnvalue ="";
		FlagData flag = (FlagData)session.createCriteria(FlagData.class).add(Restrictions.eq("flagKey","EMAIL_INVOICE_FLAG")).uniqueResult();
		if(flag.getFlagValue().equalsIgnoreCase("TRUE"))
			returnvalue= "ON";
		else if(flag.getFlagValue().equalsIgnoreCase("FALSE"))
			returnvalue ="OFF";
		session.close();
		return returnvalue;
    }
    
    @Override
    public void updateToggleInvoiceFlag(String flag) {
    	List<EmailDataObject> pendingInvoiceList = new ArrayList<EmailDataObject>();
		session = HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		FlagData flagObj = (FlagData)session.createCriteria(FlagData.class).add(Restrictions.eq("flagKey","EMAIL_INVOICE_FLAG")).uniqueResult();
		if(flag.equalsIgnoreCase("true"))
			flagObj.setFlagValue("TRUE");
		else if(flag.equalsIgnoreCase("false"))
			flagObj.setFlagValue("FALSE");
		session.getTransaction().commit();
		session.close();
    }
    
    public int getMonthFromDate(String dt){
    	if(dt == null || dt.equals(""))
    		return -1;
	    DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy").withLocale(Locale.UK);
	    LocalDate date = formatter.parseLocalDate(dt);
	    return date.getMonthOfYear();	    
    }
    
    public int getYearFromDate(String dt){
    	if(dt == null || dt.equals(""))
    		return -1;
	    DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy").withLocale(Locale.UK);
	    LocalDate date = formatter.parseLocalDate(dt);
	    return date.getYear();  
    }
    
    public String getMonthName(int monthId){
    	String month = null;
    	if(monthId == 1)
    		month = "January";
    	if(monthId == 2)
    		month = "February";
    	if(monthId == 3)
    		month = "March";
    	if(monthId == 4)
    		month = "April";
    	if(monthId == 5)
    		month = "May";
    	if(monthId == 6)
    		month = "June";
    	if(monthId == 7)
    		month = "July";
    	if(monthId == 8)
    		month = "August";
    	if(monthId == 9)
    		month = "September";
    	if(monthId == 10)
    		month = "October";
    	if(monthId == 11)
    		month = "November";
    	if(monthId == 12)
    		month = "December";
    	
    	return month;
    }

	@Override
	public String getReferralName(String cliendId) {
		String space = "\\n";
		String referrals = "\'";
		List<Client> pkgList = new ArrayList<Client>();
		session = HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		Collection<Client> clients = new LinkedHashSet(session.createCriteria(Client.class)
				.add(Restrictions.eq("reference", cliendId))
				.list());
		for(Client  c: clients) {
			referrals = referrals + c.getName()+space;
		}
		referrals = referrals + "\'";
	    session.getTransaction().commit();
	    return referrals;
	}
	
	@Override
	public void redeemReferPoints(String clientid) {
		session =  HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		Client client = (Client) session.get(Client.class, Integer.parseInt(clientid));
		client.setReferPoints("0");
		session.save(client);
		session.getTransaction().commit();
		session.close();		
	}
	
	@Override
	public void updateProfileActiveFlag(String clientid, String selectflag) {
		session =  HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		Client client = (Client) session.get(Client.class, Integer.parseInt(clientid));
		client.setProfileActiveFlag(selectflag);
		session.save(client);
		session.getTransaction().commit();
		session.close();		
	}
	
	@Override
	public void triggerEnableDisableProfileBatch() {
		session = HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria crit = session.createCriteria(Client.class).add(Restrictions.eq("discontinue", "false"));
		Collection<Client> collection = new LinkedHashSet(crit.list());
		List<Client> cList = new ArrayList<>();
		cList.addAll(collection);
		
		// creating display object
		for(Client c : cList){
			
			// get recent package record and set days remaining
			Criteria c1 = session.createCriteria(PackageDetails.class);
			c1.add(Restrictions.eq("client.id", c.getId()));
			c1.add(Restrictions.eq("discontinue", "false"));
			c1.addOrder(Order.desc("id"));
			c1.setMaxResults(1);
			
			if(!session.isOpen())
				HibernateUtils.getSessionFactory().openSession();
			PackageDetails pd = ((PackageDetails)c1.uniqueResult());
			
			if(pd != null){
				try {		
					if(pd.getPackageStartDate() != null && pd.getPackageEndDate() != null){
						Date firstDate=new SimpleDateFormat("dd/MM/yyyy").parse(pd.getPackageStartDate());
						Date secondDate = new SimpleDateFormat("dd/MM/yyyy").parse(pd.getPackageEndDate());
						int daysLeft = daysDiff(new Date(), secondDate);
						if(daysLeft < -31){
							c.setProfileActiveFlag("disable");
							session.saveOrUpdate(c);
						}	
					}					
				} catch (ParseException e) {}	
			}
			else{}
		}
		session.getTransaction().commit();
		session.close();
	}
	
	@Override
	public void sendFeesReminder(String clientid) {
		session = HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		
		// get recent package record and set days remaining
					Criteria c1 = session.createCriteria(PackageDetails.class);
					c1.add(Restrictions.eq("client.id", clientid));
					c1.add(Restrictions.eq("discontinue", "false"));
					c1.addOrder(Order.desc("id"));
					c1.setMaxResults(1);
					
					if(!session.isOpen())
						HibernateUtils.getSessionFactory().openSession();
					PackageDetails pd = ((PackageDetails)c1.uniqueResult());
					
					if(pd != null){
						String packageStatus = pd.getClientPackageStatus().toUpperCase();
						try {					
							Date firstDate=new SimpleDateFormat("dd/MM/yyyy").parse(pd.getPackageStartDate());
							Date secondDate = new SimpleDateFormat("dd/MM/yyyy").parse(pd.getPackageEndDate());
							String daysRemaining = String.valueOf(daysDiff(new Date(), secondDate));
						} catch (ParseException e) {}	
					}
					else{
						// no days
					}
	}
	
	public List<String> getTodaysBirthdays(){
		String todaysDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		java.time.LocalDate today = java.time.LocalDate.now();
		int systemMonth = today.getMonthValue();
		int systemDate = today.getDayOfMonth();
		
		Collection clients = new LinkedHashSet(session.createCriteria(Client.class)
				.add(Restrictions.eq("discontinue", "false"))
				.list());
		List<Client> list = new ArrayList<>();
		List<String> listNames = new ArrayList<>();
		list.addAll(clients);
		for(Client c : list){
			Date bday;
			try {
				bday = new SimpleDateFormat("dd/MM/yyyy").parse(c.getBirthDate());
				int month_ = bday.getMonth()+1;
				int date = bday.getDate();
				if(date == systemDate && month_ == systemMonth)
				listNames.add(c.getName());
			} catch (ParseException e) {}
		}	
		return listNames;
	}
	
	@Override
	public void sendBdayWish(String name) {
		Session session =  HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		Client c = (Client)session.createCriteria(Client.class).add(Restrictions.eq("name", name)).uniqueResult();
		taskExecutor.execute(new Runnable() {  
			   @Override  
			   public void run() { BdayEmailTemplate.sendEmail2(c.getEmail(),name); } }); 
		session.getTransaction().commit();
	}
	
	@Override
	public void createNewEmail(String emailSubject, String filter,String image) {
		
		List<String> emailsList = new ArrayList<>();
		Session session= HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria crit = session.createCriteria(Client.class);
		if(filter.equals("sendToEnable"))
			crit.add(Restrictions.eq("profileActiveFlag", "enable"));
		if(filter.equals("sendToDisable"))
			crit.add(Restrictions.eq("profileActiveFlag", "disable"));
		if(filter.equals("sendToEnableDisable"))
			crit.add(Restrictions.eq("discontinue", "false"));
		
		Collection<Client> collection = new LinkedHashSet(crit.list());
		List<Client> cList = new ArrayList<>();
		cList.addAll(collection);
		int counter = 0;
		for(Client c : cList){
			taskExecutor.execute(new Runnable() 
			{  
				   @Override  
				   public void run() { 
					   PromotionalEmailTemplate.sendEmail(c.getEmail(),c.getName() ,emailSubject, image);				    
				   } 
			}); 
		}		 
	}
	
	@Override
	public void createNewSms(String smsContent, String filter) {
		System.out.println("method called: createNewSms");
		if(getSmsFlag().equalsIgnoreCase("ON")){
			taskExecutor.execute(new Runnable() 
			{  
				   @Override  
				   public void run() {
					   
			Session session= HibernateUtils.getSessionFactory().openSession();
			session.beginTransaction();
			Criteria crit = session.createCriteria(Client.class);
			if(filter.equals("sendToEnable"))
				crit.add(Restrictions.eq("profileActiveFlag", "enable"));
			if(filter.equals("sendToDisable"))
				crit.add(Restrictions.eq("profileActiveFlag", "disable"));
			if(filter.equals("sendToEnableDisable"))
				crit.add(Restrictions.eq("discontinue", "false"));
			
			Collection<Client> collection = new LinkedHashSet(crit.list());
			List<Client> cList = new ArrayList<>();
			cList.addAll(collection);
			int counter = 0;
			for(Client c : cList){
				String mobile = c.getMobile();
				if(mobile != null && mobile.length() == 10){
					System.out.println("members found :"+cList.size());
					String body = "Hi "+c.getName()+"\n"+smsContent;
					   SendSmsUtil.triggerSms(c.getMobile(), body);
					   SmsLogs smsLogs = new SmsLogs(c.getName(),c.getMobile(),body,String.valueOf(new Date()));
					   session.save(smsLogs);
					 
							   
							   
						   
				}			 
			}//end for
			session.getTransaction().commit();
			
				   } 
			});	
		}		
	}
	
	@Override
	public String getSmsFlag() {
		session = HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		String returnvalue ="";
		FlagData flag = (FlagData)session.createCriteria(FlagData.class).add(Restrictions.eq("flagKey","SMS_FLAG")).uniqueResult();
		if(flag.getFlagValue().equalsIgnoreCase("TRUE"))
			returnvalue= "ON";
		else if(flag.getFlagValue().equalsIgnoreCase("FALSE"))
			returnvalue ="OFF";
		return returnvalue;
	}
	
	public Boolean checkIsBatchCompleted(String batchName , String date) {
		session = HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		String returnvalue ="";
		BatchLogs batch = (BatchLogs)session.createCriteria(BatchLogs.class).add(Restrictions.eq("batchName",batchName)).add(Restrictions.eq("date", date)).uniqueResult();
		return batch != null ? true : false;
	}
	
	
	@Override
	public void updateSmsFlag(String flag) {
		session = HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		FlagData flagObj = (FlagData)session.createCriteria(FlagData.class).add(Restrictions.eq("flagKey","SMS_FLAG")).uniqueResult();
		if(flag.equalsIgnoreCase("true"))
			flagObj.setFlagValue("TRUE");
		else if(flag.equalsIgnoreCase("false"))
			flagObj.setFlagValue("FALSE");
		session.saveOrUpdate(flagObj);
		session.getTransaction().commit();
	}
		
	@Override
	public void sendReminderToSingleClient(String clientname, String clientid, String daysLeft, String packageName,
			String packageDuration, String pendingFees, String feesPaid, String packageTotalFees) {
		System.out.println("method : sendReminderToSingleClient");
		String emailFlag = getToggleInvoiceFlag();
		String smsFlag = getSmsFlag();
		Client c = (Client)getClientById(Integer.parseInt(clientid));
		String messageLine = "Kindly pay your pending fees";
		if(emailFlag.equalsIgnoreCase("ON")){
			// send email reminder
			String email = c.getEmail();
			if(email.contains("@"));{
				String subject = "ProGym Fee's reminder";
				taskExecutor.execute(new Runnable() 
				{  
					   @Override  
					   public void run() { 
						   FeesReminderEmail.sendEmail(c.getEmail(), subject, c.getName(), packageName, packageDuration, daysLeft, feesPaid, pendingFees,packageTotalFees,messageLine);				    
					   } 
				});				
			}
		}
		
		String body = "PRO GYM KOP\n\nHi "+c.getName()+"\nYour package <"+packageName+"> will expire in "+daysLeft+" days \nPlease pay your pending fees of Rs."+pendingFees+" by click on this link mpay.me/8796655176";
		// send sms reminder
		
		
		if(smsFlag.equalsIgnoreCase("ON")){
			String number = c.getMobile();
			if(number != null && number.length() == 10){				 
				session = HibernateUtils.getSessionFactory().openSession();
				   session.beginTransaction();  
				   SendSmsUtil.triggerSms(number, body);
				   SmsLogs smsLogs = new SmsLogs(c.getName(),c.getMobile(),body,String.valueOf(new Date()));						   
				   session.save(smsLogs);
				   session.getTransaction().commit();
				
			}	
		}		
	}
	
	@Override
	public void triggerFeesPaymentReminderBatch(){
		
		System.out.println("Batch Started : triggerFeesPaymentReminderBatch");
		List<EmailPVO> emailPVOList = new ArrayList<>();
		String todaysDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		String batchName ="FEES_REMINDER_BATCH"; 
		List<SmsLogs> smsLogsList = new ArrayList<>();
		if(!checkIsBatchCompleted(batchName, todaysDate)){
			List<MemberStatPVO> memberStatPVOs = getMembersBy("all", "none", "enable");
			for(MemberStatPVO m : memberStatPVOs){
				String packagePaymentStatus = m.getPackagePaymentStatus();
				if(getToggleInvoiceFlag().equalsIgnoreCase("ON")){
					String email = m.getEmail();
					if(email.contains("@"));{
						String subject = "ProGym Fee's reminder";
						if(!m.getDaysRemaining().equalsIgnoreCase("-")){
							if(Integer.parseInt(m.getDaysRemaining()) > 0 && Integer.parseInt(m.getDaysRemaining()) <= 5){
							   	   String messageLine ="";
									   if(!packagePaymentStatus.equalsIgnoreCase("fully-paid"))
										   messageLine = "Kindly pay your pending fees";
									   else
										   messageLine = "PRO GYM KOP \n\nYour package will expire in "+m.getDaysRemaining();
								emailPVOList.add(new EmailPVO(m.getEmail(), subject, m.getName(), m.getPackageName(), m.getPackageDuration(), m.getDaysRemaining(), m.getFeesPaid(), m.getPendingFees(),m.getPackageTotalFees(),messageLine));
								   
							}
						   else if(Integer.parseInt(m.getDaysRemaining()) < 0 && Integer.parseInt(m.getDaysRemaining()) > -8)
							{ 
								final String messageLine  = "PRO GYM KOP \n\nYour gym package is expired , Please renew your packages & pay if you have any pending fees by click on this link mpay.me/8796655176";
								emailPVOList.add(new EmailPVO(m.getEmail(), subject, m.getName(), m.getPackageName(), m.getPackageDuration(), m.getDaysRemaining(), m.getFeesPaid(), m.getPendingFees(),m.getPackageTotalFees(),messageLine));										
							}
						}
					}	
				}// end toggle check	
			}// end for				
			
	taskExecutor.execute(new Runnable() 
	{
				
		@Override  
		public void run() {
			for(MemberStatPVO m : memberStatPVOs){				
				if(getSmsFlag().equalsIgnoreCase("ON")){
					String body = "Hi "+m.getName()+"\nYour package <"+m.getPackageName()+"> will expire in "+m.getDaysRemaining()+" days \nPlease pay your pending fees of Rs."+m.getPendingFees()+"\n\nfrom - ProGym Kolhapur.";
					String number = m.getMobile();
					if(number != null && number.length() == 10){
						System.out.println("sms sent :"+number);
						
								   SendSmsUtil.triggerSms(number, body);
								   smsLogsList.add(new SmsLogs(m.getName(),m.getMobile(),body,String.valueOf(new Date())));
					}
				}			
						
		}// end method
			session = HibernateUtils.getSessionFactory().openSession();
			session.beginTransaction();
			for(SmsLogs s : smsLogsList){
				session.save(s);
			}
			session.getTransaction().commit();
			updateBatchCompletedStatus(batchName,todaysDate);
			
			} 
				});// end taskexecuter 2
		}// end if	
		
		// send emails
		System.out.println("email pvo size"+emailPVOList.size());
		sendEmails(emailPVOList);
		System.out.println("Batch finished : triggerFeesPaymentReminderBatch");
			   
			   
	}
	
	public void sendEmails(List<EmailPVO> emailPVOList){		
		for(EmailPVO m : emailPVOList){
			System.out.println("sending email to :"+m.getEmail());
			taskExecutor.execute(new Runnable() 
			{  
				   @Override  
				   public void run() {
					   System.out.println("sending payment reminder email :"+m.getEmail());
					   FeesReminderEmail.sendEmail(m.getEmail(), m.getSubject(), m.getName(), m.getPackageName(), m.getPackageDuration(), m.getDaysRemaining(), m.getFeesPaid(), m.getPendingFees(),m.getPackageTotalFees(),m.getMessageLine());
				   }
			});	
		}		
	}
	
	

	private void updateBatchCompletedStatus(String batchName, String todaysDate) {
		session = HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		BatchLogs batchObject = new BatchLogs(batchName,todaysDate,"completed");
		session.save(batchObject);	
		session.getTransaction().commit();
	}
	
	@Override
	public List<SmsLogs> getSmsLogs() {
		List<SmsLogs> smsList = new ArrayList<SmsLogs>();
		session = HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria crit = session.createCriteria(SmsLogs.class);
		crit.setMaxResults(50);
		crit.addOrder(Order.desc("id"));
		smsList = crit.list();
		session.getTransaction().commit();
		return smsList;
	}
	
	@Override
	public void renewPackage(String clientid , User user) {
		// get current list & add to existing list - pkgdetails
				// 
				session = HibernateUtils.getSessionFactory().openSession();
				session.beginTransaction();
				
				Criteria c1 = session.createCriteria(PackageDetails.class);
				c1.add(Restrictions.eq("client.id", Integer.parseInt(clientid)));
				c1.add(Restrictions.eq("discontinue", "false"));
				c1.addOrder(Order.desc("id"));
				c1.setMaxResults(1);
				PackageDetails pd = ((PackageDetails)c1.uniqueResult());
				System.out.println("pkg found :"+pd.getPackageStartDate());
				System.out.println("pkg found :"+pd.getPackageEndDate());
				
				
				PackageDetails pd1 = new PackageDetails();
				pd1.setClientPackageStatus("not paid");
				CPackage c = (CPackage) session.load(CPackage.class, pd.getcPackageId());
				System.out.println("new start date"+addDaysToDateToddmmyyyy(pd.getPackageEndDate(), 1));
				pd1.setPackageStartDate(addDaysToDateToddmmyyyy(pd.getPackageEndDate(), 1));
				pd1.setPackageEndDate(addDaysToDateToddmmyyyy(pd1.getPackageStartDate() ,  c.getDays()));
				pd1.setcPackageId(pd.getcPackageId());
				pd1.setDiscontinue("false");
				pd1.setAmountPaid(0.0);	
				pd1.setPackageName(c.getPackageName());
				pd1.setPackageFees(c.getFees());
				
				Client client = getClientById(Integer.parseInt(clientid));
				pd1.setClient(client);
				session.save(pd1);
				logActivity(session , client , user, ACTIVITY_TYPE_ASSIGN_PACKAGE_TO_CLIENT , String.valueOf(pd1.getPackageFees()));
				session.getTransaction().commit();
				//session.close();
	}
	
	public String addDaysToDate(String oldDate , int daysToAdd) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		try{
		   //Setting the date to the given date
		   c.setTime(sdf.parse(oldDate));
		}catch(ParseException e){
			e.printStackTrace();
		 }
		   
		//Number of Days to add
		c.add(Calendar.DAY_OF_MONTH, daysToAdd);  
		//Date after adding the days to the given date
		String newDate = sdf.format(c.getTime());
		
		return newDate;
	}
	
	public String addDaysToDateToddmmyyyy(String oldDate , int daysToAdd) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		try{
		   //Setting the date to the given date
		   c.setTime(sdf.parse(oldDate));
		}catch(ParseException e){
			e.printStackTrace();
		 }
		   
		//Number of Days to add
		c.add(Calendar.DAY_OF_MONTH, daysToAdd);  
		//Date after adding the days to the given date
		String newDate = sdf.format(c.getTime());
		
		return newDate;
	}
	
	public String getDdMmYyyyDate(String oldDate){
		if(oldDate.equals(""))
			return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		try{
		   //Setting the date to the given date
		   c.setTime(sdf.parse(oldDate));
		}catch(ParseException e){
			e.printStackTrace();
		 }
		
		return new SimpleDateFormat("dd/MM/yyyy").format(c.getTime());
	}

	@Override
	public void updatePhotoInfo(Integer cid, String uploadedImagePath) {
		session =  HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		Client client = (Client) session.get(Client.class, cid);
		client.setPhoto(uploadedImagePath);
		session.save(client);
		session.getTransaction().commit();
		session.close();
	}
}
 

