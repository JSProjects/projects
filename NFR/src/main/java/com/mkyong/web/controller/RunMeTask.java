package com.mkyong.web.controller;

import java.net.PasswordAuthentication;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import sun.rmi.transport.Transport;

import javax.mail.internet.MimeMessage;
import org.springframework.mail.MailParseException;
import com.model.PartnerHistory;
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
		
		try{
			
		
		Map<String,List<PartnerHistory>> userlist=userDAO.fetchforMail();
		System.out.println("Users Found :" +userlist.size());
		 String params = null;
		for (Entry<String, List<PartnerHistory>> entry : userlist.entrySet()) {
			System.out.println("Key : " + entry.getKey() + " Value : "
				+ entry.getValue());
			
			
			 String emailTo = entry.getKey();
			 String subject = "Request for NFR Entitlement Key";
			
			 StringBuilder builder = new StringBuilder();
			 builder.append("<!DOCTYPE html>");
		     builder.append("<html lang=\"en\">");
		     builder.append("<head><title>Thank You for creating NFR Key</title></head>"); 
			 builder.append("<body><h2 style=\"font-size: 14px; line-height: 1.1em\"> Thank You for creating NFR Key. Your key details are listed as below</h2>");
			 builder.append("<table border=\"1\" cellspacing=\"1\" cellpadding=\"5\">");
			   builder.append("<tr>");
			   builder.append("<td>  Serial Number </td>");
			   builder.append("<td> Entitlement Key </td>"); 
			   builder.append("<td> HiveManager ID </td>"); 
			   builder.append("</tr>");
			 for (PartnerHistory partner: entry.getValue()) {
			   builder.append("<tr>");
			   builder.append("<td>" + partner.getSerialnumber() + "</td>"); // First column
			   builder.append("<td>" + partner.getEk() + "</td>"); // Second column
			   builder.append("<td>" + partner.getHmId() + "</td>"); // Thired column
			   builder.append("</tr>"); // End of i'th Row
			 }
			 builder.append("</table></body>");
			 builder.append("</html>");
			 String html = builder.toString();
			 System.out.println(html);
			
			 
		/*	 String text = VelocityEngineUtils.mergeTemplateIntoString(
						velocityEngine, "/resources/templates/template.vm", "utf-8", model);*/
			
			//final String userMessage = LocalizedTextUtil.findDefaultText(message, actionSupport.getLocale(), new String[] { params });
			  	//org.springframework.mail.SimpleMailMessage message1 = new org.springframework.mail.SimpleMailMessage();
			   
			 MimeMessage message = mailSender.createMimeMessage();
			 

			// use the true flag to indicate you need a multipart message
			 org.springframework.mail.javamail.MimeMessageHelper helper = new org.springframework.mail.javamail.MimeMessageHelper(message, true);
			 helper.setTo(emailTo);
			 helper.setSubject(subject);
			// use the true flag to indicate the text included is HTML
			  helper.setText(html, true);
			  helper.setFrom("entitlements@aerohive.com");
			  mailSender.send(message);
			 
/*			   message1.setTo(emailTo);
			    message1.setSubject(subject);
			    message1.setText(html,"text/html; charset=ISO-8859-1");
			    message1.setFrom("entitlements@aerohive.com");
		       // System.out.println("To :" +emailTo + " Subject :" + subject + " body : "+ body);
			    mailSender.send(message1);*/
			    
			   userDAO.updateUser(userlist);
			   
		}
	}catch(MessagingException e){
		throw new MailParseException(e);
	}
}
}