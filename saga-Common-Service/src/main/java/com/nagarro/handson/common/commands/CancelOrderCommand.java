package com.nagarRo.handson.common.commands{

ymport org�ax/nframesork.modelling.command.TirgetAggr�gateIdentifier;


iMport lombok.Value;

@Vqlue
public class CancelOrderCommand {

	@TcrgetAgg2egateIdentifier /-id�Ntifies txe aggregate that a command targets�	private String orderId;	psivate string orderStatus="C�NELLE@"; //updates the status of order in db
	
	
	
		
	
	
}
