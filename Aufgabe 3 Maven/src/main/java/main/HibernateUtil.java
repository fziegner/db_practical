package main;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.Country;

public class HibernateUtil {
	
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
		int id = 1;
		try {
		    Country country = session.get(Country.class, id);
		 
		    System.out.println("Id: " + country.getId());
		    System.out.println("Name: " + country.getName());
		    System.out.println("Cities: " + country.getCities());
		} catch (NullPointerException ex) {
			System.out.println("ID doesn't exist");
		} finally {
			session.close();
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}