package com.example.catchclone.review.dto;

import com.example.catchclone.review.entity.ReviewPicture;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewResponseDto {
 public Long reviewId;
 public String reviewTitle;
  public String reviewContent;
  public Float tasteRating;
  public Float atmosphereRating;
  public Float serviceRating;
  public Float totalRating;
  public LocalDateTime createdAt;
  public Long likeCount;
  public List<ReviewPictureDto> reviewPictures;
}
