package model;

import java.util.List;

import javax.persistence.*;

@Entity
public class Country extends Place {

	private int countryid;
	
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "continent", nullable = false)
    private Continent continent;
    
    @OneToMany(mappedBy = "country")
    private List<City> cities;
    
//    @OneToMany(mappedBy = "country")
    //private List<Company> companies;
    
    public Country() {	
    }

	public Country(int countryid, Continent continent, List<City> cities, List<Company> companies) {
		this.countryid = countryid;
		this.continent = continent;
		this.cities = cities;
		//this.companies = companies;
	}

	public int getCountryid() {
		return countryid;
	}

	public void setCountryid(int countryid) {
		this.countryid = countryid;
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