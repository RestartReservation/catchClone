package com.example.catchclone.reservation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReservationMonthInfoResponseDto {

  private String yearInfo;
  private String monthInfo;


  public ReservationMonthInfoResponseDto(String yearInfo,String monthInfo){
    this.yearInfo = yearInfo;
    this.monthInfo = monthInfo;
  }

}
