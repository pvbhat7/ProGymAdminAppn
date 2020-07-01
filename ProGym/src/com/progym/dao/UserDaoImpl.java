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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.progym.model.AddClientPackageForm;
import com.progym.model.AddMemberObject;
import com.progym.model.AddPackageObject;
import com.progym.model.CPackage;
import com.progym.model.Client;
import com.progym.model.CollectionDashboardPVO;
import com.progym.model.CollectionPVO;
import com.progym.model.FemaleMemberAdditionalDataVO;
import com.progym.model.FilterCollectionObject;
import com.progym.model.MemberStatPVO;
import com.progym.model.Notifications;
import com.progym.model.User;
import com.progym.model.PackageDetails;
import com.progym.model.PaymentDataPVO;
import com.progym.model.PaymentTransaction;
import com.progym.model.ReferenceVO;
import com.progym.utils.HibernateUtils;

@Repository
public class UserDaoImpl implements UserDao {
	
    public static final long MILLISECONDS_IN_DAY = (long) (1000 * 60 * 60 * 24);
    public static final String RED = "#FF0000";
    public static final String YELLOW = "#FCFF00";
    public static final String GREEN = "#1CFF00";
    public static final String ACTIVITY_TYPE_ADD_NEW_MEMBER = "Add New Client";
    public static final String ACTIVITY_TYPE_ADD_NEW_PACKAGE = "Create New Package";
    public static final String ACTIVITY_TYPE_ASSIGN_PACKAGE_TO_CLIENT = "Assign New Package";
    public static final String ACTIVITY_TYPE_NEW_PAYMENT = "New Payment";
    public static final String ACTIVITY_TYPE_UPDATE_CLIENT_EXISTING_PACKAGE = "Update Package";
    public static final String ACTIVITY_TYPE_DELETE_CLIENT_EXISTING_PACKAGE = "Delete Package";
    public static final String ACTIVITY_TYPE_DELETE_CLIENT_PROFILE = "Delete Client Profile";

	
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
			User u1 = new User("a", "a", "Pranav Patil","YES");
			  User u2 = new User("b", "b","Swati HadPad", "NO");
			  session.save(u1);
			  session.save(u2);
			  session.getTransaction().commit();
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
		
		logActivity(session , c , u , ACTIVITY_TYPE_ADD_NEW_MEMBER , null);		
		session.getTransaction().commit();
    }

	@Override
	public void addMemberToDatabase(AddMemberObject addMemberObject , User user, String userType) {
		String refererId = addMemberObject.getReference();
		session = HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		Client c = new Client(addMemberObject.getName(), addMemberObject.getMobile(), addMemberObject.getGender(), getDdMmYyyyDate(addMemberObject.getBirthDate()), addMemberObject.getRemarks(),"false",null,"0"
				,addMemberObject.getEmail() , addMemberObject.getAddress() , addMemberObject.getBloodGroup() ,addMemberObject.getReference(), addMemberObject.getPreviousGym());
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

	public List<MemberStatPVO> getMembersBy(String filter , String zone) {
		session = HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria crit = session.createCriteria(Client.class).add(Restrictions.eq("discontinue", "false"));
		Collection<Client> collection = new LinkedHashSet(crit.list());
		
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
		List<MemberStatPVO> membersStatPVOs = new ArrayList<>();
		for(Client c : cList){
			MemberStatPVO m = new MemberStatPVO();
			m.setId(c.getId());
			m.setName(c.getName());
			m.setGender(c.getGender());
			m.setReferPoints(c.getReferPoints());
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
				try {					
					Date firstDate=new SimpleDateFormat("dd/MM/yyyy").parse(pd.getPackageStartDate());
					Date secondDate = new SimpleDateFormat("dd/MM/yyyy").parse(pd.getPackageEndDate());
					m.setDaysRemaining(String.valueOf(daysDiff(new Date(), secondDate)));
				} catch (ParseException e) {
					e.printStackTrace();
				}	
			}
			else{
				m.setDaysRemaining("-");
			}
			
			if(m.getDaysRemaining().equals("-"))
				m.setColor(GREEN);
			else{
				if(Integer.parseInt(m.getDaysRemaining())<5 && isFeesNotPaid(c))
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
		session =  HibernateUtils.getSessionFactory().openSession();
			session.beginTransaction();
			String isAuth = "NO";
			if(isAuthorized)
				isAuth= "YES";
			
		PaymentTransaction p = new PaymentTransaction(new SimpleDateFormat("dd/MM/yyyy").format(new Date()), paymentTransaction.getPackageDetailsId(), paymentTransaction.getFeesPaid(),isAuth);
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
	
	public String getDdMmYyyyDate(String oldDate){
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
	public List<CollectionPVO> getCollectionBy(FilterCollectionObject filter) {
		session = HibernateUtils.getSessionFactory().openSession();
		List<CollectionPVO> collectionPVOList = new ArrayList<CollectionPVO>();
		 Collection<PackageDetails> packagePaymentCollection = new LinkedHashSet(session.createCriteria(PackageDetails.class).add(Restrictions.ne("clientPackageStatus", "not paid")).list());
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
		Double male=0.00;
		Double female=0.00;
		Double total=0.00;
		Double steam=0.00;
		int maleFullyPaid = 0;
		int malePartialPaid = 0;
		int maleNotPaid = 0;
		int femaleFullyPaid = 0;
		int femalePartialPaid = 0;
		int femaleNotPaid = 0;
		if(packagePaymentCollection != null ){
			for(PackageDetails p : packagePaymentCollection) {
				
				if(p.getClient() != null){
					if(p.getClient().getDiscontinue().equalsIgnoreCase("false")){
						if(p.getClient().getGender().equals("male")){
							male = male + p.getAmountPaid();
							if(p.getClientPackageStatus().equalsIgnoreCase("fully-paid") && p.getDiscontinue().equalsIgnoreCase("false"))
								maleFullyPaid++;
							else if(p.getClientPackageStatus().equalsIgnoreCase("partial-paid") && p.getDiscontinue().equalsIgnoreCase("false"))
								malePartialPaid++;
							else if(p.getClientPackageStatus().equalsIgnoreCase("not paid") && p.getDiscontinue().equalsIgnoreCase("false"))
								maleNotPaid++;
						}				
						else if(p.getClient().getGender().equals("female")){
							female = female + p.getAmountPaid();
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
		
		
		return new CollectionDashboardPVO(male, female, total, steam,
				new LinkedHashSet(session.createCriteria(Client.class).add(Restrictions.eq("discontinue", "false")).add(Restrictions.eq("gender", "male")).list()).size(),
				new LinkedHashSet(session.createCriteria(Client.class).add(Restrictions.eq("discontinue", "false")).add(Restrictions.eq("gender", "female")).list()).size(),
				new LinkedHashSet(session.createCriteria(Client.class).add(Restrictions.eq("discontinue", "false")).list()).size(),
				maleFullyPaid,malePartialPaid,maleNotPaid,
				femaleFullyPaid,femalePartialPaid,femaleNotPaid
				);
		
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
		session.beginTransaction();
		Criteria crit = session.createCriteria(Client.class);
		crit.add(Restrictions.eq("discontinue","false"));
		list = crit.list();
		session.close();
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
    public List<Notifications> getNotifications() {
    	List<Notifications> notiList = new ArrayList<Notifications>();
		session = HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria crit = session.createCriteria(Notifications.class);
		crit.add(Restrictions.eq("discontinue","false"));
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
}
 

