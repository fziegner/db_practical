package api;

import org.hibernate.Session;

public interface StatisticAPI {
	
	public void getTagClassHierarchy(Session session);
	
	public void getMostPostingCountry(Session session);
	
	public void getPopularComments(Session session, int minimumLikes);
}