package com.example.catchclone.reservation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationRequestDto {

  private Integer reservationCount;

  private String reservationDate;

  private String reservationTime;

}
