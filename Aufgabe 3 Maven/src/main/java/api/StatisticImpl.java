package api;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import model.Comments;
import model.Country;
import model.Person;
import model.TagClass;

public class StatisticImpl implements StatisticAPI {

	public StatisticImpl() {
	}
	
	/**
	 * Prints Tag Class Hierarchy
	 */
	@Override
	public void getTagClassHierarchy(Session session) {
		TagClass rootTagClass = session.get(TagClass.class, 0);
		System.out.println("0 " + rootTagClass.getTagClassName());
		printHierarchy("0", rootTagClass);
	}
	
	private void printHierarchy(String level, TagClass rootTagClass) {
		int i = 1;
		for(TagClass tc : rootTagClass.getChildTagClass()) {
			if(!tc.getChildTagClass().isEmpty()) {
				System.out.println(level + "." + i + " " + tc.getTagClassName());
				printHierarchy(level + "." + i, tc);
			} else {
			System.out.println(level + "." + i + " " + tc.getTagClassName());
			}
			i++;
		}
	}

	/**
	 * Function to get the most posting country by total posts
	 */
	@Override
	public void getMostPostingCountry(Session session) {
		
		Query<Country> query = session.createQuery("from Country");
		List<Country> countryList = query.getResultList();
		int messageCount = 0;
		int countryID = 0;
		for(Country country : countryList){
			if(messageCount < country.getComments().size() + country.getPosts().size()) {
				messageCount = country.getComments().size() + country.getPosts().size();
				countryID = country.getId();
			}
		}
		Country country = session.get(Country.class, countryID);
		System.out.println("Most posting Country: " + country.getName() + "\nMessages: " + (country.getComments().size() + country.getPosts().size()));
	}

	/**
	 * Function to get the most popular Comment by total likes
	 */
	@Override
	public void getPopularComments(Session session, int minimumLikes) {
		Query<Comments> query = session.createQuery("from Comments");
		List<Comments> commentsList = query.getResultList();
		List<Comments> popularComments = new ArrayList<>();
		for(Comments comment : commentsList) {
			if(minimumLikes < comment.getLikes().size()) {
				popularComments.add(comment);
			}
		}
		for(Comments comment : popularComments) {
			Person person = session.get(Person.class, comment.getCreatorID());
			System.out.println(comment.getCommentID() + " by " + person.getFirstName() + " " + person.getLastName());
		}
	}
}