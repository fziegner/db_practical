package model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Comment")
public class Comments implements Message {

	@Id
	private Long commentID;
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	@JoinColumn(name = "person")
	private Long creatorID;
	private String browserUsed;
	private String locationIP;
	private String content;
	private int length;
	@JoinColumn(name = "comment")
	private Long replyOfComment;
	@JoinColumn(name = "post")
	private Long replyOfPost;
	
	@ManyToMany
	@JoinTable(name = "likes_comment",
			   joinColumns = {@JoinColumn(name = "commentid")},
			   inverseJoinColumns = {@JoinColumn(name = "personid")})
	private List<Person> likes;
	
	@ManyToOne
	@JoinColumn(name = "location")
	private Country location;

	public Comments() {
	}
	
	public Comments(Long commentID, Date creationDate, Long creatorID, String browserUsed, String locationIP,
			String content, int length, Long replyOfComment, Long replyOfPost, List<Person> likes, Country location) {
		this.commentID = commentID;
		this.creationDate = creationDate;
		this.creatorID = creatorID;
		this.browserUsed = browserUsed;
		this.locationIP = locationIP;
		this.content = content;
		this.length = length;
		this.replyOfComment = replyOfComment;
		this.replyOfPost = replyOfPost;
		this.likes = likes;
		this.location = location;
	}
	
	public Long getCommentID() {
		return commentID;
	}

	public void setCommentID(Long commentID) {
		this.commentID = commentID;
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

	public Long getReplyOfComment() {
		return replyOfComment;
	}

	public void setReplyOfComment(Long replyOfComment) {
		this.replyOfComment = replyOfComment;
	}

	public Long getReplyOfPost() {
		return replyOfPost;
	}

	public void setReplyOfPost(Long replyOfPost) {
		this.replyOfPost = replyOfPost;
	}

	public List<Person> getLikes() {
		return likes;
	}

	public void setLikes(List<Person> likes) {
		this.likes = likes;
	}

	public Country getLocation() {
		return location;
	}

	public void setLocation(Country location) {
		this.location = location;
	}

	public String toString() {
		return commentID.toString();
	}
}