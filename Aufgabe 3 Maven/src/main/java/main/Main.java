package main;

import org.hibernate.Session;

import api.PersonRelatedImpl;
import api.StatisticImpl;

public class Main {
	
	public static void main(String[] args) {
		
		System.out.println("Hallo");
		
		HibernateUtil hibernate = new HibernateUtil();
		PersonRelatedImpl api = new PersonRelatedImpl();
		StatisticImpl api2 = new StatisticImpl();
		
		hibernate.setup();
		Session session = hibernate.getSessionFactory().openSession();
		//long profileID = 96;
		//api.getProfile(profileID, hibernate.getSessionFactory());
		api2.getTagClassHierarchy(session);
		//hibernate.read();
		session.close();
		hibernate.exit();
	}
}