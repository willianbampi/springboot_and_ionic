package com.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.cursomc.domain.Client;
import com.cursomc.domain.Order;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Order order);
	
	void sendEmail(SimpleMailMessage message);
	
	void sendOrderConfirmationHtmlEmail(Order order);
	
	void sendHtmlEmail(MimeMessage message);

	void sendNewPasswordEmail(Client client, String newPassword);

}