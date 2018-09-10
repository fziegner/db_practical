package api;

import org.hibernate.Session;

public interface PersonRelatedAPI {
	
	public void getProfile(long personID, Session session);
	
	public void getCommonFriends(long personID1, long personID2, Session session);
}