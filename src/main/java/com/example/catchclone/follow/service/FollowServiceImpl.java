package com.example.catchclone.follow.service;

import com.example.catchclone.common.dto.StatusResponseDto;
import com.example.catchclone.follow.dao.FollowRepository;
import com.example.catchclone.follow.dto.FollowResponseDto;
import com.example.catchclone.follow.entity.Follow;
import com.example.catchclone.user.entity.User;
import com.example.catchclone.user.service.UserService;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService{

  private final FollowRepository followRepository;

  private final UserService userService;

  @Override
  @Transactional
  public List<FollowResponseDto> getMyFollowers(Long ownerId) {

    return followRepository.findFollowersByOwnerId(ownerId);
  }

  @Override
  @Transactional
  public StatusResponseDto deleteFollower(Long followId, User user) {

    followRepository.deleteById(followId);
    return new StatusResponseDto(204, "No Content");
  }

  @Override
  @Transactional
  public StatusResponseDto addFollower(Long followerId, User user) {

    userService.findUserByUserId(user.getId());

    if(followRepository.findFollowByOwnerIdAndFollowerId(user.getId(),followerId))
      throw new IllegalArgumentException("해당 팔로워가 이미 존재합니다!");

    if(Objects.equals(user.getId(), followerId)) throw new IllegalArgumentException("자기 자신을 팔로우 할 수 없습니다!");

    Follow follow = new Follow(user.getId(),followerId);
    followRepository.save(follow);



    return new StatusResponseDto(201,"Created");
  }
}
