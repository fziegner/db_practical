package model;

import java.io.Serializable;

public class WorkAtPk implements Serializable {
	
    private Person person;
    private Company company;
    
	public WorkAtPk() {
	}

	public WorkAtPk(Person person, Company company) {
		this.person = person;
		this.company = company;
	}
	
	@Override
	public int hashCode() {
		return 0;
	}
	
	@Override
	public boolean equals(Object o) {
		return false;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}    
}