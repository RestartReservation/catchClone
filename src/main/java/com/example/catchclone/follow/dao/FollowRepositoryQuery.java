package com.example.catchclone.follow.dao;

public interface FollowRepositoryQuery {

  boolean findFollowByOwnerIdAndFollowerId(Long ownerId,Long followerId);

}
