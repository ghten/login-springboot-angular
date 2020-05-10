package com.onurabat.login.language;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class CustomLocaleResolver {
	
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource rs = new ResourceBundleMessageSource();
	    rs.setBasename("i18n/messages");
	    rs.setDefaultEncoding("UTF-8");
	    rs.setUseCodeAsDefaultMessage(true);
	    return rs;
	}
}
