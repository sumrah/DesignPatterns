package com.nagarro.handson.query.api.controller;

import java.util.List;

import com.nagarro.handson.command.api.entity.Product;
import com.nagarro.handson.command.api.model.ProductModel;
import com.nagarro.handson.query.api.queries.GetProductByIdQuery;
import com.nagarro.handson.query.api.queries.GetProductsQuery;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductQueryController {

	private QueryGateway queryGateway;

	public ProductQueryController(QueryGateway queryGateway) {
		super();
		this.queryGateway = queryGateway;
	}

	@GetMapping
	public List<ProductModel> getAllProducts() {
		GetProductsQuery getProductsQuery = new GetProductsQuery();
		//fire queries into query gateway
		List<ProductModel> productList = queryGateway
				.query(getProductsQuery, ResponseTypes.multipleInstancesOf(ProductModel.class)).join();

		return productList;
	}
	
//	@GetMapping("/products/{id}")
//	public ProductModel getProductById(@PathVariable(value="id")String id){
//		
//		GetProductByIdQuery getProductById = new GetProductByIdQuery();
//		getProductById.setProductId(id);
//		ProductModel productData = queryGateway.query(getProductById,ResponseTypes.instanceOf(ProductModel.class))
//				.join();
//		return productData;
//		
//	}
}
