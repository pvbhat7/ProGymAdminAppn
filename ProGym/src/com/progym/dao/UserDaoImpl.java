package com.progym.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.progym.model.AddClientPackageForm;
import com.progym.model.AddMemberObject;
import com.progym.model.AddPackageObject;
import com.progym.model.CPackage;
import com.progym.model.Client;
import com.progym.model.Login;
import com.progym.model.PackageDetails;
import com.progym.utils.HibernateUtils;

@Repository
public class UserDaoImpl implements UserDao {
	
	/*
	 * @Autowired DataSource datasource;
	 */
  @Autowired
  JdbcTemplate jdbcTemplate;
  
	/*
	 * @Autowired SessionFactory sessionFactory;
	 */
  
  
  public void register(Client client) {
	  SessionFactory factory = HibernateUtils.getSessionFactory();
	  Session session = factory.openSession();
	  session.beginTransaction();
	  session.save(client);
	  session.getTransaction().commit();	  
    }
  
    public Client validateUser(Login login) {
    	Client c = new Client();
    	c.setName("prashant");
    return c;
    }

	@Override
	public void addMemberToDatabase(AddMemberObject addMemberObject) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		Client c = new Client(addMemberObject.getName(), addMemberObject.getMobile(), addMemberObject.getGender(), addMemberObject.getBirthDate(), addMemberObject.getRemarks(),"false",null);
		session.save(c);
		session.getTransaction().commit();
	}

	@Override
	public List<Client> getMembersBy(String filter) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		Criteria crit = session.createCriteria(Client.class);
		if(!filter.equals("all"))
		crit.add(Restrictions.eq("gender",filter));
		crit.add(Restrictions.eq("discontinue","false"));
		return crit.list();
	}

	@Override
	public void addPackageToDatabase(AddPackageObject addPackageObject) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		CPackage pkg = new CPackage(addPackageObject.getFees(), addPackageObject.getPackageName(), addPackageObject.getDays(), addPackageObject.getGender(),"false");
		session.save(pkg);
		session.getTransaction().commit();		
	}

	@Override
	public List<CPackage> getPackagesByFilter(String filter) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		Criteria crit = session.createCriteria(CPackage.class);
		if(!filter.equals("all"))
		crit.add(Restrictions.eq("gender",filter));
		crit.add(Restrictions.eq("discontinue","false"));
		return crit.list();		
	}
	
	@Override
	public void addPackageForClientToDatabase(AddClientPackageForm o) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		if(!session.getTransaction().isActive())
		session.beginTransaction();
		Client c=getClientById(o.getClientId());
		List<PackageDetails> packageDetails=null;
		if(c.getPackageDetails() == null)
			packageDetails = new ArrayList<PackageDetails>();
		 	packageDetails= c.getPackageDetails();
		 	
		 	PackageDetails pd1 = new PackageDetails();
			pd1.setClientPackageStatus("active");
			pd1.setPackageStartDate(o.getStartDate());
			CPackage cp1 =  getPackageById(o.getClientId());
			pd1.setcPackage(cp1);
			pd1.setDiscontinue("false");
			pd1.setAmountPaid(0.0);
			packageDetails.add(pd1);
			c.setPackageDetails(packageDetails);
			session.saveOrUpdate(c);
			session.getTransaction().commit();
	}

	@Override
	public Client getClientById(int clientId) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		if(!session.getTransaction().isActive())
		session.beginTransaction();
		Criteria crit = session.createCriteria(Client.class);
		crit.add(Restrictions.eq("id",clientId));
		crit.add(Restrictions.eq("discontinue","false"));
		return (Client) crit.uniqueResult();
	}
	
	@Override
	public CPackage getPackageById(int id) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		if(!session.getTransaction().isActive())
		session.beginTransaction();
		Criteria crit = session.createCriteria(CPackage.class);
		crit.add(Restrictions.eq("id",id));
		crit.add(Restrictions.eq("discontinue","false"));
		return (CPackage) crit.uniqueResult();
	}
	
	
  
}
 

