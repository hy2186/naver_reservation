package com.ntscorp.reservation.domain.promotion.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PromotionResponse {
	private List<Promotion> items;
	private int size;

	@Builder
	public PromotionResponse(List<Promotion> items) {
		this.items = items;
		this.size = items.size();
	}
}
