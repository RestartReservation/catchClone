package com.example.catchclone.reservation.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReservationDayInfoResponseDto {


  private Integer dayInfo;
  private String timeInfo;
  private String isAvailable;
  private Integer capacity;


}
