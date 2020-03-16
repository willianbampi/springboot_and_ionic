package com.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.cursomc.domain.Order;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Order order);
	
	void sendEmail(SimpleMailMessage message);

}