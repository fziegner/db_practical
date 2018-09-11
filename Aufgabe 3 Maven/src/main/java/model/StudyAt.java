package model;

import javax.persistence.*;

@Entity
@Table(name = "study_at")
@IdClass(StudyAtPk.class)
public class StudyAt {
	
	@Id
	@ManyToOne
	@JoinColumn(name = "personid")
	private Person person;
	@Id
	@ManyToOne
	@JoinColumn(name = "university")
	private University university;
	private int classyear;

	public StudyAt() {
	}

	public StudyAt(Person person, University university, int classyear) {
		this.person = person;
		this.university = university;
		this.classyear = classyear;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	public int getClassyear() {
		return classyear;
	}

	public void setClassyear(int classyear) {
		this.classyear = classyear;
	}
	
	public String toString() {
		return "Person: " + person.getFirstName() + " " + person.getLastName() + " Uni: " + university.getName();
	}
}