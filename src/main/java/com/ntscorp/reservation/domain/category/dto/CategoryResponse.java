package com.ntscorp.reservation.domain.category.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CategoryResponse {
	private List<Category> items;
	private int count;
	
	@Builder
	public CategoryResponse(List<Category> items) {
		this.items = items;
		this.count = items.size();
	}
}
