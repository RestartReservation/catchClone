package com.example.catchclone.follow.dao;

import com.example.catchclone.follow.dto.FollowResponseDto;
import java.util.List;

public interface FollowRepositoryQuery {

  boolean findFollowByOwnerIdAndFollowerId(Long ownerId,Long followerId);

  List<FollowResponseDto> findFollowersByOwnerId(Long ownerId);

}
