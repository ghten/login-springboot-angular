package com.onurabat.login.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onurabat.login.models.entity.Role;

@Repository
public interface IRoleDao extends JpaRepository<Role, Integer> {
	
}
