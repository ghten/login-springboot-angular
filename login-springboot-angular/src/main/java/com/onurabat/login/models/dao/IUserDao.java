package com.onurabat.login.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onurabat.login.models.entity.UserLocale;

@Repository
public interface IUserDao extends JpaRepository<UserLocale, Long>{
	
	public UserLocale findByEmail(String email);
	
}

