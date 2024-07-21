package com.example.catchclone.follow.dao;

import static com.example.catchclone.follow.entity.QFollow.follow;

import com.example.catchclone.follow.dto.FollowResponseDto;
import static com.example.catchclone.user.entity.QUser.user;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class FollowRepositoryQueryImpl implements FollowRepositoryQuery{

  private final JPAQueryFactory jpaQueryFactory;



  @Override
  public List<FollowResponseDto> findFollowersByOwnerId(Long ownerId) {

    return jpaQueryFactory.select(
            Projections.bean(FollowResponseDto.class,
            follow.followerId,user.nickName,user.profileUrl))
        .from(follow)
        .innerJoin(user)
        .on(follow.followerId.eq(user.id))
        .where(follow.ownerId.eq(ownerId))
        .fetch();

  }

  @Override
  public boolean findFollowByOwnerIdAndFollowerId(Long ownerId, Long followerId) {

    return jpaQueryFactory.selectFrom(follow)
        .where(follow.ownerId.eq(ownerId),follow.followerId.eq(followerId))
        .fetchFirst()!=null;
  }
}
