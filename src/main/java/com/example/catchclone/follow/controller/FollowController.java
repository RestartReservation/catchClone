package com.example.catchclone.follow.controller;


import static com.example.catchclone.follow.controller.FollowController.FOLLOW_URI_API;
import static com.example.catchclone.review.controller.ReviewController.REVIEW_URI_API;

import com.example.catchclone.common.dto.StatusResponseDto;
import com.example.catchclone.follow.dto.FollowResponseDto;
import com.example.catchclone.follow.service.FollowService;
import com.example.catchclone.security.UserDetailsImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(FOLLOW_URI_API)
public class FollowController {

  public static final String FOLLOW_URI_API = "/ct/followers";

  private final FollowService followService;

  @PostMapping("/{followerId}")
  public ResponseEntity<StatusResponseDto> addFollower(@PathVariable Long followerId,@AuthenticationPrincipal UserDetailsImpl userDetails){

    return ResponseEntity.ok().body(followService.addFollower(followerId,userDetails.getUser()));
  }

  @DeleteMapping ("/{followId}")
  public ResponseEntity<StatusResponseDto> deleteFollower(@PathVariable Long followId,@AuthenticationPrincipal UserDetailsImpl userDetails){

    return ResponseEntity.ok().body(followService.deleteFollower(followId,userDetails.getUser()));
  }

  @GetMapping
  public ResponseEntity<List<FollowResponseDto>> getMyFollowers(@AuthenticationPrincipal UserDetailsImpl userDetails){
    return ResponseEntity.ok().body(followService.getMyFollowers(userDetails.getUserId()));
  }

}
