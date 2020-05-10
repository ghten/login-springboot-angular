package com.onurabat.login.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onurabat.login.models.entity.Company;

@Repository
public interface ICompanyDao extends JpaRepository<Company, Long> {
	
	public Company findByVat(String vat);
}
