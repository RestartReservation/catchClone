package com.example.catchclone.follow.service;

import com.example.catchclone.common.dto.StatusResponseDto;
import com.example.catchclone.user.entity.User;
import org.springframework.http.ResponseEntity;

public interface FollowService {

  StatusResponseDto addFollower(Long followerId, User user);

  StatusResponseDto deleteFollower(Long followId, User user);
}
