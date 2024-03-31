package com.example.catchclone.reservation.entity;


import com.example.catchclone.reservation.dto.ReservationDayRequestDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

@Entity
@Getter
@NoArgsConstructor
public class ReservationDayInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "monthInfo_id")
  private ReservationMonthInfo reservationMonthInfo;

  @Column
  private String dayInfo;

  @Column
  private String timeInfo;

  @Column
  private String isAvailable;

  @Column
  private Integer capacity;

  @Builder
  public ReservationDayInfo(ReservationMonthInfo reservationMonthInfo, ReservationDayRequestDto reservationDayRequestDto){
    this.reservationMonthInfo = reservationMonthInfo;
    this.dayInfo = reservationDayRequestDto.getDayInfo();
    this.timeInfo = reservationDayRequestDto.getTimeInfo();
    this.isAvailable = "Y";
    this.capacity = reservationDayRequestDto.getCapacity();
  }

}
