package com.example.catchclone.reservation.repository.day;

import com.example.catchclone.reservation.dto.ReservationDayInfoResponseDto;
import com.example.catchclone.reservation.dto.ReservationMonthInfoResponseDto;
import java.util.List;

public interface ReservationDayInfoRepositoryQuery {

  List<ReservationDayInfoResponseDto> reservationDayInfoByMonthId(Long storeId);

  void updateCapacity(Long dayId,Integer minusCount);

  void plusCapacity(Long reservationDayInfoId,Integer capacity);
}
