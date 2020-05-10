package com.onurabat.login.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onurabat.login.models.entity.Lang;
import com.onurabat.login.models.service.ILangService;


@RestController
public class LangController {
	
	@Autowired
	private ILangService langservice;
	
	//see users
	@GetMapping(path="/register-langs")
	public List<Lang> listLangs() {	
		return langservice.findAll();
	}
}
