package api;

import org.hibernate.Session;

import model.Person;

public interface PersonRelatedAPI {
	
	public void getProfile(long personID, Session session);
	
	public void getCommonInterestOfMyFriends(long personID, Session session);
	
	public void getCommonFriends(long personID1, long personID2, Session session);
	
	public void getPersonsWithMostCommonInterests(long personID, Session session); 
	
	public void getJobRecommendations(long personID, Session session);
	
	public void getShortestFriendshipPath(long personID1, long personID2, Session session);
	
	public Person getPerson(long personID, Session session);
}