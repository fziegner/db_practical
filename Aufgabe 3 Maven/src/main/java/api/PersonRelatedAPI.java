package api;

import org.hibernate.SessionFactory;

public interface PersonRelatedAPI {
	
	public void getProfile(long personID, SessionFactory sessionFactory);

}