package main;

import org.hibernate.Session;

import api.PersonRelatedImpl;
import api.StatisticImpl;

public class Main {
	
	public static void main(String[] args) {
		
		HibernateUtil hibernate = new HibernateUtil();
		PersonRelatedImpl priAPI = new PersonRelatedImpl();
		StatisticImpl statAPI = new StatisticImpl();
		
		hibernate.setup();
		Session session = hibernate.getSessionFactory().openSession();
		
		Application app = new Application(priAPI, statAPI, hibernate, session);
		app.startApplication();
		
		session.close();
		hibernate.exit();
		
	}
}