package com.ntscorp.reservation.domain.comment.controller;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ntscorp.reservation.common.file.util.FileNameGenerator;
import com.ntscorp.reservation.common.file.vo.ImageFile;
import com.ntscorp.reservation.domain.comment.dto.Comment;
import com.ntscorp.reservation.domain.comment.dto.CommentParam;
import com.ntscorp.reservation.domain.comment.dto.CommentResponse;
import com.ntscorp.reservation.domain.comment.service.CommentService;
import com.ntscorp.reservation.exception.custom.FileLoadException;

@RestController
@Validated
public class CommentController {
	@Value("${spring.directory.root}")
	private String rootPath;
	@Value("${spring.directory.comment-image}")
	private String commentImagePath;
	private static final String IMAGE_CONTENT_TYPE = "image/png";
	
	private final CommentService commentService;
	
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@GetMapping("/api/products/{productId}/comments")
	public CommentResponse getCommentResponse(@PathVariable @Positive int productId) {
		double averageScore = commentService.getAverageScore(productId);
		List<Comment> commentList = commentService.getCommentList(productId);
		int commentCount = commentService.getCommentCount(productId);

		return CommentResponse.builder()
			.averageScore(averageScore)
			.comments(commentList)
			.commentCount(commentCount)
			.build();
	}

	@PostMapping("/api/reservations/{reservationInfoId}/comments")
	public CommentResponse addComment(@ModelAttribute @Valid CommentParam commentParam, 
		@PathVariable @Positive int reservationInfoId) {
		MultipartFile file = commentParam.getAttachedImage();
		if (file == null) {
			commentService.addComment(commentParam);
			
			return CommentResponse.builder()
				.averageScore(0)
				.comments(Collections.emptyList())
				.commentCount(0)
				.build();
		}
		
		String fileName = FileNameGenerator.getRandomFileName();
		String filePath = commentImagePath + fileName;
		
		File saveFile = new File(rootPath + filePath);
		
		try {
			file.transferTo(saveFile);
			
			ImageFile imageFile = ImageFile.builder()
				.fileName(fileName)
				.saveFileName(filePath)
				.contentType(IMAGE_CONTENT_TYPE)
				.build();
			
			commentService.addComment(commentParam, imageFile);
		} catch (IOException exception) {
			saveFile.delete();
			throw new FileLoadException(exception);
		} catch (DataAccessException exception) {
			saveFile.delete();
			throw exception;
		}
		
		return CommentResponse.builder()
			.averageScore(0)
			.comments(Collections.emptyList())
			.commentCount(0)
			.build();
	}
}
