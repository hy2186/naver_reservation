package com.ntscorp.reservation.domain.comment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ntscorp.reservation.common.file.service.FileService;
import com.ntscorp.reservation.common.file.vo.ImageFile;
import com.ntscorp.reservation.domain.comment.dto.Comment;
import com.ntscorp.reservation.domain.comment.dto.CommentImage;
import com.ntscorp.reservation.domain.comment.dto.CommentParam;
import com.ntscorp.reservation.domain.comment.mapper.CommentMapper;

@Service
public class CommentService {
	private final CommentMapper commentMapper;
	private final FileService fileService;

	@Autowired
	public CommentService(CommentMapper commentMapper, FileService fileService) {
		this.commentMapper = commentMapper;
		this.fileService = fileService;
	}
	
	public List<Comment> getCommentList(int productId) {
		return commentMapper.selectCommentListByProductId(productId);
	}

	public List<Comment> getCommentList(int productId, int limit) {
		return commentMapper.selectCommentLimitListByProductId(productId, limit);
	}

	public double getAverageScore(int productId) {
		Double score = commentMapper.selectAverageScoreByProductId(productId);
		return score != null ? score : 0;
	}

	public int getCommentCount(int productId) {
		return commentMapper.selectCommentCountByProductId(productId);
	}

	public void addComment(CommentParam commentParam) {
		commentMapper.insertComment(commentParam);
	}
	
	@Transactional
	public void addComment(CommentParam commentParam, ImageFile imageFile) {
		commentMapper.insertComment(commentParam);
		int commentId = commentParam.getId();
		
		int fileId = fileService.addImageFile(imageFile);
		
		CommentImage commentImage = CommentImage.builder()
			.reservationInfoId(commentParam.getReservationInfoId())
			.reservationUserCommentId(commentId)
			.fileId(fileId)
			.build();
		
		commentMapper.insertCommentImage(commentImage);
	}
}
