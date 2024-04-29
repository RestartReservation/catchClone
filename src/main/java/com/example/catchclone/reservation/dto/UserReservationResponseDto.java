package com.example.catchclone.reservation.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserReservationResponseDto {
  private Long reservationId;
  private String storeName;
  private Integer yearInfo;
  private Integer monthInfo;
  private Integer dayInfo;
  private String timeInfo;
  private String reservationStatus;
}
