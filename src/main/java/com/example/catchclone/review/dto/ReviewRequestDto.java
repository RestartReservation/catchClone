package com.example.catchclone.review.dto;


import java.util.List;

public record ReviewRequestDto(Long reservationId, String reviewContent, String reviewTitle, Float tasteRating, Float atmosphereRating, Float serviceRating, List<String> pictureUrl) {

}
