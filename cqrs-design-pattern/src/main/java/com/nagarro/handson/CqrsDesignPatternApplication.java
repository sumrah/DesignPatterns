package com.nagarro.handson;

import com.nagarro.handson.command.api.exception.ProductServiceEventErrorsHandler;

import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CqrsDesignPatternApplication {

	public static void main(String[] args) {
		SpringApplication.run(CqrsDesignPatternApplication.class, args);
	}

	@Autowired
	public void configure(EventProcessingConfigurer configurer) {
		configurer.registerListenerInvocationErrorHandler("product",
				configuration -> new ProductServiceEventErrorsHandler());
	}
}
