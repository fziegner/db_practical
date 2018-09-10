package api;

import java.util.Iterator;

import org.hibernate.Session;

import model.Person;

public class PersonRelatedImpl implements PersonRelatedAPI {
	
	public PersonRelatedImpl() {
	}

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
	
	@Override
	public void getCommonInterestOfMyFriends(long personID, Session session) {
		try {
			
		} catch (Exception ex) {
			System.out.println("ID doesn't exist");
		}
	}
	
	@Override
	public void getCommonFriends(long personID1, long personID2, Session session) {
		try {
			Person person1 = session.get(Person.class, personID1);
			Person person2 = session.get(Person.class, personID2);
			for (Iterator<Person> it = person1.getFriends().keySet().iterator(); it.hasNext(); ) {
				Person friend = it.next();
				if(friend.getPersonID() != person1.getPersonID() && person2.getFriends().containsKey(friend)) {
					System.out.println("Id: " + friend.getPersonID() + " Name: " + friend.getFirstName() + friend.getLastName());
				}
			}
		} catch (Exception ex) {
			System.out.println("ID doesn't exist");
		}
	}
	
	@Override
	public Person getPerson(long personID, Session session) {
		Person person = session.get(Person.class, personID);
		return person;
	}
}