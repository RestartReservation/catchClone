package com.example.catchclone.store.entity;

import com.example.catchclone.store.dto.StoreRequestDto;
import com.example.catchclone.user.entity.User;
import com.example.catchclone.util.TimeStamped;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Store extends TimeStamped {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String storeName; //가맹점이름

  @Column
  private String storeLocation; //가맹점위치정보

  @Column
  private Float starRate; //별점

  @Column
  private String timeDetail; // 변수명 변경 예정, 운영시간

  @Column
  private String storePhoneNumber;  //가게전화번호

  @Column
  private String aboutStore;  //가게소개

  @Column
  private String storeNotification; //가맹점공지

  @Column
  private String reservationTypeFlag; //예약 지정일자 오픈 여부(Y,N)

  @Column
  private String regularHoliday; //정기 휴무일

  @Column
  private String storeHomepage; //홈페이지 주소

  @Column
  private Float totalRate; // 리뷰 합계

  @Builder
  public Store(String storeName, String storeLocation, Float starRate, String timeDetail, String storePhoneNumber, String aboutStore, LocalDateTime createdAt,String storeNotification,String reservationTypeFlag, String regularHoliday,
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


  public Store(StoreRequestDto storeRequestDto,User user){
    this.storeName = storeRequestDto.getStoreName();
    this.user = user;
    this.storeLocation = storeRequestDto.getStoreLocation();
    this.starRate = 0.0f; // 처음 가맹점 등록할 때 별점 초기화
    this.timeDetail = storeRequestDto.getTimeDetail();
    this.storePhoneNumber = storeRequestDto.getStorePhoneNumber();
    this.aboutStore = storeRequestDto.getAboutStore();
    this.storeNotification = storeRequestDto.getStoreNotification();
    this.reservationTypeFlag = storeRequestDto.getReservationTypeFlag();
    this.regularHoliday = storeRequestDto.getRegularHoliday();
    this.storeHomepage = storeRequestDto.getStoreHomepage();
    this.totalRate = 0.0f;// 처음 가맹점 등록할 때 총점 초기화
  }

  public Store(String storeName){
    this.storeName = storeName;
  }

  public void updateStarRate(Float plusStarRate,Float plusSumRate){
    this.starRate = plusStarRate;
    this.totalRate+= plusSumRate;
  }

  //연관관계
  @ManyToOne
  @JoinColumn(name="user_id")
  private User user;
}
