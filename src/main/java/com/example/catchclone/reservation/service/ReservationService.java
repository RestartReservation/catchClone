package com.example.catchclone.reservation.service;

import com.example.catchclone.common.dto.StatusResponseDto;
import com.example.catchclone.reservation.dto.ReservationDayInfoResponseDto;
import com.example.catchclone.reservation.dto.ReservationDayRequestDto;
import com.example.catchclone.reservation.dto.ReservationMonthInfoResponseDto;
import com.example.catchclone.reservation.dto.ReservationMonthRequestDto;
import com.example.catchclone.reservation.dto.ReservationRequestDto;
import com.example.catchclone.user.entity.User;
import java.util.List;

public interface ReservationService {


  StatusResponseDto addMonthInfo(Long storeId, ReservationMonthRequestDto reservationMonthRequestDto, User user);

  StatusResponseDto addDayInfo(Long mothInfoId, ReservationDayRequestDto reservationDayRequestDto,User user);

  List<ReservationMonthInfoResponseDto> showReservationMonthInfo(Long storeId,User user);

  List<ReservationDayInfoResponseDto> showReservationDayInfo(Long monthId,User user);

  void addReservation(Long storeId,Long dayId,ReservationRequestDto reservationRequestDto,User user);

  StatusResponseDto cancelReservation(Long reservationId,User user);

  StatusResponseDto visitComplete(Long reservationId,User user);
}
