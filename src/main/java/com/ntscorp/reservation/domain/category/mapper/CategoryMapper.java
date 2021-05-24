package com.ntscorp.reservation.domain.category.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ntscorp.reservation.domain.category.dto.Category;

@Repository
public interface CategoryMapper {
	List<Category> selectCategoryAll();
}
