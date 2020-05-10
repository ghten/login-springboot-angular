package com.onurabat.login.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.onurabat.login.models.entity.UserLocale;
import com.onurabat.login.models.service.IUserService;

@Component
public class InfoAdicionalToken implements TokenEnhancer {
	
	@Autowired
	private IUserService userservice;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		UserLocale user = userservice.findByEmail(authentication.getName());
		
		Map<String, Object> info = new HashMap<>();
		// info.put("adicional_info", "dgh58".concat(authentication.getName()));
		info.put("id", user.getId());
		info.put("name", user.getName());
		info.put("surname", user.getSurname());
		info.put("enabled", user.getEnabled());
		info.put("companyId", user.getCompany());
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		
		return accessToken;
	}

}
