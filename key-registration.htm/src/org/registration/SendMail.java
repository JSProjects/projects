package org.registration;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SendMail implements Job {
	
	static KeyDAO dao = new KeyDAO();

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		  try {
		List<Key> userlist=dao.fetchforMail();
		 String params = null;
		 

	      // Get system properties
	    Properties properties = System.getProperties();
	    properties.setProperty("mail.smtp.host", "mx2.aerohive.com");
	    properties.setProperty("mail.smtp.port","25");
	    properties.setProperty("mail.transport.protocol", "smtp");
	    properties.setProperty("mail.smtp.starttls.enable", "true");
	    properties.setProperty("mail.smtp.ssl.trust", "mx2.aerohive.com");
	    properties.setProperty("mail.debug", "true");

  
	    Session session = Session.getDefaultInstance(properties);
	    
		System.out.println("Users Found :" +userlist.size());
		for(Key key:userlist){
			
		     
		         // Create a default MimeMessage object.
		         MimeMessage message = new MimeMessage(session);

		         // Set From: header field of the header.
		         message.setFrom(new InternetAddress("entitlements@aerohive.com"));

		         // Set To: header field of the header.
		         message.addRecipient(Message.RecipientType.TO, new InternetAddress(key.getEmail()));

		         // Set Subject: header field
		         message.setSubject("Request for Free AP NG Entitlement Key");

				 message.setContent("<p>Thank you for creating Entitlement Key for <b>AP "+key.getSerialNumber()+ "</b>.Your Entitlement Key is as follows:<b>" +key.getEntitlementkey()+"</b>."
				 		+ "To apply your Entitlement Key you must login to your HiveManager NG instance with your existing credentials (https://cloud.aerohive.com)."
				 		+ "Once logged in, you can apply your Entitlement Key by clicking on the gear icon on the tool bar in the upper right corner.</p>"
				 		+"<p>If you should have questions, or need assistance, please send an email to: freeap@aerohive.com. </p>","text/html");
				
		         // Send message
		         Transport.send(message);
		         
		         dao.updateUser(key);
		         
		}
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

						
	/*		 String emailTo = user.getEmail();
			 String subject = "Request for Free AP NG Entitlement Key";
			
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
			    
			    userDAO.updateUser(user);*/


}
}