package com.example.catchclone.like.dao.commentLike;

import static com.example.catchclone.like.entity.commentLike.QCommentLike.commentLike;

import com.example.catchclone.like.entity.commentLike.CommentLikeId;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class CommentLikeRepositoryQueryImpl implements CommentLikeRepositoryQuery {

  private final JPAQueryFactory jpaQueryFactory;


  @Override
  @Transactional
  public boolean existByCommentLikeId(CommentLikeId commentLikeId) {
    return jpaQueryFactory.from(commentLike).where(commentLike.commentLikeId.eq(commentLikeId)).select(commentLike.commentLikeId)
        .setHint("org.hibernate.readOnly", true).fetchFirst() != null;
  }

}
