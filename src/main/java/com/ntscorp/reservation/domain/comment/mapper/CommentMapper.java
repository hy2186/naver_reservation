package com.ntscorp.reservation.domain.comment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ntscorp.reservation.domain.comment.dto.Comment;
import com.ntscorp.reservation.domain.comment.dto.CommentImage;
import com.ntscorp.reservation.domain.comment.dto.CommentParam;

@Repository
public interface CommentMapper {
	List<Comment> selectCommentListByProductId(int productId);

	List<Comment> selectCommentLimitListByProductId(@Param("productId") int productId, @Param("limit") int limit);

	Double selectAverageScoreByProductId(int productId);

	int selectCommentCountByProductId(int productId);

	int insertComment(CommentParam commentParam);

	int insertCommentImage(CommentImage commentImage);
}
