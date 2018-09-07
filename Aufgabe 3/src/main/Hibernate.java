package main;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Hibernate {
	
	private static SessionFactory sessionFactory;
	
	/*
	static {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
			System.out.println( "Finished Initializing Hibernate" );
		} catch( HibernateException ex ) {
			ex.printStackTrace();
			System.err.println("Failed Initializing Hibernate");
		}
	}
	*/
	
	public void setup() {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
		        .configure() // configures settings from hibernate.cfg.xml
		        .build();
		try {
		    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception ex) {
		    StandardServiceRegistryBuilder.destroy(registry);
		}
    }
 
	public void exit() {
		sessionFactory.close();
    }
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
