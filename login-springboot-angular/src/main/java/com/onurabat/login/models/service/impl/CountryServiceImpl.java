package com.onurabat.login.models.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onurabat.login.models.dao.ICountryDao;
import com.onurabat.login.models.entity.Country;
import com.onurabat.login.models.service.ICountryService;

@Service
public class CountryServiceImpl implements ICountryService {

	@Autowired
    private ICountryDao countryDao;

	@Override
	@Transactional(readOnly = true)
	public List<Country> findAll() {
		return countryDao.findAll();
	}

}
