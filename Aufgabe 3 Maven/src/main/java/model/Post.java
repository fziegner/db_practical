package model;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Post implements Message {

	@Id
	private Long postID;
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	@JoinColumn(name = "person")
	private Long creatorID;
	private String languageName;
	private String imageFile;
	private String browserUsed;
	private String locationIP;
	private String content;
	private int length;
	
	@ManyToOne
	@JoinColumn(name = "location")
	private Country location;

	//TODO
	/*@Column(nullable = false)
	@JoinColumn
	private Long containerForum;*/
	
	public Post() {
	}

	public Post(Long postID, Date creationDate, Long creatorID, String languageName, String imageFile,
			String browserUsed, String locationIP, String content, int length, Country location) {
		this.postID = postID;
		this.creationDate = creationDate;
		this.creatorID = creatorID;
		this.languageName = languageName;
		this.imageFile = imageFile;
		this.browserUsed = browserUsed;
		this.locationIP = locationIP;
		this.content = content;
		this.length = length;
		this.location = location;
	}

	public Long getPostID() {
		return postID;
	}

	public void setPostID(Long postID) {
		this.postID = postID;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public Country getLocation() {
		return location;
	}

	public void setLocation(Country location) {
		this.location = location;
	}
	
	public String toString() {
		return postID.toString();
	}
	//TODO Getter/Setter für ContainerForum
}