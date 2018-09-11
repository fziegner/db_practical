package model;

import java.util.List;

import javax.persistence.*;

@Entity
public class Country extends Place {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "continent", nullable = false)
    private Continent continent;
    
    @OneToMany(mappedBy = "country")
    private List<City> cities;
    
    @OneToMany(mappedBy = "country")
    private List<Company> companies;
    
    @OneToMany(mappedBy = "location")
    private List<Post> posts;
    
    @OneToMany(mappedBy = "location")
    private List<Comments> comments;
    
    public Country() {	
    }

	public Country(Continent continent, List<City> cities, List<Company> companies, List<Post> posts,
			List<Comments> comments) {
		this.continent = continent;
		this.cities = cities;
		this.companies = companies;
		this.posts = posts;
		this.comments = comments;
	}

	public Continent getContinent() {
		return continent;
	}

	public void setContinent(Continent continent) {
		this.continent = continent;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public List<Comments> getComments() {
		return comments;
	}

	public void setComment(List<Comments> comments) {
		this.comments = comments;
	}
}