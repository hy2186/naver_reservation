package com.ntscorp.reservation.domain.displayInfo.controller;

import java.util.List;

import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ntscorp.reservation.domain.comment.dto.Comment;
import com.ntscorp.reservation.domain.comment.service.CommentService;
import com.ntscorp.reservation.domain.displayInfo.dto.DisplayInfo;
import com.ntscorp.reservation.domain.displayInfo.dto.DisplayInfoImage;
import com.ntscorp.reservation.domain.displayInfo.dto.DisplayInfoResponse;
import com.ntscorp.reservation.domain.displayInfo.service.DisplayInfoService;
import com.ntscorp.reservation.domain.product.dto.ProductImage;
import com.ntscorp.reservation.domain.product.dto.ProductPrice;
import com.ntscorp.reservation.domain.product.service.ProductService;

@RestController
@Validated
public class DisplayInfoController {
	private static final int DEFAULT_REVIEW_COUNT = 3;

	private final CommentService commentService;
	private final DisplayInfoService displayInfoService;
	private final ProductService productService;

	@Autowired
	public DisplayInfoController(CommentService commentService, DisplayInfoService displayInfoService,
		ProductService productService) {
		this.commentService = commentService;
		this.displayInfoService = displayInfoService;
		this.productService = productService;
	}

	@GetMapping("/api/products/{displayInfoId}")
	public DisplayInfoResponse getDisplayInfoResponse(@PathVariable @Positive int displayInfoId) {
		DisplayInfo displayInfo = displayInfoService.getDisplayInfo(displayInfoId);
		DisplayInfoImage displayInfoImage = displayInfoService.getDisplayInfoImage(displayInfoId);

		int productId = displayInfo.getProductId();

		double averageScore = commentService.getAverageScore(productId);
		List<Comment> commentList = commentService.getCommentList(productId, DEFAULT_REVIEW_COUNT);
		int commentCount = commentService.getCommentCount(productId);

		List<ProductImage> productImageList = productService.getProductImageList(productId);
		List<ProductPrice> productPriceList = productService.getProductPriceList(productId);

		return DisplayInfoResponse.builder()
			.averageScore(averageScore)
			.commentCount(commentCount)
			.comments(commentList)
			.displayInfo(displayInfo)
			.displayInfoImage(displayInfoImage)
			.productImages(productImageList)
			.productPrices(productPriceList)
			.build();
	}
}
