package api;

import java.sql.ResultSet;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Session;
import org.hibernate.query.Query;

import model.Company;
import model.Organization;
import model.Person;
import model.StudyAt;
import model.Tag;
import model.University;
import model.WorkAt;

public class PersonRelatedImpl implements PersonRelatedAPI {
	
	public PersonRelatedImpl() {
	}

	/**
	 * Shows data of person related to the given personID
	 */
	@Override
	public void getProfile(long personID, Session session) {
		try {
			Person person = session.get(Person.class, personID);
			System.out.println("Name: " + person.getFirstName() + " " + person.getLastName());
			System.out.println("Gender: " + person.getGender());
			System.out.println("Birthday: " + person.getBirthday());
			System.out.println("Residence: " + person.getCity().getName());
			System.out.println("Browser: " + person.getBrowserUsed());
			System.out.println("IP: " + person.getLocationIP());
			System.out.println("Creation date: " + person.getCreationDate());
		} catch (NullPointerException ex) {
			System.out.println("ID doesn't exist");
		}
	}
	
	/**
	 * Outputs common interest of a person and his friends by a given personID
	 */
	@Override
	public void getCommonInterestOfMyFriends(long personID, Session session) {
		try {
			Person person = session.get(Person.class, personID);
			Map<Person, Calendar> friends = person.getFriends();
			List<Tag> personInterest = person.getInterests();
			
			for(Iterator<Person> itr = friends.keySet().iterator(); itr.hasNext();) {
				Person friend = itr.next();
				for(int i = 0; i < friend.getInterests().size(); i++) {
					if(personInterest.contains(friend.getInterests().get(i))) {
						System.out.println(friend.getInterests().get(i).getTagID() + " " + friend.getInterests().get(i).getTagName() + " - " + friend.getFirstName() + " " + friend.getLastName());
					}
				}
			}
		} catch (Exception ex) {
			System.out.println("ID doesn't exist");
		}
	}
	
	/**
	 * Prints common friends of two persons given their personIds
	 */
	@Override
	public void getCommonFriends(long personID1, long personID2, Session session) {
		try {
			Person person1 = session.get(Person.class, personID1);
			Person person2 = session.get(Person.class, personID2);
			for (Iterator<Person> itr = person1.getFriends().keySet().iterator(); itr.hasNext(); ) {
				Person friend = itr.next();
				if(friend.getPersonID() != person1.getPersonID() && person2.getFriends().containsKey(friend)) {
					System.out.println("Id: " + friend.getPersonID() + " Name: " + friend.getFirstName() + " " + friend.getLastName());
				}
			}
		} catch (Exception ex) {
			System.out.println("ID doesn't exist");
		}
	}
	
	/**
	 * Outputs person with most common interests to a given personID
	 */
	@Override
	public void getPersonsWithMostCommonInterests(long personID, Session session) {
		Person person = session.get(Person.class, personID);
		Query<Person> query = session.createQuery("from Person");
		List<Person> personList = query.getResultList();
		Map<Integer, Person> mostCommonInterests = new HashMap<Integer, Person>();
		int personInterestCount = 0;
		int maxInterestCount = 0;
		personList.remove(getPerson(personID, session));
		for(int i = 0; i < personList.size(); i++) {
			for(int j = 0; j < personList.get(i).getInterests().size(); j++) {
				if(person.getInterests().contains(personList.get(i).getInterests().get(j))) {
					//System.out.println(personList.get(i).getPersonID()+ " " + personList.get(i).getInterests().get(j).getTagName());
					personInterestCount++;
				}
			}
			if(personInterestCount >= maxInterestCount) {
				maxInterestCount = personInterestCount;
				mostCommonInterests.put(maxInterestCount, personList.get(i));
				for(Iterator<Integer> itr = mostCommonInterests.keySet().iterator(); itr.hasNext();) {
					if(itr.next() < maxInterestCount) {
						itr.remove();
					}
				}
			}
			personInterestCount = 0;
		}
		for(Iterator<Person> itr = mostCommonInterests.values().iterator(); itr.hasNext();) {
			//System.out.println(itr.next().getPersonID() + " - " + itr.next().getFirstName() + " " + itr.next().getLastName());
			Person p = getPerson(itr.next().getPersonID(), session);
			System.out.println(p.getPersonID() + " - " + p.getFirstName() + " " + p.getLastName() + " " + maxInterestCount);
		}
	}

	/**
	 * Prints job recommendations to a given personID by acknowledging friends jobs and location 
	 */
	@Override
	public void getJobRecommendations(long personID, Session session) {

		Person person = getPerson(personID, session);
		Map<Person, Calendar> friends = getPerson(personID, session).getFriends();
		Set<Organization> potentialRecommendations = new HashSet<>();
		for(Map.Entry<Person, Calendar> entry : friends.entrySet()) {
			for(WorkAt workAt : entry.getKey().getWorkAt()) {
				potentialRecommendations.add(workAt.getCompany());
			}
			for(StudyAt studyAt : entry.getKey().getStudyAt()) {
				potentialRecommendations.add(studyAt.getUniversity());
			}
		}
		for(Organization org : potentialRecommendations) {
			if(org instanceof University) {
				if(((University) org).getCity().equals(person.getCity())) {
					System.out.println(org.toString() + " (University)");
				}
			}
			if(org instanceof Company) {
				if(((Company) org).getCountry().equals(person.getCity().getCountry())) {
					System.out.println(org.toString() + " (Company)");
				}
			}
		}
	}
	
	public void getShortestFriendshipPath(long personID1, long personID2, Session session) {
		StoredProcedureQuery spq = session.createStoredProcedureQuery("getShortestFriendshipPath");
		spq.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
		spq.registerStoredProcedureParameter(2, Long.class, ParameterMode.IN);
		spq.setParameter(1, personID1);
		spq.setParameter(2, personID2);
		List<String> results = spq.getResultList();
		System.out.println(results.toString());
	}

	/**
	 * Function to get a person to a given personID
	 * returns person
	 */
	@Override
	public Person getPerson(long personID, Session session) {
		Person person = session.get(Person.class, personID);
		return person;
	}
}