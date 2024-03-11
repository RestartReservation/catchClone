package com.example.catchclone.like.dao.commentLike;

import com.example.catchclone.like.entity.commentLike.CommentLikeId;

public interface CommentLikeRepositoryQuery {
  boolean existByCommentLikeId(CommentLikeId commentLikeId);
}
