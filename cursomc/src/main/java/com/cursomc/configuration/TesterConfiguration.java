package com.cursomc.configuration;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.cursomc.services.EmailService;
import com.cursomc.services.MockEmailService;

@Configuration
@Profile("test")
public class TesterConfiguration {
	
	@Autowired
	private DatabaseService databaseService;
	
	@Bean
	public boolean instatiateDatabase() throws ParseException {
		databaseService.instantiateDatabase();
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}

}