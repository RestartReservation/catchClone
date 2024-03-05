package com.example.catchclone.like.dao.commentLike;

import com.example.catchclone.like.entity.commentLike.CommentLike;
import com.example.catchclone.like.entity.commentLike.CommentLikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;


public interface CommentLikeRepository extends Repository<CommentLike, CommentLikeId>,CommentLikeRepositoryQuery {
  void save(CommentLike commentLike);

  void deleteByCommentLikeId(CommentLikeId commentLikeId);

}
