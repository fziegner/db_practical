package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TagClass {

	@Id
	private int tagClassID;
	
	@Column(length = 128)
	private String tagClassName;

	public TagClass() {
	}
	
	public TagClass(int tagClassID, String tagClassName) {
		this.tagClassID = tagClassID;
		this.tagClassName = tagClassName;
	}

	public int getTagClassID() {
		return tagClassID;
	}

	public String getTagClassName() {
		return tagClassName;
	}

	public void setTagClassName(String tagClassName) {
		this.tagClassName = tagClassName;
	}
	
	
}