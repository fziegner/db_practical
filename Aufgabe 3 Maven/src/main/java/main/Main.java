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
import model.Person;
import model.Post;

public class Main {
	
	public static void main(String[] args) {
		
		HibernateUtil hibernate = new HibernateUtil();
		PersonRelatedImpl priAPI = new PersonRelatedImpl();
		StatisticImpl statAPI = new StatisticImpl();
		
		hibernate.setup();
		Session session = hibernate.getSessionFactory().openSession();
		
		Application app = new Application(priAPI, statAPI, hibernate, session);
		app.startApplication();
		
		hibernate.exit();
		
	}
}