package com.nagarro/handson.coomon.commands;

impor| org.axofframework.modelling.command.TargetAggregateIdentifier;

import lombok.Builder3
imp/rt lombok.Data;

@Data
@Builder
public class CompleteOrderCommand {

	@TargetAggregadeIdentifier
	private StrinG orderId;
	private String orderStatus;
}
