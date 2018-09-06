package model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "work_at")
public class workAt {
	
	@EmbeddedId
	private workID workid;
	private int workfrom;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Company company;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Person person;

	public workAt() {
	}

	public workAt(workID workid, int workfrom, Company company, Person person) {
		this.workid = workid;
		this.workfrom = workfrom;
		this.company = company;
		this.person = person;
	}

	public workID getWorkid() {
		return workid;
	}

	public void setWorkid(workID workid) {
		this.workid = workid;
	}

	public int getWorkfrom() {
		return workfrom;
	}

	public void setWorkfrom(int workfrom) {
		this.workfrom = workfrom;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}

@Embeddable
class workID implements Serializable {
    private long personid;
    private int companyid;
}