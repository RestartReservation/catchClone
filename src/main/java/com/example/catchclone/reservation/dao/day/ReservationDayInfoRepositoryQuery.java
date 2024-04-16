package com.example.catchclone.reservation.dao.day;

import com.example.catchclone.reservation.dto.ReservationDayInfoResponseDto;
import java.util.List;

public interface ReservationDayInfoRepositoryQuery {

  List<ReservationDayInfoResponseDto> reservationDayInfoByMonthId(Long storeId);

  void updateCapacity(Long dayId,Integer minusCount);

  void plusCapacity(Long reservationDayInfoId,Integer capacity);
}
