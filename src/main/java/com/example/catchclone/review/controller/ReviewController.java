package com.example.catchclone.review.controller;

import static com.example.catchclone.review.controller.ReviewController.REVIEW_URI_API;

import com.example.catchclone.common.dto.StatusResponseDto;
import com.example.catchclone.review.dto.ReviewRequestDto;
import com.example.catchclone.review.dto.ReviewResponseDto;
import com.example.catchclone.review.dto.UpdateReviewRequestDto;
import com.example.catchclone.review.service.ReviewServiceImpl;
import com.example.catchclone.security.UserDetailsImpl;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping(REVIEW_URI_API)
public class ReviewController {

  public static final String REVIEW_URI_API = "/ct/reviews";
  private final ReviewServiceImpl reviewService;

  @PostMapping("/{storeId}/{reservationId}")
  public ResponseEntity<StatusResponseDto> addReview(@RequestBody ReviewRequestDto reviewRequestDto,
      @PathVariable Long storeId,@PathVariable Long reservationId, @AuthenticationPrincipal
  UserDetailsImpl userDetails) {
    StatusResponseDto statusResponseDto = reviewService.addReview(userDetails.getUser(),
        reservationId,reviewRequestDto, storeId);
    return ResponseEntity.ok().body(statusResponseDto);
  }

  @GetMapping("/{reviewId}")
  public ResponseEntity<ReviewResponseDto> getReview(@PathVariable Long reviewId) {
    ReviewResponseDto reviewResponseDto = reviewService.getReview(reviewId);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    return ResponseEntity.ok().headers(headers).body(reviewResponseDto);
  }

  //store 리뷰 모두 조회(페이징 필요)
  @GetMapping("/stores/{storeId}")
  public ResponseEntity<List<ReviewResponseDto>> getStoreReviews(@PathVariable Long storeId) {
    List<ReviewResponseDto> dtoList = reviewService.getStoreReviewsByStoreId(storeId);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    return ResponseEntity.ok().headers(headers).body(dtoList);
  }

  @PatchMapping("/{reviewId}")
  public ResponseEntity<StatusResponseDto> updateReview(@PathVariable Long reviewId, @AuthenticationPrincipal UserDetailsImpl userDetails,
      @RequestBody UpdateReviewRequestDto updateReviewRequestDto){
    StatusResponseDto statusResponseDto = reviewService.updateReview(reviewId,
        userDetails.getUserId(), updateReviewRequestDto);
    return ResponseEntity.ok().body(statusResponseDto);
  }

  @DeleteMapping("/{reviewId}")
  public ResponseEntity<StatusResponseDto> deleteReview(@PathVariable Long reviewId, @AuthenticationPrincipal
  UserDetailsImpl userDetails){
    StatusResponseDto statusResponseDto = reviewService.deleteReview(userDetails.getUser(),reviewId);
    return ResponseEntity.ok().body(statusResponseDto);
  }

}
