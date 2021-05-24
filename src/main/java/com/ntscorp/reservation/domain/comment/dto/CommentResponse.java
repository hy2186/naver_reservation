package com.ntscorp.reservation.domain.comment.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentResponse {
	private double averageScore;
	private List<Comment> comments;
	int commentCount;
}
