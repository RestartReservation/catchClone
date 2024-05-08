package com.example.catchclone.reservation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReservationMonthInfoResponseDto {

  private Integer yearInfo;
  private Integer monthInfo;


  public ReservationMonthInfoResponseDto(Integer yearInfo,Integer monthInfo){
    this.yearInfo = yearInfo;
    this.monthInfo = monthInfo;
  }

}
