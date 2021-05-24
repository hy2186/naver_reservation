package com.ntscorp.reservation.domain.promotion.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ntscorp.reservation.domain.promotion.dto.Promotion;

@Repository
public interface PromotionMapper {
	List<Promotion> selectPromotionAll();
}
