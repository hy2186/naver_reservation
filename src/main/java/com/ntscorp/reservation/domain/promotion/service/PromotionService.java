package com.ntscorp.reservation.domain.promotion.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ntscorp.reservation.domain.promotion.dto.Promotion;
import com.ntscorp.reservation.domain.promotion.mapper.PromotionMapper;

@Service
public class PromotionService {
	private final PromotionMapper promotionMapper;
	
	public PromotionService(PromotionMapper promotionMapper) {
		this.promotionMapper = promotionMapper;
	}

	public List<Promotion> getPromotionAll() {
		return promotionMapper.selectPromotionAll();
	}
}
