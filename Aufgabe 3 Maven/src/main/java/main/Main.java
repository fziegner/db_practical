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
		long profileID = 96;
		long profile2 = 8796093022251L;
		//api.getProfile(8796093022251L, session);
		//api2.getTagClassHierarchy(session);
		//hibernate.read();
		api.getCommonFriends(profileID, profile2, session);;
		session.close();
		hibernate.exit();
	}
}