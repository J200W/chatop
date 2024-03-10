package com.chatop.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.chatop.api")
public class ApiApplication implements CommandLineRunner {

	/**
	 * The custom properties
	 */
	@Autowired
	private CustomProperties customProperties;

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("API URL: " + customProperties.getApiUrl());
	}

}
