package com.cursomc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cursomc.services.AmazonS3Service;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private AmazonS3Service amazonS3Service;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		amazonS3Service.uploadFile("C:\\temp\\fotos\\ana.jpg");
	}

}