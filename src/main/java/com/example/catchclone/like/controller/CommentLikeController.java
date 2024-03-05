package com.example.catchclone.like.controller;


import static com.example.catchclone.like.controller.CommentLikeController.COMMENT_LIKE_URI_API;

import com.example.catchclone.common.dto.StatusResponseDto;
import com.example.catchclone.like.service.interfaces.CommentLikeService;
import com.example.catchclone.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(COMMENT_LIKE_URI_API)
public class CommentLikeController {
  public static final String COMMENT_LIKE_URI_API = "/ct/comments/likes";
  private final CommentLikeService commentLikeService;
  @PostMapping("like/{commentId}")
  public ResponseEntity<StatusResponseDto> requestCommentLike(@PathVariable Long commentId,@AuthenticationPrincipal
  UserDetailsImpl userDetails){
    StatusResponseDto statusResponseDto = commentLikeService.requestCommentLike(userDetails.getUserId(),commentId);
    return ResponseEntity.ok().body(statusResponseDto);
  }
}
