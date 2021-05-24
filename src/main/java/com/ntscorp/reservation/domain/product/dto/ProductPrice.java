package com.ntscorp.reservation.domain.product.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPrice {
	private LocalDateTime createDate;
	private double discountRate;
	private LocalDateTime modifyDate;
	private int price;
	private String priceTypeName;
	private int productId;
	private int productPriceId;
}
