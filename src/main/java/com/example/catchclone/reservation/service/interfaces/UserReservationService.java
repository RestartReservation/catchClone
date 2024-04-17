package com.example.catchclone.reservation.service.interfaces;

import com.example.catchclone.common.dto.StatusResponseDto;
import com.example.catchclone.reservation.dto.ReservationRequestDto;
import com.example.catchclone.user.entity.User;

public interface UserReservationService {
  void addReservation(Long storeId,Long dayId, ReservationRequestDto reservationRequestDto,
      User user);

  StatusResponseDto cancelReservation(Long reservationId,User user);
}
