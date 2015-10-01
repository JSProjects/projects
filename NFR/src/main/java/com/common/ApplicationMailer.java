package com.common;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
 
@Service("mailService")
public class ApplicationMailer 
{
    @Autowired
	private JavaMailSender mailSender;
 
    
    /**
     * This method will send compose and send the message 
     * */
    public void sendMail(final String to, final String subject, final String body) 
    {
      /*  SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("entitlements@aerohive.com");
        System.out.println("To :" +to + " Subject :" + subject + " body : "+ body);
        mailSender.send(message);*/
    	
 
    	mailSender.send(new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper messageHelper = new MimeMessageHelper(
						mimeMessage, true, "UTF-8");
				messageHelper.setFrom("entitlements@aerohive.com");
				messageHelper.setTo(to);
				messageHelper.setSubject(subject);
				messageHelper.setText(body);
				
			/*	// determines if there is an upload file, attach it to the e-mail
				String attachName = attachFile.getOriginalFilename();
				if (!attachFile.equals("")) {

					messageHelper.addAttachment(attachName, new InputStreamSource() {
						
						@Override
						public InputStream getInputStream() throws IOException {
							return attachFile.getInputStream();
						}
					});
				}*/
				
			}

		});
		System.out.println("Mail Sent");
    }
}
