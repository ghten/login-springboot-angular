package com.onurabat.login.controllers;

import java.awt.PageAttributes.MediaType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.onurabat.login.dto.registerDto;
import com.onurabat.login.exceptions.NotFoundException;

import com.onurabat.login.models.entity.Company;
import com.onurabat.login.models.entity.UserLocale;

import com.onurabat.login.models.service.IRegisterService;
import com.onurabat.login.models.service.IUserService;



@RestController
public class UserController {
	
	@Value("${url.angular}")
	private String urlAngular;
	
	@Autowired
	private IUserService userservice;
	
	@Autowired
	private IRegisterService registerservice;
		
	@Autowired   
	private MessageSource messageSource;
	
	@Autowired
    private JavaMailSender sender;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@PostMapping(path="/forgot")
	public Boolean forgotPassword(@RequestParam("email") String email, @RequestParam("lang") String lang, @RequestParam("time") String userdate) {

		UserLocale user = userservice.findByEmail(email);

		if(user != null) {
			try { 
				sendEmail(email, lang, userdate,user.getId());
			}catch(Exception ex) {
    	        throw new NotFoundException("Error in sending email: "+ex);
    	    }
			return true;
		}
		return false;
	}	
	
	private void sendEmail(String email, String lang, String userdate, Long id) throws Exception{
		String url=urlAngular + "/restore-password?link=" + userdate + "&line=" + id;
		String text = messageSource.getMessage("message.salutation", null, new Locale(lang))
		+ "<br><br>" + messageSource.getMessage("message.forgot1", null, new Locale(lang))		
		+ " " + "<a href='" + url +"'>" + messageSource.getMessage("message.forgot2", null, new Locale(lang)) + "</a>"
		+ " " + messageSource.getMessage("message.forgot3", null, new Locale(lang)) 
		+ "<br><br>" + messageSource.getMessage("message.forgot4", null, new Locale(lang))
		+ "<br><br>" + messageSource.getMessage("message.thanks", null, new Locale(lang))
		+ "<br>" + messageSource.getMessage("message.firm", null, new Locale(lang));
			
		MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(email);
        
     // Create the message part 
        MimeBodyPart messageBodyPart = new MimeBodyPart();

        // Fill the message
        messageBodyPart.setText(text,"UTF-8","html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        // Put parts in message
        message.setContent(multipart);
        
        //helper.setText(text);
        helper.setSubject(messageSource.getMessage("message.forgotSubject", null, new Locale(lang)));
		sender.send(message);
    }
	
	@PostMapping(path="/change")
	public Boolean changePassword(@RequestParam("password") String password, @RequestParam("id") Long id) {

		UserLocale user = userservice.findById(id);
		
		if(user != null) {
			try { 
				user.setPassword(passwordEncoder.encode(password));
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				Date date = new Date();
				user.setUpdated_at(date);
				userservice.update(user);
			}catch(Exception ex) {
    	        throw new NotFoundException("Error in sending email: "+ex);
    	    }
			return true;
		}
		return false;	
	}
	
	@PostMapping(path="/auth/signup", produces = { "application/json" })
	@ResponseBody
	public  ResponseEntity<?> Register(@RequestBody registerDto register) {
		return registerservice.Register(register);	
	}

}
