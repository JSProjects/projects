package com.common;

import java.net.PasswordAuthentication;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import sun.rmi.transport.Transport;

import com.model.User;
import com.model.UserDAO;

public class RunMeTask {
	@Autowired
    private UserDAO userDAO;
	@Autowired
	private JavaMailSender mailSender;
	
	public void printMe() {
		List<User> userlist=userDAO.fetchforMail();
		System.out.println("Users Found :" +userlist.size());
		for(User user:userlist){
			
			
			final String emailTo = user.getEmail();
			final String subject = "Request for Entitlement Key";
			final String message = user.getEntitlementKey();
			  	org.springframework.mail.SimpleMailMessage message1 = new org.springframework.mail.SimpleMailMessage();
			    message1.setTo(emailTo);
			    message1.setSubject(subject);
			    message1.setText(message);
			    message1.setFrom("entitlements@aerohive.com");
		       // System.out.println("To :" +emailTo + " Subject :" + subject + " body : "+ body);
			    mailSender.send(message1);
		}
	}
}