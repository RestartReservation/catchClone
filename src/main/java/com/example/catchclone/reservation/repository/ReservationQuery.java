package com.example.catchclone.reservation.repository;

import com.example.catchclone.reservation.entity.Reservation;
import java.util.List;
import java.util.Optional;

public interface ReservationQuery {

  List<Reservation> findByUserIdAndDayId(Long userId,Long dayId);

  void updateReservationFlag(Long reservationId);

}
