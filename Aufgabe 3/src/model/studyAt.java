package model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "study_at")
public class studyAt {
	
	@EmbeddedId
	private studyID studyid;
	private int classyear;

	@ManyToOne(fetch = FetchType.LAZY)
	private University university;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Person person;

	public studyAt() {
	}

	public studyAt(studyID studyid, int classyear, University university, Person person) {
		this.studyid = studyid;
		this.classyear = classyear;
		this.university = university;
		this.person = person;
	}

	public studyID getStudyid() {
		return studyid;
	}

	public void setStudyid(studyID studyid) {
		this.studyid = studyid;
	}

	public int getClassyear() {
		return classyear;
	}

	public void setClassyear(int classyear) {
		this.classyear = classyear;
	}

	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}

@Embeddable
class studyID implements Serializable {
    private long personid;
    private int university;
}