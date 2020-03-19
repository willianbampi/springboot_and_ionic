package com.cursomc.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cursomc.domain.Client;
import com.cursomc.repositories.ClientRepository;
import com.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class AuthorizationService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private EmailService emailService;
	
	private Random random = new Random();
	
	public void sendNewPassword(String email) {
		Client client = clientRepository.findByEmail(email);
		if(client == null) {
			throw new ObjectNotFoundException("Not found email.");
		}
		String newPassword = newPassword();
		client.setPassword(bCryptPasswordEncoder.encode(newPassword));
		clientRepository.save(client);
		emailService.sendNewPasswordEmail(client, newPassword);
	}

	private String newPassword() {
		char[] vetor = new char[10];
		for(int i = 0; i < 10; i++) {
			vetor[i] = randomChar();
		}
		return new String(vetor);
	}

	private char randomChar() {
		int option = random.nextInt(3);
		if(option == 0) {
			return (char) (random.nextInt(10) + 48);
		} else if(option == 1) {
			return (char) (random.nextInt(26) + 65);
		} else {
			return (char) (random.nextInt(26) + 97);
		}
	}
	
}