package com.nagarro.handson.command.api.controller;

import java.util.UUID;


import com.nagarro.handson.command.api.commands.CreateProductCommand;
import com.nagarro.handson.command.api.model.ProductModel;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//when we send anything from command gateway ,it will return completablefuture
@RestController
@RequestMapping("/products")
public class ProductCommandController {

	private CommandGateway commandGateway;

	public ProductCommandController(CommandGateway commandGateway) {

		this.commandGateway = commandGateway;
	}
	
 /*
  * can write like public CompletableFuture<T> addProduct()*/
	@PostMapping
	public String addProduct(@RequestBody ProductModel productModel) {
		// getting data from apis to command
		CreateProductCommand createProductCommand = CreateProductCommand.builder()
				.productId(UUID.randomUUID().toString()).name(productModel.getName())
				.price(productModel.getPrice())
				.quantity(productModel.getQuantity()).build();

		String result = commandGateway.sendAndWait(createProductCommand);
		return result;
	}

}
//CreateProductCommand productCommand = new CreateProductCommand();
//productCommand.setName(productModel.getName());
//productCommand.setPrice(productModel.getPrice());
//productCommand.setProductId(UUID.randomUUID().toString());
//productCommand.setQuantity(productModel.getQuantity());
//
//
