package main;

import api.PersonRelatedImpl;

public class Main {
	
	public static void main(String[] args) {
		
		System.out.println("Hallo");
		
		HibernateUtil hibernate = new HibernateUtil();
		PersonRelatedImpl api = new PersonRelatedImpl();
		
		hibernate.setup();
		long profileID = 96;
		api.getProfile(profileID, hibernate.getSessionFactory());
		//hibernate.read();
		hibernate.exit();
	}
}