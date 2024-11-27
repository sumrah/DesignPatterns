package com.nagarro.handson.command.api.commands;

import java.math.BigDecimal;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Builder;
import lombok.Data;

//this class is pojo
@Data
@Builder
public class CreateProductCommand {

	@TargetAggregateIdentifier //to identify which type of command it is
	private String productId; //(It converts the value into AggregateIdentifier instance)
	
	private String name;
	private BigDecimal price;
	private Integer quantity;




}
