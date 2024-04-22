package com.example.catchclone.reservation.dao.month;

import com.example.catchclone.reservation.entity.ReservationDayInfo;
import com.example.catchclone.reservation.entity.ReservationMonthInfo;
import com.example.catchclone.store.entity.Store;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface ReservationMonthInfoRepository extends Repository<ReservationMonthInfo,Long>,ReservationMonthInfoRepositoryQuery {

  void save(ReservationMonthInfo reservationMonthInfo);
  void saveAndFlush(ReservationMonthInfo reservationMonthInfo);
  Optional<ReservationMonthInfo> findById(Long monthInfoId);

  Optional<ReservationMonthInfo> findByStore(Store store);

  Optional<ReservationMonthInfo> findByYearInfoAndMonthInfoAndStore(Integer yearInfo,Integer monthInfo, Store store);

}
