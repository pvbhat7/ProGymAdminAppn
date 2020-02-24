package com.progym.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.progym.model.Client;

public class HibernateUtils {

	private static StandardServiceRegistry serviceRegistry;
	private static SessionFactory factory;
	
	public static SessionFactory getSessionFactory() {
		if(factory == null) {
			
			try {
				
				/*
				 * serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
				 * MetadataSources metadataSources = new MetadataSources(serviceRegistry);
				 * Metadata metadata = metadataSources.getMetadataBuilder().build();
				 * factory=metadata.buildSessionFactory();
				 */
			
				  System.out.println("config searching...");				
				  Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
				  cfg.addAnnotatedClass(Client.class); //cfg.configure();
				  StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
				  sb.applySettings(cfg.getProperties()); StandardServiceRegistry
				  standardServiceRegistry = sb.build(); factory =cfg.buildSessionFactory(standardServiceRegistry);
				   	
        	
			}
			catch (Exception e) {
				System.out.println("exception in hibernateutils");
				if(serviceRegistry != null)
					StandardServiceRegistryBuilder.destroy(serviceRegistry);
			}			
			
		}
		
		return factory;
	}
	
	public static void shutdown() {
        if (serviceRegistry != null) {
            StandardServiceRegistryBuilder.destroy(serviceRegistry);
        }
    }
}
