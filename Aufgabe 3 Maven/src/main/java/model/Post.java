package model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Post implements Message {

	@Id
	@Column(name = "postID")
	private Long postID;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar creationDate;
	
	@JoinColumn(name = "person")
	private Long creatorID;
	
	@Column(length = 32)
	private String languageName;
	
	@Column(length = 128)
	private String imageFile;
	
	@Column(length = 64)
	private String browserUsed;
	
	@Column(nullable = false)
	private String locationIP;
	
	@Column(columnDefinition = "context")
	private String context;
	
	private int length;
	
	@Column(nullable = false)
	@JoinColumn(name = "country")
	private int location;
	
	//TODO
	/*@Column(nullable = false)
	@JoinColumn
	private Long containerForum;*/
	
	public Post() {
	}
	
	public Post(Long postID, Calendar creationDate, Long creatorID, String languageName, String imageFile, String browserUsed, String locationIP, String context, int length, int location) {
		this.postID = postID;
		this.creationDate = creationDate;
		this.creatorID = creatorID;
		this.languageName = languageName;
		this.imageFile = imageFile;
		this.browserUsed = browserUsed;
		this.locationIP = locationIP;
		this.context = context;
		this.length = length;
		this.location = location;
		//this.containerForum = containerForum;
	}

	public Long getPostID() {
		return postID;
	}

	public Calendar getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}

	public Long getCreatorID() {
		return creatorID;
	}

	public void setCreatorID(Long creatorID) {
		this.creatorID = creatorID;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	public String getImageFile() {
		return imageFile;
	}

	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}

	public String getBrowserUsed() {
		return browserUsed;
	}

	public void setBrowserUsed(String browserUsed) {
		this.browserUsed = browserUsed;
	}

	public String getLocationIP() {
		return locationIP;
	}

	public void setLocationIP(String locationIP) {
		this.locationIP = locationIP;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}
	
	//TODO Getter/Setter für ContainerForum
	
}