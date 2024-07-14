package com.example.catchclone.follow.dao;

import static com.example.catchclone.follow.entity.QFollow.follow;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class FollowRepositoryQueryImpl implements FollowRepositoryQuery{

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public boolean findFollowByOwnerIdAndFollowerId(Long ownerId, Long followerId) {

    return jpaQueryFactory.selectFrom(follow)
        .where(follow.ownerId.eq(ownerId),follow.followerId.eq(followerId))
        .fetchFirst()!=null;
  }
}
