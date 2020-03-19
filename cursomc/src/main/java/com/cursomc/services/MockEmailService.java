package com.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService {
	
	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

	@Override
	public void sendEmail(SimpleMailMessage message) {
		LOG.info("Simulating email send...");
		LOG.info(message.toString());
		LOG.info("Email sent...");
	}

	@Override
	public void sendHtmlEmail(MimeMessage message) {
		LOG.info("Simulating HTML email send...");
		LOG.info(message.toString());
		LOG.info("HTML email sent...");
	}
	
}