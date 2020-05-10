package com.onurabat.login.models.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onurabat.login.models.dao.ILangDao;
import com.onurabat.login.models.entity.Country;
import com.onurabat.login.models.entity.Lang;
import com.onurabat.login.models.service.ILangService;

@Service
public class LangServiceImpl implements ILangService {

	@Autowired
    private ILangDao langDao;

	@Override
	@Transactional(readOnly = true)
	public Lang getLang(String code) {
		return langDao.findByCode(code);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Lang> findAll() {
		return langDao.findAll();
	}

}
