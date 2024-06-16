package com.example.catchclone.review.entity;

import com.example.catchclone.comment.entity.Comment;
import com.example.catchclone.like.entity.reviewLike.ReviewLike;
import com.example.catchclone.review.dto.ReviewRequestDto;
import com.example.catchclone.review.dto.UpdateReviewRequestDto;
import com.example.catchclone.user.entity.User;
import com.example.catchclone.util.TimeStamped;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Review extends TimeStamped {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "review_id", nullable = false)
  private Long id;

  @Column
  private Long userId;  //회원ID

  @Column
  private Long storeId; //가맹정ID

  @Column
  private Long reservationId; //예약ID

  @Column
  private String reviewTitle;

  @Column
  private String reviewContent; //리뷰내용

  @Column
  private Float tasteRating;  //맛별점

  @Column
  private Float atmosphereRating; //분위기별점

  @Column
  private Float serviceRating;  //서비스별점

  @Column
  private Float totalRating;  //모든별점평균


  @Builder
  public Review(Long id, Long userId, Long storeId, Long reservationId, String reviewTitle, String reviewContent, Float tasteRating, Float atmosphereRating, Float serviceRating){
    this.id = id;
    this.userId = userId;
    this.storeId = storeId;
    this.reservationId = reservationId;
    this.reviewTitle = reviewTitle;
    this.reviewContent = reviewContent;
    this.tasteRating = tasteRating;
    this.atmosphereRating = atmosphereRating;
    this.serviceRating = serviceRating;
    this.totalRating = (tasteRating + atmosphereRating + serviceRating) / 3;
  }

  public Review(Long userId,Long storeId,ReviewRequestDto reviewRequestDto){
    this.userId = userId;
    this.storeId = storeId;
    this.reservationId = reviewRequestDto.reservationId();
    this.reviewTitle = reviewRequestDto.reviewTitle();
    this.reviewContent = reviewRequestDto.reviewContent();
    this.tasteRating = reviewRequestDto.tasteRating();
    this.atmosphereRating = reviewRequestDto.atmosphereRating();
    this.serviceRating = reviewRequestDto.serviceRating();
    this.totalRating = (tasteRating + atmosphereRating + serviceRating) / 3;
  }

  //연관관계
  @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Comment> comments = new LinkedHashSet<>();

  @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<ReviewLike> likes = new HashSet<>();

  @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ReviewPicture> reviewPicture = new ArrayList<>();

  //메서드
  public boolean isWriter(User user,Review review){
    return Objects.equals(user.getId(), review.getUserId());
  }

  public void update(UpdateReviewRequestDto reviewRequestDto) {
    this.reviewContent = reviewRequestDto.reviewContent();
    this.tasteRating = reviewRequestDto.tasteRating();
    this.atmosphereRating = reviewRequestDto.atmosphereRating();
    this.serviceRating = reviewRequestDto.serviceRating();
    this.totalRating = (reviewRequestDto.tasteRating() + reviewRequestDto.atmosphereRating() + reviewRequestDto.serviceRating()) / 3;
  }
}
