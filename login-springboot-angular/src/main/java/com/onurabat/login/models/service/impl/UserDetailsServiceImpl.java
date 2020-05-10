package com.onurabat.login.models.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onurabat.login.models.dao.IUserDao;
import com.onurabat.login.models.entity.UserLocale;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(UserDetailsService.class);
	
	@Autowired
	private IUserDao userDao;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		UserLocale userlocale = userDao.findByEmail(email);
		
		if(userlocale == null) {
			logger.error("Error in login, the email '" + email + "' doesn't exist");
			throw new UsernameNotFoundException("Error in login, the email '" + email + "' doesn't exist");
		}
		List<GrantedAuthority> authorities =  userlocale.getRole()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.peek(authority -> logger.info("Role: " + authority.getAuthority()))
				.collect(Collectors.toList());
			
		return new User(email, userlocale.getPassword(), userlocale.getEnabled(), true, true, true, authorities);
	}
}
