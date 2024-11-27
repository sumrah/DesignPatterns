package com.nagarro.handson.order.command.api.aggregate;

import com.nagarro.handson.common.commands.CancelOrderCommand;
import com.nagarro.handson.common.commands.CompleteOrderCommand;
import com.nagarro.handson.common.events.OrderCancelledEvent;
import com.nagarro.handson.common.events.OrderCompletedEvent;
import com.nagarro.handson.order.command.api.command.CreateOrderCommand;
import com.nagarro.handson.order.command.api.events.OrderCreatedEvent;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class OrderAggregate {

	@AggregateIdentifier
	private String orderId;
	private String productId;
	private String userId;
	private String addressId;
	private Integer quantity;
	private String orderStatus;
	public OrderAggregate() {
		super();
		//TODO Auto-generated constructor stub
	}
	//we create saga in the service that first start's the command
	
	@CommandHandler
	public OrderAggregate(CreateOrderCommand createOrderCommand) {
		//The aggregate validates the command and creates an event for it
		OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent();
		BeanUtils.copyProperties(createOrderCommand, orderCreatedEvent);
		AggregateLifecycle.apply(orderCreatedEvent);//send data to axon server
	}
	
	//whatever value we receive in our event ,need to be updated in aggregate therefore this function
	
	@EventSourcingHandler
	public void onChange(OrderCreatedEvent eventUpdate) {
		this.addressId=eventUpdate.getAddressId();
		this.orderId=eventUpdate.getOrderId();
		this.orderStatus=eventUpdate.getOrderStatus();
		this.productId=eventUpdate.getProductId();
		this.userId=eventUpdate.getUserId();
		this.quantity=eventUpdate.getQuantity();
	}
	
	@CommandHandler
	public void handle(CompleteOrderCommand completeOrderCommand ) {
		//validate the command
		//publish OrderCompleted Event
		 OrderCompletedEvent orderCompletedEvent
         = OrderCompletedEvent.builder()
         .orderStatus(completeOrderCommand.getOrderStatus())
         .orderId(completeOrderCommand.getOrderId())
         .build();
		 AggregateLifecycle.apply(orderCompletedEvent);
	}
	
	@EventSourcingHandler
	public void on(OrderCompletedEvent event) {
		 this.orderStatus = event.getOrderStatus();
	}
	
	@CommandHandler
	public void handle(CancelOrderCommand cancelOrderCommand ) {
		OrderCancelledEvent orderCancelledEvent = new OrderCancelledEvent();
		BeanUtils.copyProperties(cancelOrderCommand, orderCancelledEvent);
		AggregateLifecycle.apply(orderCancelledEvent);
	}
	
	@EventSourcingHandler 
	public void on(OrderCancelledEvent event) {//whatever values are updating we need to handle that
		 this.orderStatus = event.getOrderStatus();
	}
}
