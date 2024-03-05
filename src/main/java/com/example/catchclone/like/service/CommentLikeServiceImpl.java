package com.example.catchclone.like.service;

import com.example.catchclone.comment.entity.Comment;
import com.example.catchclone.comment.service.interfaces.CommentService;
import com.example.catchclone.common.dto.StatusResponseDto;
import com.example.catchclone.like.dao.commentLike.CommentLikeRepository;
import com.example.catchclone.like.entity.commentLike.CommentLike;
import com.example.catchclone.like.entity.commentLike.CommentLikeId;
import com.example.catchclone.like.service.interfaces.CommentLikeService;
import com.example.catchclone.user.entity.User;
import com.example.catchclone.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentLikeServiceImpl implements CommentLikeService {
  private final CommentLikeRepository commentLikeRepository;
  private final UserService userService;
  private final CommentService commentService;
  @Override
  @Transactional
  public StatusResponseDto requestCommentLike(Long userId, Long commentId) {
    CommentLikeId commentLikeId = CommentLikeId.builder()
        .userId(userId)
        .commentId(commentId)
        .build();

    if(commentLikeRepository.existByCommentLikeId(commentLikeId)){
      commentLikeRepository.deleteByCommentLikeId(commentLikeId);
    }

    else{
      User user = userService.findUserByUserId(userId);
      Comment comment = commentService.findCommentByCommentId(commentId);
      commentLikeRepository.save(new CommentLike(user,comment));
    }

    return new StatusResponseDto(200,"OK");
  }
}
