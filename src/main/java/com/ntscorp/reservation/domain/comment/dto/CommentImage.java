package com.ntscorp.reservation.domain.comment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentImage {
	private int commentImageId;
	private int reservationInfoId;
	private int reservationUserCommentId;
	private int fileId;
	
	@Builder
	public CommentImage(int reservationInfoId, int reservationUserCommentId, int fileId) {
		this.reservationInfoId = reservationInfoId;
		this.reservationUserCommentId = reservationUserCommentId;
		this.fileId = fileId;
	}
}
