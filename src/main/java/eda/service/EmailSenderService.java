package eda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
	@Autowired
	JavaMailSenderImpl javaMailSenderImpl;
	
	public void sendEmailContact(String userEmail,String message,String userName, String phoneNo) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo("sagundevyt@gmail.com");
		simpleMailMessage.setSubject("Message From Client "+userName);
		simpleMailMessage.setText("You have been contacted by "+
		userName+".\nTo make an inquiry about "+message+".\n\n\nGet back to client using.\n"+
		"Email: "+userEmail+"\n"+"Phone Number: "+phoneNo);
		javaMailSenderImpl.send(simpleMailMessage);
	}
}
