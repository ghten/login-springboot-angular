package com.onurabat.login.models.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onurabat.login.dto.registerDto;
import com.onurabat.login.exceptions.NotFoundException;
import com.onurabat.login.models.dao.IUserDao;
import com.onurabat.login.models.dao.ICompanyDao;
import com.onurabat.login.models.dao.ICountryDao;
import com.onurabat.login.models.dao.IRoleDao;
import com.onurabat.login.models.entity.Company;
import com.onurabat.login.models.entity.Country;
import com.onurabat.login.models.entity.Role;
import com.onurabat.login.models.entity.UserLocale;
import com.onurabat.login.models.service.ICompanyService;
import com.onurabat.login.models.service.IRegisterService;
import com.onurabat.login.models.service.IUserService;

@Service
public class RegisterServiceImpl implements IRegisterService {

	@Autowired
	private ICompanyService companyservice;

	@Autowired
	private IUserService userservice;
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private ICompanyDao companyDao;
	
	@Autowired
	private ICountryDao countryDao;
	
	@Autowired
	private IRoleDao roleDao;
	
	private int ok = 0;
	private int otherCompany = 1;
	private int sameCompany = 2;

	@Transactional
	public ResponseEntity<?> Register(registerDto register) {
		
		Map<String, Object> response = new HashMap<>();
		Company companylocale = null;
		UserLocale userlocale = null;
		
		UserLocale userclient = new UserLocale();
		Company data = new Company();
		
		userclient.setEmail(register.getEmail());
		userclient.setName(register.getName());
		userclient.setSurname(register.getSurname());
		userclient.setPassword(register.getPassword());
		
		Optional<Role> role = roleDao.findById(register.getRole());
		Set<Role> Srole = new HashSet<>();
		Srole.add(role.get());
		userclient.setRole(Srole);
		
		data.setAddress(register.getAddress());
		data.setCity(register.getCity());
		data.setName(register.getCompanyName());
		data.setPostcode(register.getPostcode());
		data.setState(register.getState());
		data.setVat(register.getVat());
		
		Country country = new Country();
		country.setId(register.getCountryId());
		data.setCountry(country);

        companylocale = companyDao.findByVat(data.getVat());
        userlocale = userDao.findByEmail(userclient.getEmail());
        
         try {
        		if (userlocale == null && companylocale == null) {
        			return this.RegisterFull(userclient, data);
           		}
        		
        if (userlocale != null && companylocale == null) {
            if(userlocale.getCompany().getId() != null) {
                return new ResponseEntity<Integer>(otherCompany, HttpStatus.OK);
            } else {
               return this.UserAndsetCompany(userlocale, data);
            }               
        } 
        
       if (userlocale == null && companylocale != null) {
			userlocale.setCompany(companylocale);
			userservice.save(userlocale);		
			return new ResponseEntity<Integer>(this.ok, HttpStatus.OK);          
        } 
	
       if (userlocale != null && companylocale != null){
            if(userlocale.getCompany().getId() == null){
    			userlocale.setCompany(companylocale);
    			userservice.save(userlocale);		
    			return new ResponseEntity<Integer>(this.ok, HttpStatus.OK);        
            } else if (userlocale.getCompany().getId() == companylocale.getId()) {
              return new ResponseEntity<Integer>(this.sameCompany, HttpStatus.OK);
            } else {
            	return new ResponseEntity<Integer>(otherCompany, HttpStatus.OK);
            }
        }
        } catch(Exception ex) {
			response.put("mensaje", "Error in register");
			response.put("error", "Error:" + ex.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}		

       return new ResponseEntity<Integer>(this.ok, HttpStatus.OK);
	}	
	
	private ResponseEntity<Integer> RegisterFull(UserLocale userlocale, Company comp) {
		try { 
			Company company = companyservice.save(comp);
			userlocale.setCompany(company);
			userservice.save(userlocale);
		}catch(Exception ex) {
			throw new NotFoundException("Error in database: "+ex);
		}
		return new ResponseEntity<Integer>(this.ok, HttpStatus.OK);				
	}
	
	private ResponseEntity<Integer> UserAndsetCompany(UserLocale userlocale, Company companylocale) {
		try { 
			Company company = companyservice.save(companylocale);
			userlocale.setCompany(company);
			userservice.save(userlocale);
		}catch(Exception ex) {
			throw new NotFoundException("Error in database: "+ex);
		}
		return new ResponseEntity<Integer>(this.ok, HttpStatus.OK);					
	}
}
