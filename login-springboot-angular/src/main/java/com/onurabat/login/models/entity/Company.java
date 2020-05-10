package com.onurabat.login.models.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "companies")
public class Company implements Serializable {

	@Id
	private Long id;
	
	@Column(unique = true, nullable = false, length = 45)
	@NotEmpty
	private String vat;

	@Column(nullable = false, length = 45)
	@NotEmpty
	private String name;
	
	@Column(nullable = false, length = 255)
	@NotEmpty
	private String address;
	
	@Column(nullable = false, length = 45)
	@NotEmpty
	private String postcode;
	
	@Column(nullable = false, length = 45)
	@NotEmpty
	private String city;
	
	@Column(nullable = false, length = 45)
	@NotEmpty
	private String state;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "countries_id", nullable = false)
    private Country country;
    
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "companies_id")
	private Set<UserLocale> user = new HashSet<UserLocale>();

	private static final long serialVersionUID = 1L;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVat() {
		return vat;
	}

	public void setVat(String vat) {
		this.vat = vat;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Set<UserLocale> getUser() {
		return user;
	}

	public void setUser(Set<UserLocale> user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", vat=" + vat + ", name=" + name + ", address=" + address + ", postcode="
				+ postcode + ", city=" + city + ", state=" + state + ", country=" + country + ", user=" + user + "]";
	}
	
}
