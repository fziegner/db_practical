package model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class Comments implements Message {

	@Id
	@Column(name = "commentID")
	private Long commentID;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar creationDate;
	
	@JoinColumn(name = "person")
	private Long creatorID;
	
	@Column(length = 64)
	private String browserUsed;
	
	@Column(nullable = false)
	private String locationIP;
	
	@Column(columnDefinition = "text")
	private String text;
	
	private int length;
	
	@JoinColumn(name = "comment")
	private Long replyOfComment;
	
	@JoinColumn(name = "post")
	private Long replyOfPost;
	
	@Column(nullable = false)
	@JoinColumn(name = "country")
	private int location;

	public Comments() {
	}

	public Comments(Long commentID, Calendar creationDate, Long creatorID, String browserUsed, String locationIP, String text, int length, Long replyOfComment, Long replyOfPost, int location) {
		this.commentID = commentID;
		this.creationDate = creationDate;
		this.creatorID = creatorID;
		this.browserUsed = browserUsed;
		this.locationIP = locationIP;
		this.text = text;
		this.length = length;
		this.replyOfComment = replyOfComment;
		this.replyOfPost = replyOfPost;
		this.location = location;
	}

	public Long getCommentID() {
		return commentID;
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}
	
}