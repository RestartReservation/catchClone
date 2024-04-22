package com.example.catchclone.reservation.dao.day;

import com.example.catchclone.reservation.dto.ReservationDayInfoResponseDto;
import static com.example.catchclone.reservation.entity.QReservationDayInfo.reservationDayInfo;
import static com.example.catchclone.reservation.entity.QReservationMonthInfo.reservationMonthInfo;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReservationDayInfoRepositoryQueryImpl implements ReservationDayInfoRepositoryQuery {

  private final JPAQueryFactory jpaQueryFactory;


  @Override
  public void updateCapacity(Long dayId,Integer minusCount) {
    jpaQueryFactory.update(reservationDayInfo)
        .set(reservationDayInfo.capacity,minusCount)
        .where(reservationDayInfo.id.eq(dayId))
        .execute();
  }

  @Override
  public void plusCapacity(Long reservationDayInfoId, Integer capacity) {
    jpaQueryFactory.update(reservationDayInfo)
        .set(reservationDayInfo.capacity,capacity)
        .where(reservationDayInfo.id.eq(reservationDayInfoId))
        .execute();
  }

  @Override
  public List<ReservationDayInfoResponseDto> reservationDayInfoByMonthId(Long monthId) {

    List<ReservationDayInfoResponseDto> result =
        jpaQueryFactory.select(
            Projections.bean(
                ReservationDayInfoResponseDto.class,
                reservationDayInfo.dayInfo,
                reservationDayInfo.timeInfo,
                reservationDayInfo.isAvailable,
                reservationDayInfo.capacity
            )
        ).from(reservationDayInfo)
            .where(reservationDayInfo.reservationMonthInfo.id.eq(monthId))
            .fetch();

    return result;
  }

  @Override
  public List<ReservationDayInfoResponseDto> findReservationInfos(Long storeId, Integer year,
      Integer month, Integer day) {
    return jpaQueryFactory.select(
        Projections.bean(
            ReservationDayInfoResponseDto.class,
            reservationDayInfo.dayInfo,
            reservationDayInfo.timeInfo,
            reservationDayInfo.isAvailable,
            reservationDayInfo.capacity
        )
    ).from(reservationMonthInfo)
        .leftJoin(reservationMonthInfo.reservationDayInfo, reservationDayInfo)
        .where(reservationMonthInfo.store.id.eq(storeId)
            .and(reservationMonthInfo.yearInfo.eq(year))
            .and(reservationMonthInfo.monthInfo.eq(month))
            .and(reservationDayInfo.dayInfo.eq(day)))
        .fetch();

  }
}
