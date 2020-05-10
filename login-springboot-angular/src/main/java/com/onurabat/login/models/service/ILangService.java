package com.onurabat.login.models.service;

import java.util.List;

import com.onurabat.login.models.entity.Lang;

public interface ILangService {
	
	public Lang getLang(String code);
	
	public List<Lang> findAll();
}
