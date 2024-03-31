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
  private Integer reservationCount;  //예약 인원

  @Column
  private String reservationDate; //예약 일자

  @Column
  private String reservationTime; //예약 시간


  @Builder
  public Reservation(User user,Store store,Long reservationDayInfoId, ReservationRequestDto reservationRequestDto){

    this.user = user;
    this.store = store;
    this.reservationDayInfoId = reservationDayInfoId;
    this.reservationStatus = "Y";
    this.reservationCount = reservationRequestDto.getReservationCount();
    this.reservationDate = reservationRequestDto.getReservationDate();
    this.reservationTime = reservationRequestDto.getReservationTime();
  }

}
