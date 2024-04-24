package com.example.catchclone.reservation.entity;

import com.example.catchclone.reservation.dto.ReservationRequestDto;
import com.example.catchclone.store.entity.Store;
import com.example.catchclone.user.entity.User;
import com.example.catchclone.util.TimeStamped;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
public class Reservation extends TimeStamped {

  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;  //회원ID

  @ManyToOne
  @JoinColumn(name = "store_id")
  private Store store; //가맹점ID

  @Column // 필요해서 넣었으나, 연관관계 까지는 필요없는...
  private Long reservationDayInfoId;

  @Column
  private String reservationStatus;  //예약상태

  @Column
  private Integer numberOfPeople;  //예약 인원

  @Column
  private Integer yearInfo;

  @Column
  private Integer monthInfo;

  @Column
  private Integer dayInfo;

  @Column
  private String timeInfo; //예약 시간


  @Builder
  public Reservation(User user,Store store,Long reservationDayInfoId, Integer numberOfPeople, Integer yearInfo, Integer monthInfo, Integer dayInfo, String timeInfo){
    this.user = user;
    this.store = store;
    this.reservationDayInfoId = reservationDayInfoId;
    this.reservationStatus = "Y";
    this.numberOfPeople= numberOfPeople;
    this.yearInfo = yearInfo;
    this.monthInfo = monthInfo;
    this.dayInfo = dayInfo;
    this.timeInfo = timeInfo;
  }

}
