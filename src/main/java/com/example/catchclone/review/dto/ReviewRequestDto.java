package com.example.catchclone.review.dto;




public record ReviewRequestDto(Long reservationId, String reviewContent, String reviewTitle, Float tasteRating, Float atmosphereRating, Float serviceRating) {

}
