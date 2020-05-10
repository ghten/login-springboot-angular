package com.onurabat.login.models.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onurabat.login.models.dao.IUserDao;
import com.onurabat.login.models.entity.UserLocale;
import com.onurabat.login.models.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
		
	@Override
	@Transactional(readOnly = true)
	public UserLocale findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserLocale> findAll(Long id) {
		List<UserLocale> users = userDao.findAll();
		for (Iterator<UserLocale> iter = users.listIterator(); iter.hasNext(); ) {
		    UserLocale a = iter.next();
		    if (a.getId() == id) {
		        iter.remove();
		    }
		    a.setPassword(null);
		}

		return users;
	}

	@Override
	@Transactional(readOnly = true)
	public UserLocale findById(Long id) {
		return userDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public UserLocale save(UserLocale userclient) {
		UserLocale user;
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
	
		if(userclient.getId() == null) {
			Long id = System.currentTimeMillis();	

			userclient.setId(id);
			userclient.setPassword(passwordEncoder.encode(userclient.getPassword()));
			userclient.setCreated_at(date);
			userclient.setEnabled(true);
			user = userclient;
		}else {
			Optional<UserLocale> useroptional = userDao.findById(userclient.getId());
			user = useroptional.get();
			if(userclient.getPassword() != null && !userclient.getPassword().isEmpty()) {
				user.setPassword(passwordEncoder.encode(userclient.getPassword()));
			}
			
			user.setName(userclient.getName());
			user.setSurname(userclient.getSurname());
			user.setEmail(userclient.getEmail());
			user.setCompany(userclient.getCompany());
			user.setRole(userclient.getRole());
		}	
		
		user.setUpdated_at(date);
		
		userDao.save(user);
		
		return user;
	}

	@Override
	@Transactional
	public UserLocale update(UserLocale user) {
		userDao.save(user);
	
		return user;
	}

	@Override
	@Transactional
	public void delete(Long id) {
		userDao.deleteById(id);		
	}
}
