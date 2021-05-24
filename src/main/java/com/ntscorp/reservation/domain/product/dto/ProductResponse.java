package com.ntscorp.reservation.domain.product.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductResponse {
	private List<Product> items;
	private int totalCount;
}
