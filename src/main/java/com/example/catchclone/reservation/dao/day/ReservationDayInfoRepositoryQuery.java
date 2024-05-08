package com.example.catchclone.reservation.dao.day;

import com.example.catchclone.reservation.dto.ReservationDayInfoResponseDto;
import java.util.List;

public interface ReservationDayInfoRepositoryQuery {

  List<ReservationDayInfoResponseDto> reservationDayInfoByMonthId(Long storeId);

  List<ReservationDayInfoResponseDto> findReservationInfos(Long storeId,Integer year,Integer month,Integer day);

  void updateCapacity(Long dayId,Integer minusCount);

  void plusCapacity(Long reservationDayInfoId,Integer capacity);

  boolean findDayInfoByMonthIdAndDayAndTime(Long monthInfoId,Integer dayInfo,String timeInfo);
}
