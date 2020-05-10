package com.onurabat.login.models.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onurabat.login.models.dao.ICompanyDao;
import com.onurabat.login.models.dao.ICountryDao;

import com.onurabat.login.models.entity.Company;
import com.onurabat.login.models.entity.Country;
import com.onurabat.login.models.service.ICompanyService;

@Service
public class CompanyServiceImpl implements ICompanyService {

	@Autowired
	private ICompanyDao companyDao;
	
	@Autowired
	private ICountryDao countryDao;

	
	@Override
	@Transactional
	public Company save(Company data) {
			
		Company company;
		Country country;
		
		if(data.getId() == null) {
			Long id = System.currentTimeMillis();	
			data.setId(id++);
			company = data;
		}else {
			Optional<Company> companyoptional = companyDao.findById(data.getId());
			company = companyoptional.get();
			
			Optional<Country> countryoptional = countryDao.findById(data.getCountry().getId());
			country = countryoptional.get();
			
			company.setAddress(data.getAddress());
			company.setCity(data.getCity());
			company.setCountry(country);
			company.setName(data.getName());
			company.setPostcode(data.getPostcode());
			company.setState(data.getState());
			company.setVat(data.getVat());
		}		 	
		companyDao.save(company);
	
		return company;
	}
}
