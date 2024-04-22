package com.example.catchclone.reservation.service.interfaces;

import com.example.catchclone.common.dto.StatusResponseDto;
import com.example.catchclone.reservation.dto.ReservationDayInfoResponseDto;
import com.example.catchclone.reservation.dto.ReservationDayRequestDto;
import com.example.catchclone.reservation.dto.ReservationMonthInfoResponseDto;
import com.example.catchclone.reservation.dto.ReservationMonthRequestDto;
import com.example.catchclone.reservation.dto.ReservationRequestDto;
import com.example.catchclone.reservation.entity.Reservation;
import com.example.catchclone.reservation.entity.ReservationDayInfo;
import com.example.catchclone.reservation.entity.ReservationMonthInfo;
import com.example.catchclone.store.entity.Store;
import com.example.catchclone.user.entity.User;
import java.util.List;

public interface ReservationService {


  StatusResponseDto addMonthInfo(Long storeId, ReservationMonthRequestDto reservationMonthRequestDto, User user);

  StatusResponseDto addDayInfo(Long mothInfoId, ReservationDayRequestDto reservationDayRequestDto,User user);

  List<ReservationMonthInfoResponseDto> showReservationMonthInfo(Long storeId,User user);

  List<ReservationDayInfoResponseDto> showReservationDayInfo(Long monthId,User user);

 List<ReservationDayInfoResponseDto> showReservations(Long storeId,Integer year,Integer month,Integer day);
  StatusResponseDto visitComplete(Long reservationId,User user);



  ReservationMonthInfo findReservationMonthInfoByYearInfoAndMonthInfoAndStoreId(Integer yearInfo, Integer monthInfo,
      Store store);
  List<ReservationDayInfo> findAllByReservationMonthInfoAndDayInfo(
      ReservationMonthInfo reservationMonthInfo, Integer dayInfo);
  ReservationDayInfo findReservationDayInfoByReservationMonthInfoAndDayInfo(
      ReservationMonthInfo reservationMonthInfo, Integer dayInfo);
}
