package com.onurabat.login.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onurabat.login.models.entity.Country;
import com.onurabat.login.models.service.ICountryService;

@RestController
public class CountryController {
	
	@Autowired
	private ICountryService countryservice;
	
	//see users
	@GetMapping(path="/register-countries")
	public List<Country> listCountries() {	
		return countryservice.findAll();
	}

}
