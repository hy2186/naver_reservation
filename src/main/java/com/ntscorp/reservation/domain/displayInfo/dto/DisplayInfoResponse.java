package com.ntscorp.reservation.domain.displayInfo.dto;

import java.util.List;

import com.ntscorp.reservation.domain.comment.dto.Comment;
import com.ntscorp.reservation.domain.product.dto.ProductImage;
import com.ntscorp.reservation.domain.product.dto.ProductPrice;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DisplayInfoResponse {
	private double averageScore;
	private int commentCount;
	private List<Comment> comments;
	private DisplayInfo displayInfo;
	private DisplayInfoImage displayInfoImage;
	private List<ProductImage> productImages;
	private List<ProductPrice> productPrices;
}
