package com.nagarro.handson.order.command.api.repository;

import com.nagarro.handson.order.command.api.entity.OrderEntity;

import org.springframework.data.jpa.repository.JpaRepository;

//interface that provides various common methods for hibernate
public interface OrderRepository extends JpaRepository<OrderEntity,String> {

}
