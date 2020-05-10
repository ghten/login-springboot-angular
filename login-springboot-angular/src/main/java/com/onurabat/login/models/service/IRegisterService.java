package com.onurabat.login.models.service;

import org.springframework.http.ResponseEntity;

import com.onurabat.login.dto.registerDto;

public interface IRegisterService {

	public ResponseEntity<?> Register(registerDto register);
}
