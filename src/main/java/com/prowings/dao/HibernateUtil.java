package com.prowings.dao;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	private static SessionFactory buildSessionFactory()
	{
		try
		{
			return new Configuration().configure("/com/prowings/dao/hibernate.cfg.xml").buildSessionFactory();
		}catch(Throwable ex)
		{
			System.err.println("buildSessionfactory failed"+ ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
		public static SessionFactory getSessionFactory(){
			return sessionFactory;
		}
		
		public static void close() {
			// Close all cached and active connection pools
			getSessionFactory().close();
		}
		
		
	}
 
