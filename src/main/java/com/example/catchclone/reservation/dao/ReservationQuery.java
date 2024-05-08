package com.example.catchclone.reservation.dao;

import com.example.catchclone.reservation.dto.UserReservationResponseDto;
import com.example.catchclone.reservation.entity.Reservation;
import java.util.List;
import java.util.Optional;

public interface ReservationQuery {

  List<Reservation> findByUserIdAndDayId(Long userId,Long dayId);

  void updateReservationFlag(Long reservationId);

  void updateReservationFlagVisitComplete(Long reservationId);

  Reservation findByUserIdAndStoreId(Long userId,Long storeId);

  List<UserReservationResponseDto> findUserReservationsByUserId(Long userId);

  Optional<Reservation> findReservationStatusVById(Long reservationId);

  boolean findMontInfoByYearAndMonth(Integer yearInfo,Integer monthInfo);

}
