package api;

import org.hibernate.Session;

public interface PersonRelatedAPI {
	
	public void getProfile(long personID, Session session);
}