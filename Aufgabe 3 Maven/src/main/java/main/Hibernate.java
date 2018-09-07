package main;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.Continent;

public class Hibernate {
	
	private static SessionFactory sessionFactory;
	
	public void setup() {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
			System.out.println( "Finished Initializing Hibernate" );
		} catch( HibernateException ex ) {
			ex.printStackTrace();
			System.err.println("Failed Initializing Hibernate");
		}
    }
 
	public void exit() {
		System.out.println("Shutting down");
		sessionFactory.close();
    }
	
	protected void read() {
	    Session session = sessionFactory.openSession();
	 
	    int continentid = 1460;
	    Continent continent = session.get(Continent.class, continentid);
	 
	    System.out.println("cityId: " + continent.getContinentid());
	    System.out.println("Name: " + continent.getName());
	 
	    session.close();
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}