package com.example.catchclone.reservation.dao.day;

import com.example.catchclone.reservation.entity.ReservationDayInfo;
import com.example.catchclone.reservation.entity.ReservationMonthInfo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface ReservationDayInfoRepository extends Repository<ReservationDayInfo,Long>,ReservationDayInfoRepositoryQuery {


  void save(ReservationDayInfo reservationDayInfo);

  Optional<ReservationDayInfo> findById(Long dayId);

  Optional<ReservationDayInfo> findByReservationMonthInfoAndDayInfo(ReservationMonthInfo reservationMonthInfo,Integer dayInfo);

  List<ReservationDayInfo> findAllByReservationMonthInfoAndDayInfo(ReservationMonthInfo reservationMonthInfo,Integer dayInfo);
}
