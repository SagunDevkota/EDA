package eda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eda.dto.ContactInfo;
import eda.dto.UserInfo;
import eda.service.EmailSenderService;

@RestController
public class RestfulController {
	@Autowired
	EmailSenderService emailSenderService;
	
	@RequestMapping(value="/sendEmail")
	public String sendEmail(@ModelAttribute ContactInfo contactInfo) {
		System.out.println(contactInfo);
		try {
			emailSenderService.sendEmailContact(contactInfo.getEmail(), contactInfo.getMessage(), contactInfo.getName(),contactInfo.getPhoneNo());
			return "Email sent Successfully";
		}catch (Exception e) {
			return "Could not send email";
		}
		
	}
}
