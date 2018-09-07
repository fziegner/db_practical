package main;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.City;
import model.Continent;
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
		
		int continentid = 1460;
		int countryid = 1;
		int cityid = 959;
		
		try {
			Continent continent = session.get(Continent.class, continentid);
		    Country country = session.get(Country.class, countryid);
		    City city = session.get(City.class, cityid);
		 
		    System.out.println("Id: " + continent.getId());
		    System.out.println("Name: " + continent.getName());
		    System.out.println("Countries: " + continent.getCountries());
		    System.out.println("--------------------------------------------------------");
		    System.out.println("Id: " + country.getId());
		    System.out.println("Name: " + country.getName());
		    System.out.println("Continent: " + country.getContinent());
		    System.out.println("Cities: " + country.getCities());
		    System.out.println("Companies: " + country.getCompanies());
		    System.out.println("--------------------------------------------------------");
		    System.out.println("Id: " + city.getId());
		    System.out.println("Name: " + city.getName());
		    System.out.println("Country: " + city.getCountry());
		    System.out.println("Universities: " + city.getUniversities());
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