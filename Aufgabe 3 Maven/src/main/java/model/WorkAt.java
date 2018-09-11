package model;

import javax.persistence.*;

@Entity
@Table(name = "work_at")
@IdClass(WorkAtPk.class)
public class WorkAt {
	
	@Id
	@ManyToOne
	@JoinColumn(name = "personid")
	private Person person;
	@Id
	@ManyToOne
	@JoinColumn(name = "company")
	private Company company;
	private int workfrom;

	public WorkAt() {
	}

	public WorkAt(Person person, Company company, int workfrom) {
		this.person = person;
		this.company = company;
		this.workfrom = workfrom;
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

	public int getWorkfrom() {
		return workfrom;
	}

	public void setWorkfrom(int workfrom) {
		this.workfrom = workfrom;
	}
	
	public String toString() {
		return "Person: " + person.getFirstName() + " " + person.getLastName() + " Company: " + company.getName();
	}
}