package com.example.catchclone.reservation.repository.month;

import com.example.catchclone.reservation.entity.ReservationMonthInfo;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface ReservationMonthInfoRepository extends Repository<ReservationMonthInfo,Long>,ReservationMonthInfoRepositoryQuery {

  void save(ReservationMonthInfo reservationMonthInfo);

  Optional<ReservationMonthInfo> findById(Long monthInfoId);



}
