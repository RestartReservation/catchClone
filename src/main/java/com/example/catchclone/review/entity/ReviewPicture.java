package com.example.catchclone.review.entity;

import com.example.catchclone.util.TimeStamped;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class ReviewPicture extends TimeStamped {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Long id;

  @Column
  private String pictureUrl;

  @Builder
  public ReviewPicture(Long id, String pictureUrl,Review review){
    this.id = id;
    this.pictureUrl = pictureUrl;
    this.review = review;
  }

  @ManyToOne
  @JoinColumn(name = "review_id")
  private Review review;
}
