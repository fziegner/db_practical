package api;

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
}