package model;

import java.util.Calendar;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Forum {
	
	@Id
	@Column(name = "forumID")
	private Long forumID;
	
	@Column(length = 128)
	private String forumTitle;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar creationDate;
	
	@OneToOne
	@JoinColumn(name = "moderator")
	private Person moderator;
	
	@OneToMany(mappedBy = "forum", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Post> posts;
	
	//TODO hasTags and hasMembers
	
	public Forum() {
	}
	
	public Forum(Long forumID, String forumTitle, Calendar creationDate, Person moderator) {
		this.forumID = forumID;
		this.forumTitle = forumTitle;
		this.creationDate = creationDate;
		this.moderator = moderator;
	}

	public Long getForumID() {
		return forumID;
	}

	public String getForumTitle() {
		return forumTitle;
	}

	public void setForumTitle(String forumTitle) {
		this.forumTitle = forumTitle;
	}

	public Calendar getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}

	public Person getModerator() {
		return moderator;
	}

	public void setModerator(Person moderator) {
		this.moderator = moderator;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}
	
}