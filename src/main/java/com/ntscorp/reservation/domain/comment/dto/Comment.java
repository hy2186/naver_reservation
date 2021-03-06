package com.ntscorp.reservation.domain.comment.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comment {
	private String comment;
	private int commentId;
	private List<CommentImage> commentImages;
	private LocalDateTime createDate;
	private LocalDateTime modifyDate;
	private int productId;
	private LocalDate reservationDate;
	private String reservationEmail;
	private int reservationInfoId;
	private String reservationName;
	private String reservationTelephone;
	private double score;

	public String getReservationEmail() {
		return reservationEmail.substring(0, Math.min(4, reservationEmail.length())) + "****";
	}
}
