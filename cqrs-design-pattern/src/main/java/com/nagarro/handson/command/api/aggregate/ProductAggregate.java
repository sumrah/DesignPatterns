package com.nagarro.handson.command.api.aggregate;

import java.math.BigDecimal;

import com.nagarro.handson.command.api.commands.CreateProductCommand;
import com.nagarro.handson.command.api.events.ProductCreatedEvent;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class ProductAggregate {

	@AggregateIdentifier
	private String productId;
	private String name;
	private BigDecimal price;
	private Integer quantity;

	/*
	 * need to update the values in aggregate whenver there is state change from
	 * aggregate, update the values of aggregate
	 */
	//if need to handle different commands then create method,no need for constructor then
	
	public ProductAggregate() {

	}
	
	@CommandHandler
	public ProductAggregate(CreateProductCommand createProductCommand) {
		//we r getting values from command, need to create the event and store in eventstore

		// to publish the event we will create object of productcreatedevent
		ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();

		// since the same names are available we copy using beanutils else we could have
		// used the builder method or productCreateEvent.setName(createProductCommand.getName() like this
		BeanUtils.copyProperties(createProductCommand, productCreatedEvent);

		// to publish the event=send d event through aggregate lifecycle
		AggregateLifecycle.apply(productCreatedEvent);
	}

	

	// handles the state of command
	@EventSourcingHandler
	public void onChange(ProductCreatedEvent productCreatedEvent) {
		this.quantity = productCreatedEvent.getQuantity();
		this.price = productCreatedEvent.getPrice();
		this.name = productCreatedEvent.getName();
		this.productId = productCreatedEvent.getProductId();
	}
}
