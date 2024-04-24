package com.example.catchclone.reservation.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationRequestDto {

  private Integer numberOfPeople;

  private Integer yearInfo;

  private Integer monthInfo;

  private Integer dayInfo;

  private String timeInfo;

}
