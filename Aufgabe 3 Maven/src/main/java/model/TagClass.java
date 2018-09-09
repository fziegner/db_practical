package model;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "tag_class")
public class TagClass {

	@Id
	private int tagClassID;
	private String tagClassName;
	
	@ManyToMany
	@JoinTable(name = "is_sub_class",
			   joinColumns = {@JoinColumn(name = "tagclassid1")},
               inverseJoinColumns = {@JoinColumn(name = "tagclassid2")})
	private Set<TagClass> parentTagClass;
	
	@ManyToMany(mappedBy = "parentTagClass")
	private Set<TagClass> childTagClass;

	public TagClass() {
	}

	public TagClass(int tagClassID, String tagClassName, Set<TagClass> parentTagClass, Set<TagClass> childTagClass) {
		this.tagClassID = tagClassID;
		this.tagClassName = tagClassName;
		this.parentTagClass = parentTagClass;
		this.childTagClass = childTagClass;
	}

	public int getTagClassID() {
		return tagClassID;
	}

	public void setTagClassID(int tagClassID) {
		this.tagClassID = tagClassID;
	}

	public String getTagClassName() {
		return tagClassName;
	}

	public void setTagClassName(String tagClassName) {
		this.tagClassName = tagClassName;
	}

	public Set<TagClass> getParentTagClass() {
		return parentTagClass;
	}

	public void setParentTagClass(Set<TagClass> parentTagClass) {
		this.parentTagClass = parentTagClass;
	}

	public Set<TagClass> getChildTagClass() {
		return childTagClass;
	}

	public void setChildTagClass(Set<TagClass> childTagClass) {
		this.childTagClass = childTagClass;
	}
	
	public String toString() {
		return tagClassName;
	}
}