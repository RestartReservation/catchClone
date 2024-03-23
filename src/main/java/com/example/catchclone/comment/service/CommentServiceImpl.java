package com.example.catchclone.comment.service;

import com.example.catchclone.comment.dao.CommentRepository;
import com.example.catchclone.comment.dto.CommentRequestDto;
import com.example.catchclone.comment.dto.CommentResponseDto;
import com.example.catchclone.comment.entity.Comment;
import com.example.catchclone.comment.service.interfaces.CommentService;
import com.example.catchclone.common.dto.StatusResponseDto;
import com.example.catchclone.review.dao.ReviewRepository;
import com.example.catchclone.review.dto.ReviewRequestDto;
import com.example.catchclone.review.dto.ReviewResponseDto;
import com.example.catchclone.review.entity.Review;
import com.example.catchclone.review.service.interfaces.ReviewService;
import com.example.catchclone.user.entity.User;
import com.example.catchclone.user.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
  private CommentRepository commentRepository;
  private UserRepository userRepository;
  private ReviewService reviewService;
  @Override
  @Transactional
  public StatusResponseDto addComment(User user, CommentRequestDto commentRequestDto,Long reviewId) {
    Review review = reviewService.findReviewByReviewId(reviewId);
    Comment comment = Comment.builder()
        .userId(user.getId())
        .commentContent(commentRequestDto.commentContents())
        .review(review)
        .build();
    if(commentRequestDto.isChild()) setChildComment(comment, commentRequestDto.parentId());
    commentRepository.save(comment);
    return new StatusResponseDto(200,"OK");
  }

  @Override
  @Transactional(readOnly = true)
  public CommentResponseDto getComment(Long commentId) {
    return commentRepository.responseCommentDtoByCommentId(commentId).orElseThrow(
        () -> new IllegalArgumentException("유효하지 않은 정보입니다")
    );
  }

  @Override
  @Transactional(readOnly = true)
  public List<CommentResponseDto> getReviewComments(Long reviewId) {
    return commentRepository.findCommentsByReviewId(reviewId);
  }

  @Override
  @Transactional(readOnly = true)
  public List<CommentResponseDto> getUserComments(Long userId) {
    return commentRepository.findCommentsByUserId(userId);
  }

  @Override
  @Transactional
  public StatusResponseDto updateComment(User user, Long commentId,
      CommentRequestDto commentRequestDto) {
    Comment comment = findCommentByCommentId(commentId);

    if(!comment.isWriter(user,comment)) return new StatusResponseDto(400,"Bad Request");

    comment.update(commentRequestDto);

    return new StatusResponseDto(200,"OK");
  }

  @Override
  @Transactional
  public StatusResponseDto deleteComment(User user, Long commentId) {
    Comment comment = findCommentByCommentId(commentId);

    if(!comment.isWriter(user,comment)) return new StatusResponseDto(400,"Bad Request");

    commentRepository.deleteById(commentId);

    return new StatusResponseDto(204,"NO_CONTENT");
  }

  @Override
  public Comment findCommentByCommentId(Long commentId) {
    return  commentRepository.findById(commentId).orElseThrow(
        () -> new IllegalArgumentException("유효하지 않은 Id입니다")
    );
  }

  private void setChildComment(Comment childComment, Long parentId){
    Comment parentComment = findCommentByCommentId(parentId);
    childComment.setChildComment(parentComment.getLayer() + 1, parentComment.getParentId());
  }

}

