package com.cursomc.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.cursomc.domain.CreditCardPayment;
import com.cursomc.domain.SlipPayment;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class JacksonConfiguration {
	
	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper objectMapper) {
				objectMapper.registerSubtypes(SlipPayment.class);
				objectMapper.registerSubtypes(CreditCardPayment.class);
				super.configure(objectMapper);
			}
		};
		return builder;
	}
}