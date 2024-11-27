package com.nagarro.handson.order.command.api.controller;

import java.util.UUID;

import com.nagarro.handson.order.command.api.command.CreateOrderCommand;
import com.nagarro.handson.order.command.api.model.Order;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderCommandController {
//controller creates command and send it to command gateway and then after commandgateway it to goes to aggregate
	private CommandGateway commandGateway;

	public OrderCommandController(CommandGateway commandGateway) {
		super();
		this.commandGateway = commandGateway;
	}

	@PostMapping
	public String createOrder(@RequestBody Order orderModel) {

		String orderId = UUID.randomUUID().toString();

		CreateOrderCommand createOrderCommand = CreateOrderCommand.builder().orderId(orderId)
				.addressId(orderModel.getAddressId()).productId(orderModel.getProductId())
				.quantity(orderModel.getQuantity()).orderStatus("Created").build();
		commandGateway.sendAndWait(createOrderCommand);

		return "Order created";

	}

}
