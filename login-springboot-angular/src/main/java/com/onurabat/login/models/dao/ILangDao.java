package com.onurabat.login.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onurabat.login.models.entity.Lang;

@Repository
public interface ILangDao extends JpaRepository<Lang, Integer> {
	
	public Lang findByCode(String code);

}
