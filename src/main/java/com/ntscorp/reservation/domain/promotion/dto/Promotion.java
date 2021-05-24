package com.ntscorp.reservation.domain.promotion.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Promotion {
	private int id;
	private int productId;
	private int fileId;
	private String productDescription;
	private String placeName;
}
