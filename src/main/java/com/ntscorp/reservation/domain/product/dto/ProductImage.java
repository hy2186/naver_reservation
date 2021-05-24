package com.ntscorp.reservation.domain.product.dto;

import com.ntscorp.reservation.common.type.ImageType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductImage {
	private int id;
	private int productId;
	private int fileId;
	private ImageType type;
}
