package main;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.xml.stream.events.Comment;

import org.hibernate.Session;
import org.hibernate.query.Query;

import api.PersonRelatedImpl;
import api.StatisticImpl;
import model.Comments;
import model.Country;
import model.Post;

public class Main {
	
	public static void main(String[] args) {
		
		System.out.println("Hallo");
		
		HibernateUtil hibernate = new HibernateUtil();
		PersonRelatedImpl api = new PersonRelatedImpl();
		StatisticImpl api2 = new StatisticImpl();
		
		hibernate.setup();
		Session session = hibernate.getSessionFactory().openSession();

		//api.getProfile(profileID,hibernate.getSessionFactory());
		//api2.getTagClassHierarchy(session);
		//hibernate.read();
		api2.getMostPostingCountry(session);

		session.close();
		hibernate.exit();
	}
}