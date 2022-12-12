package com.bookstore.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	public void sendEmail(String msg, String email) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();

		mailMessage.setTo(email);
		mailMessage.setSubject("Notification from Look Inna Book");
		mailMessage.setText(msg);

		try {
			// emailSender.send(mailMessage);
			System.out.println("Sent an email to: " + email);
		} catch (Exception e) {
			System.out.println("Could not send an email to  " + email + " due to: " + e.getCause());
		}
	}
}
