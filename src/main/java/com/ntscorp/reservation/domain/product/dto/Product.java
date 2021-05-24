package com.ntscorp.reservation.domain.product.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
	private int displayInfoId;
	private String placeName;
	private String productContent;
	private String productDescription;
	private int productId;
	private int imageFileId;
}
