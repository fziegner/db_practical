package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Country extends Place {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "continent", nullable = false)
    private Continent continent;
    
    @OneToMany(mappedBy = "country")
    private List<City> cities = new ArrayList<>();
    
//    @OneToMany(mappedBy = "country")
    //private List<Company> companies;
    
    public Country() {	
    }

	public Country(Continent continent, List<City> cities, List<Company> companies) {
		this.continent = continent;
		this.cities = cities;
		//this.companies = companies;
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

	//public List<Company> getCompanies() {
		//return companies;
	//}

	//public void setCompanies(List<Company> companies) {
		//this.companies = companies;
	//}
}