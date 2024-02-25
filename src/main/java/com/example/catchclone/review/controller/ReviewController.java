package com.example.catchclone.review.controller;

import static com.example.catchclone.review.controller.ReviewController.REVIEW_URI_API;

import com.example.catchclone.review.dto.ReviewRequestDto;
import com.example.catchclone.review.dto.ReviewResponseDto;
import com.example.catchclone.review.service.ReviewServiceImpl;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
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

  @PostMapping("/{storeId}")
  public ResponseEntity<StatusResponseDto> addReview(@RequestBody ReviewRequestDto reviewRequestDto,
      @PathVariable Long storeId, @AuthenticationPrincipal
  UserDetailsImpl userDetails) {
    StatusResponseDto statusResponseDto = reviewService.addReview(userDetails.getUser(),
        reviewRequestDto, storeId);
    return ResponseEntity.ok().body(statusResponseDto);
  }

  @GetMapping("/review/{reviewId}")
  public ResponseEntity<ReviewResponseDto> getReview(@PathVariable Long reviewId) {
    ReviewResponseDto reviewResponseDto = reviewService.getReview(reviewId);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    return ResponseEntity.ok().headers(headers).body(reviewResponseDto);
  }
}
