package com.example.catchclone.like.entity.commentLike;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class CommentLikeId implements Serializable {

  @Serial
  private static final long serialVersionUID = 932813599390736126L;

  @Column(name = "user_id")
  private Long userId;

  @Column(name = "comment_id")
  private Long commentId;

  @Builder
  public CommentLikeId(Long userId,Long commentId){
    this.userId = userId;
    this.commentId = commentId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommentLikeId commentLikeId = (CommentLikeId) o;
    return Objects.equals(getUserId(), commentLikeId.getUserId()) && Objects.equals(getCommentId(),
        commentLikeId.getCommentId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getUserId(), getCommentId());
  }
}
