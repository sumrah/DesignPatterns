package com.nagarro.handson.query.api.projection;

import java.util.List;
import java.util.stream.Collectors;

import com.nagarro.handson.command.api.entity.Product;
import com.nagarro.handson.command.api.entity.ProductRepository;
import com.nagarro.handson.command.api.model.ProductModel;

import com.nagarro.handson.query.api.queries.GetProductByIdQuery;
import com.nagarro.handson.query.api.queries.GetProductsQuery;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;
// this class is queryHandler and communicates with db using repo
@Component
public class ProductProjection {

	// query is handled by projection and create the event that can send u data
	private ProductRepository productRepo;

	public ProductProjection(ProductRepository productRepo) {
		super();
		this.productRepo = productRepo;
	}

	@QueryHandler
	public List<ProductModel> handle(GetProductsQuery getProductQuery) {

		List<Product> products = productRepo.findAll();
		List<ProductModel> productList = products.stream().map(product -> ProductModel.builder()
				.name(product.getName()).price(product.getPrice())
				.quantity(product.getQuantity())
				.build()).collect(Collectors.toList());
		return productList;
	}
	
//	@QueryHandler
//	public Product getProductByIdHandler(GetProductByIdQuery getProductById) {
//	  return productRepo.findById(getProductById.getProductId())
//			  .orElseThrow(()-> new RuntimeException("Not found"));
//	}
}
