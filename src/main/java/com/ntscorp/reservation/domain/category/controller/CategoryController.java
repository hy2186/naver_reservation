package com.ntscorp.reservation.domain.category.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ntscorp.reservation.domain.category.dto.Category;
import com.ntscorp.reservation.domain.category.dto.CategoryResponse;
import com.ntscorp.reservation.domain.category.service.CategoryService;

@RestController
public class CategoryController {
	private final CategoryService categoryService;
	
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping("/api/categories")
	public CategoryResponse getCategoryList() {
		List<Category> categoryList = categoryService.getCategoryList();

		CategoryResponse categoryResponse = CategoryResponse.builder()
			.items(categoryList)
			.build();
		
		return categoryResponse;
	}
}
