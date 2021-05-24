package com.ntscorp.reservation.domain.product.controller;

import java.util.List;

import javax.validation.constraints.PositiveOrZero;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ntscorp.reservation.domain.product.dto.Product;
import com.ntscorp.reservation.domain.product.dto.ProductResponse;
import com.ntscorp.reservation.domain.product.service.ProductService;

@RestController
@Validated
public class ProductController {
	private final ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("/api/products")
	public ProductResponse getProductList(@RequestParam @PositiveOrZero int categoryId, 
		@RequestParam(defaultValue = "0") @PositiveOrZero int start) {
		List<Product> productList = productService.getProductList(categoryId, start);
		int totalCount = productService.getProductCount(categoryId);

		ProductResponse productResponse = ProductResponse.builder()
			.items(productList)
			.totalCount(totalCount)
			.build();

		return productResponse;
	}
}
