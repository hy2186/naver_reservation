package com.ntscorp.reservation.domain.comment.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentParam {
	private int id;
	private MultipartFile attachedImage;

	@NotBlank
	@Length(min = 5, max = 400)
	private String comment;

	@Positive
	private int productId;

	@Positive
	private int reservationInfoId;

	@Min(1)
	@Max(5)
	private int score;
}
