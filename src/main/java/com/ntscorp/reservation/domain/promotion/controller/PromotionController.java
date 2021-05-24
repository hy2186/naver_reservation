package com.ntscorp.reservation.domain.promotion.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ntscorp.reservation.domain.promotion.dto.Promotion;
import com.ntscorp.reservation.domain.promotion.dto.PromotionResponse;
import com.ntscorp.reservation.domain.promotion.service.PromotionService;

@RestController
public class PromotionController {
	private final PromotionService promotionService;
	
	public PromotionController(PromotionService promotionService) {
		this.promotionService = promotionService;
	}
	
	@GetMapping("/api/promotions")
	public PromotionResponse getPromotionAll() {
		List<Promotion> promotionList = promotionService.getPromotionAll();

		PromotionResponse promotionResponse = PromotionResponse.builder()
			.items(promotionList)
			.build();

		return promotionResponse;
	}
}
