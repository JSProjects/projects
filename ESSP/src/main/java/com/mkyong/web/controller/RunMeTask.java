package com.mkyong.web.controller;

import java.net.PasswordAuthentication;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import sun.rmi.transport.Transport;

import com.model.User;
import com.model.UserDAO;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.LocalizedTextUtil;

public class RunMeTask {
	@Autowired
    private UserDAO userDAO;
	@Autowired
	private JavaMailSender mailSender;
	
	public void printMe() {
		List<User> userlist=userDAO.fetchforMail();
		 String params = null;
		System.out.println("Users Found :" +userlist.size());
		for(User user:userlist){
			
			
			 String emailTo = user.getEmail();
			 String subject = "Request for Entitlement Key";
			
			 String message ="Thank you for creating Entitlement Key for NG on "+user.getHmType()+ "." +
                    "Your Entitlement Key is below \t\n \t\n " +
                   "Entitlement Key :" +user.getEntitlementKey();

			
			//final String userMessage = LocalizedTextUtil.findDefaultText(message, actionSupport.getLocale(), new String[] { params });
			  	org.springframework.mail.SimpleMailMessage message1 = new org.springframework.mail.SimpleMailMessage();
			    message1.setTo(emailTo);
			    message1.setSubject(subject);
			    message1.setText(message);
			    message1.setFrom("entitlements@aerohive.com");
		       // System.out.println("To :" +emailTo + " Subject :" + subject + " body : "+ body);
			    mailSender.send(message1);
			    
			    userDAO.updateUser(user);
		}
	}
}