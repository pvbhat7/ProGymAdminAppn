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
import java.util.Set;

import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
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
import com.progym.model.FilterCollectionObject;
import com.progym.model.User;
import com.progym.model.PackageDetails;
import com.progym.model.PaymentDataPVO;
import com.progym.model.PaymentTransaction;
import com.progym.utils.HibernateUtils;

@Repository
public class UserDaoImpl implements UserDao {
	
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
			  User u2 = new User("b", "b","Pooja Patil", "NO");
			  session.save(u1);
			  session.save(u2);	
		}
	  session.getTransaction().commit();	  
    }
  
    public User validateUser(User user) {
    	session = HibernateUtils.getSessionFactory().openSession();
  	  session.beginTransaction();  
  return (User)session.createCriteria(User.class).add(Restrictions.eq("password", user.getPassword())).add(Restrictions.eq("username", user.getUsername())).uniqueResult();
  	
  			}

	@Override
	public void addMemberToDatabase(AddMemberObject addMemberObject) {
		session = getSession();
		if(!session.getTransaction().isActive())
		session.beginTransaction();
		Client c = new Client(addMemberObject.getName(), addMemberObject.getMobile(), addMemberObject.getGender(), addMemberObject.getBirthDate(), addMemberObject.getRemarks(),"false",null);
		session.save(c);
		session.getTransaction().commit();
	}

	@Override
	public List<Client> getMembersBy(String filter) {
		session = HibernateUtils.getSessionFactory().openSession();
		Criteria crit = session.createCriteria(Client.class);
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
		session.close();
		return cList;
	}

	@Override
	public void addPackageToDatabase(AddPackageObject addPackageObject) {
		session = getSession();
		if(!session.getTransaction().isActive())
		session.beginTransaction();
		CPackage pkg = new CPackage(addPackageObject.getFees(), addPackageObject.getPackageName(), addPackageObject.getDays(), addPackageObject.getGender(),"false");
		session.save(pkg);
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
	
	@Override
	public void addPackageForClientToDatabase(AddClientPackageForm o) {
		
		// get current list & add to existing list - pkgdetails
		// 
		session = getSession();
		if(!session.getTransaction().isActive())
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
		pd1.setPackageFees(c.getFees());
		
		Client c1 = getClientById(o.getClientId());
		pd1.setClient(c1);
		session.save(pd1);
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
	public void createTransaction(PaymentTransaction paymentTransaction) {
		session =  HibernateUtils.getSessionFactory().openSession();
			session.beginTransaction();
		PaymentTransaction p = new PaymentTransaction(new SimpleDateFormat("dd/MM/yyyy").format(new Date()), paymentTransaction.getPackageDetailsId(), paymentTransaction.getFeesPaid(),"NO");
		PackageDetails pd = (PackageDetails) session.createCriteria(PackageDetails.class).add(Restrictions.eq("id", paymentTransaction.getPackageDetailsId())).uniqueResult();
		pd.setAmountPaid(pd.getAmountPaid()+paymentTransaction.getFeesPaid());
		CPackage c = (CPackage) session.load(CPackage.class, pd.getcPackageId());
		if(c.getFees() > pd.getAmountPaid())
			pd.setClientPackageStatus("partial-paid");
		else
			pd.setClientPackageStatus("fully-paid");
		pd.setPackagePaymentDate(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		session.save(p);
		session.getTransaction().commit();
		session.close();
		
	}

	@Override
	public List<PackageDetails> getClientPackagesByClient(Client client) {
		List<PackageDetails> pkgList = new ArrayList<PackageDetails>();
		session = HibernateUtils.getSessionFactory().openSession();
		Collection<PackageDetails> p = new LinkedHashSet(session.createCriteria(PackageDetails.class).add(Restrictions.eq("client.id", client.getId())).list());
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
	    session.close();
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
			list.add(new PaymentDataPVO(p.getClient().getName(), p.getPackageName(), p.getPackageFees(), p.getPackageStartDate(), p.getPackageEndDate(), p.getPackagePaymentDate(), p.getClient().getGender(),p.getClientPackageStatus()));
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
				if(filter.getGender().equals(p.getClient().getGender()) && String.valueOf(c.get(Calendar.MONTH)).equals(filter.getMonth()) && String.valueOf(c.get(Calendar.YEAR)).equals(filter.getYear()))
					collectionPVOList.add(new CollectionPVO(p.getClient().getName(), p.getAmountPaid(), p.getPackageName(), p.getClientPackageStatus(), p.getPackagePaymentDate()));
			}
		}
		else if(filter.getFilter().equals("GD")) {
			for(PackageDetails p : packagePaymentCollection) {
				
				if(p.getPackagePaymentDate().equals(getDdMmYyyyDate(filter.getDate())) && filter.getGender().equals(p.getClient().getGender()))
					collectionPVOList.add(new CollectionPVO(p.getClient().getName(), p.getAmountPaid(), p.getPackageName(), p.getClientPackageStatus(), p.getPackagePaymentDate()));
			}
		}else if(filter.getFilter().equals("G")) {
			for(PackageDetails p : packagePaymentCollection) {
				if(p.getClient().getGender().equals(filter.getGender()))
					collectionPVOList.add(new CollectionPVO(p.getClient().getName(), p.getAmountPaid(), p.getPackageName(), p.getClientPackageStatus(), p.getPackagePaymentDate()));
			}
		} 
			
		return collectionPVOList;
	}

	@Override
	public CollectionDashboardPVO getDashboardCollection() {
		session = HibernateUtils.getSessionFactory().openSession();
		Collection<PackageDetails> packagePaymentCollection = new LinkedHashSet(session.createCriteria(PackageDetails.class).add(Restrictions.ne("clientPackageStatus", "not paid")).list());
		Double male=0.00;
		Double female=0.00;
		Double total=0.00;
		Double steam=0.00;
		for(PackageDetails p : packagePaymentCollection) {
			if(p.getClient().getGender().equals("male"))
				male = male + p.getAmountPaid();
			else if(p.getClient().getGender().equals("female"))
				female = female + p.getAmountPaid();
		}
		total = male + female;
		return new CollectionDashboardPVO(male, female, total, steam,
				new LinkedHashSet(session.createCriteria(Client.class).add(Restrictions.eq("gender", "male")).list()).size(),
				new LinkedHashSet(session.createCriteria(Client.class).add(Restrictions.eq("gender", "female")).list()).size(),
				new LinkedHashSet(session.createCriteria(Client.class).list()).size());
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
  
}
 

