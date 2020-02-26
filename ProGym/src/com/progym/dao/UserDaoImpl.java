package com.progym.dao;

import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.progym.model.AddMemberObject;
import com.progym.model.AddPackageObject;
import com.progym.model.CPackage;
import com.progym.model.Client;
import com.progym.model.Login;
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
	
	
  
}
 

