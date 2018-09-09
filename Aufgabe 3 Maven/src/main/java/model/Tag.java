package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Tag {
	
	@Id
	private int tagID;
	
	@Column(length = 128)
	private String tagName;
	
	//TODO Tag Class Type
	
	public Tag() {	
	}
	
	public Tag(int tagID, String tagName) {
		this.tagID = tagID;
		this.tagName = tagName;
	}

	public int getTagID() {
		return tagID;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

}