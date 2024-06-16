package com.example.catchclone.review.dao;

import com.example.catchclone.review.entity.Review;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface ReviewRepository extends Repository<Review,Long>,ReviewRepositoryQuery {
  void save(Review review);
  void saveAndFlush(Review review);
  Optional<Review> findById(Long reviewId);
  void deleteById(Long reviewId);
}
