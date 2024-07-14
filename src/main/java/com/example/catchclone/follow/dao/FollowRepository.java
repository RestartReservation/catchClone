package com.example.catchclone.follow.dao;

import com.example.catchclone.follow.entity.Follow;
import org.springframework.data.repository.Repository;


public interface FollowRepository extends Repository<Follow,Long>,FollowRepositoryQuery {


  void save(Follow follow);
  void deleteById(Long followId);

}
