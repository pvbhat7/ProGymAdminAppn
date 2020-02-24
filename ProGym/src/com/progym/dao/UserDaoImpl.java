package com.progym.dao;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.progym.model.Client;
import com.progym.model.Login;
import com.progym.utils.HibernateUtils;

public class UserDaoImpl implements UserDao {
	
	/*
	 * @Autowired DataSource datasource;
	 */
  @Autowired
  JdbcTemplate jdbcTemplate;
  
  
  
  public void register(Client client) {
	  SessionFactory factory = HibernateUtils.getSessionFactory();
	  if(factory != null)
		  System.out.println("factory object fetched");
	  Session session = factory.openSession();
	  session.beginTransaction();
	  session.save(client);
	  session.getTransaction().commit();	  
    }
  
    public Client validateUser(Login login) {
    	Client c = new Client();
    	c.setName("prashant");;
    return c;
    }
  
}
 

