package api;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import model.Country;
import model.TagClass;

public class StatisticImpl implements StatisticAPI {

	public StatisticImpl() {
	}
	
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
}