package com.example.catchclone.comment.entity;

import com.example.catchclone.comment.dto.CommentRequestDto;
import com.example.catchclone.like.entity.commentLike.CommentLike;
import com.example.catchclone.user.entity.User;
import com.example.catchclone.review.entity.Review;
import com.example.catchclone.util.TimeStamped;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Comment extends TimeStamped {
  @Id
  @GeneratedValue
  private Long id;

  @Column
  private Long userId;

  @Column
  private String commentContent;

  @Column
  private Integer layer = 0;

  @Column
  private Long parentId = null;

  //생성자
  @Builder
  public Comment(Long userId, Review review, String commentContent){
    this.userId = userId;
    this.review = review;
    this.commentContent = commentContent;
  }

  //연관관계
  @ManyToOne
  @JoinColumn(name = "review_id")
  private Review review;

  @OneToMany(mappedBy = "comment",cascade = CascadeType.ALL,orphanRemoval = true)
  private final Set<CommentLike> commentLikes = new HashSet<>();
  //메서드
  public boolean isWriter(User user,Comment comment){
    return Objects.equals(user.getId(), comment.getUserId());
  }

  public void update(CommentRequestDto commentRequestDto) {
    this.commentContent = commentRequestDto.commentContents();
  }
  public void setChildComment(Integer layer,Long parentId){
    this.layer = layer;
    this.parentId = parentId;
  }
}
