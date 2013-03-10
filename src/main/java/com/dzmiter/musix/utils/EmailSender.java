package com.dzmiter.musix.utils;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailConstants;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {
	
	public void sendEmail(String to, String text) throws EmailException {
		HtmlEmail email = new HtmlEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(587);
        email.setAuthenticator(new DefaultAuthenticator("dzmiter.bezugly@gmail.com", "times8891ojs"));
        email.setStartTLSEnabled(true);
        email.setFrom("dzmiter.bezugly@gmail.com");
        email.setSubject("Registration on Musix");
        email.setCharset(EmailConstants.UTF_8);
        email.setHtmlMsg(text);
        email.addTo(to);
        email.send();
    }
   
}