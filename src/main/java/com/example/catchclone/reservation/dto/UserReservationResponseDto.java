package com.example.catchclone.reservation.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserReservationResponseDto {
  private final Long reservationId;
  private final String storeName;
  private final Integer yearInfo;
  private final Integer monthInfo;
  private final Integer dayInfo;
  private final String timeInfo;
  private final String reservationStatus;
}
