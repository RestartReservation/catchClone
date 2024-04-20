package com.example.catchclone.reservation.entity;


import com.example.catchclone.reservation.dto.ReservationMonthRequestDto;
import com.example.catchclone.store.entity.Store;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ReservationMonthInfo {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name="store_id")
  private Store store;

  @OneToMany(mappedBy = "reservationMonthInfo")
  List<ReservationDayInfo> reservationDayInfo;

  @Column
  private Integer yearInfo;

  @Column
  private Integer monthInfo;

  @Builder
  public ReservationMonthInfo(Store store, ReservationMonthRequestDto reservationMonthRequestDto){

    this.store = store;
    this.yearInfo = reservationMonthRequestDto.getYearInfo();
    this.monthInfo = reservationMonthRequestDto.getMonthInfo();
  }

}
