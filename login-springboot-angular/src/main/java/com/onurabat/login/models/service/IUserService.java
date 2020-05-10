package com.onurabat.login.models.service;

import java.util.List;

import com.onurabat.login.models.entity.Company;
import com.onurabat.login.models.entity.UserLocale;

public interface IUserService {
	
	public UserLocale findByEmail(String email);
	
	public List<UserLocale> findAll(Long id);
	
	public UserLocale findById(Long id);
	
	public UserLocale save(UserLocale user);
	
	public UserLocale update(UserLocale user);
	
	public void delete(Long id);
		
}
