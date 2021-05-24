package com.ntscorp.reservation.domain.category.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ntscorp.reservation.domain.category.dto.Category;
import com.ntscorp.reservation.domain.category.mapper.CategoryMapper;

@Service
public class CategoryService {
	private final CategoryMapper categoryMapper;
	
	public CategoryService(CategoryMapper categoryMapper) {
		this.categoryMapper = categoryMapper;
	}

	public List<Category> getCategoryList() {
		return categoryMapper.selectCategoryAll();
	}
}
