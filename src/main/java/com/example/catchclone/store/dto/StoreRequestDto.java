package com.example.catchclone.store.dto;


import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class StoreRequestDto {

  private String storeName;

  private String storeLocation;

  private Float starRate;

  private String timeDetail;

  private String storePhoneNumber;

  private String aboutStore;

  private String storeNotification;

  private String reservationTypeFlag;

  private String regularHoliday;

  private String storeHomepage;

  @Builder
  public StoreRequestDto(String storeName, String storeLocation, Float starRate, String timeDetail, String storePhoneNumber, String aboutStore, LocalDateTime createdAt,String storeNotification,String reservationTypeFlag, String regularHoliday,
      String storeHomepage) {

    this.storeName = storeName;
    this.storeLocation = storeLocation;
    this.starRate = starRate;
    this.timeDetail = timeDetail;
    this.storePhoneNumber = storePhoneNumber;
    this.aboutStore = aboutStore;
    this.storeNotification = storeNotification;
    this.reservationTypeFlag = reservationTypeFlag;
    this.regularHoliday = regularHoliday;
    this.storeHomepage = storeHomepage;

  }

}
