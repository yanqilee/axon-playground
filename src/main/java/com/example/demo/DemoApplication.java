package com.example.demo;

import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.tokenstore.inmemory.InMemoryTokenStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	// This allows event replay to happen on app startup
	@Autowired
	public void configureInMemoryTokenStore(EventProcessingConfigurer configurer) {
		configurer.registerTokenStore(configuration -> new InMemoryTokenStore());
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
