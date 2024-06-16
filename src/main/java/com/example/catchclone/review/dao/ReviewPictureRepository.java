package com.example.catchclone.review.dao;

import com.example.catchclone.review.entity.ReviewPicture;
import org.springframework.data.repository.Repository;

public interface ReviewPictureRepository extends Repository<ReviewPicture,Long> {
  void save(ReviewPicture reviewPicture);
}
