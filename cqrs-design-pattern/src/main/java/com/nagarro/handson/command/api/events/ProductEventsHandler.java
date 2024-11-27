package com.nagarro.handson.command.api.events;

import com.nagarro.handson.command.api.entity.Product;
import com.nagarro.handson.command.api.entity.ProductRepository;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;

import org.springframework.stereotype.Component;

//class to write database logic mostly
@Component
@ProcessingGroup("product")//for exception handling
public class ProductEventsHandler {

	
	private ProductRepository productRepo;
	

	public ProductEventsHandler(ProductRepository productRepo) {
		
		this.productRepo = productRepo;
	}

	@EventHandler
	public void on(ProductCreatedEvent event)throws Exception {
		//whatever payload we are getting will be stored in database
		Product product = new Product();
		BeanUtils.copyProperties(event, product);
		productRepo.save(product);
		
	}
	
	@ExceptionHandler
    public void handle(Exception exception) throws Exception {
        throw exception;
    }
	
	
}
