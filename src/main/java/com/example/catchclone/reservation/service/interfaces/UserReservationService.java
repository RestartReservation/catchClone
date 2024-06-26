package com.example.catchclone.reservation.service.interfaces;

import com.example.catchclone.common.dto.StatusResponseDto;
import com.example.catchclone.reservation.dto.ReservationRequestDto;
import com.example.catchclone.reservation.dto.UserReservationResponseDto;
import com.example.catchclone.reservation.entity.Reservation;
import com.example.catchclone.user.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserReservationService {
  StatusResponseDto addReservation(Long storeId,Long dayId, ReservationRequestDto reservationRequestDto,
      User user);
  StatusResponseDto cancelReservation(Long reservationId,User user);
  List<UserReservationResponseDto> getUserReservations(Long userId);

  Optional<Reservation> findReservationStatusVById(Long reservationId);
}
