package com.example.catchclone.comment.dao;

import com.example.catchclone.comment.entity.Comment;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface CommentRepository extends Repository<Comment,Long>,CommentRepositoryQuery{
  void save(Comment comment);
  Optional<Comment> findById(Long commentId);
  void deleteById(Long commentId);
}
