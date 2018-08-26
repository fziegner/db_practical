package main;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Hibernate {
	
	private static SessionFactory sessionFactory;
	
	static {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
			System.out.println( "Finished Initializing Hibernate" );
		} catch( HibernateException ex ) {
			ex.printStackTrace();
			System.err.println("Failed Initializing Hibernate");
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
