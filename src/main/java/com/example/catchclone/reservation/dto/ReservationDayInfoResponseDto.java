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

  //일단 뭐 필요할지 몰라서 다 넣어놓음.
  private Long id;
  private Integer dayInfo;
  private String timeInfo;
  private String isAvailable;
  private Integer capacity;


}
