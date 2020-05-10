package com.onurabat.login.models.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onurabat.login.models.dao.IRoleDao;
import com.onurabat.login.models.entity.Role;
import com.onurabat.login.models.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
    private IRoleDao roleDao;

	@Override
	@Transactional(readOnly = true)
	public List<Role> findAll() {
		return roleDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Role findById(Integer id) {
		return roleDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public Role save(Role role) {
		return roleDao.save(role);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		roleDao.deleteById(id);
	}


}
