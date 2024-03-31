package com.example.catchclone.reservation.repository.month;

import com.example.catchclone.reservation.dto.ReservationMonthInfoResponseDto;
import java.util.List;

public interface ReservationMonthInfoRepositoryQuery {


  List<ReservationMonthInfoResponseDto> reservationMonthInfoByStoreId(Long storeId);


}
