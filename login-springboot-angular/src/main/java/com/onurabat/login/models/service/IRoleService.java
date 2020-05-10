package com.onurabat.login.models.service;

import java.util.List;

import com.onurabat.login.models.entity.Role;

public interface IRoleService {
	
	public List<Role> findAll();
	
	public Role findById(Integer id);
	
	public Role save(Role role);
	
	public void delete(Integer id);
	

}
