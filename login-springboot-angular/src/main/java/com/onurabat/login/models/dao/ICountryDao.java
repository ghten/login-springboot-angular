package com.onurabat.login.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onurabat.login.models.entity.Country;

@Repository
public interface ICountryDao extends JpaRepository<Country, Integer> {
	
}
