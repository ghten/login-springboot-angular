package com.onurabat.login.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.onurabat.login.models.entity.Role;

public class registerDto implements Serializable{
	
	private String name;
	private String surname;
	private String email;
	private String password;
	private String vat;
	private String companyName;
	private String address;
	private String postcode;
	private String city;
	private String state;
    private Integer countryId;
    private Integer role;
    
    private static final long serialVersionUID = 1L;
    
	public String getName() {
		return name;
	}
	public String getSurname() {
		return surname;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public String getVat() {
		return vat;
	}
	public String getCompanyName() {
		return companyName;
	}
	public String getAddress() {
		return address;
	}
	public String getPostcode() {
		return postcode;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public Integer getCountryId() {
		return countryId;
	}
	public Integer getRole() {
		return role;
	}
	
}
