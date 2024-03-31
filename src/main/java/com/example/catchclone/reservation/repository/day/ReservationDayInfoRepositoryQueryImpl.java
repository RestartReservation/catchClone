package com.example.catchclone.reservation.repository.day;

import com.example.catchclone.reservation.dto.ReservationDayInfoResponseDto;
import static com.example.catchclone.reservation.entity.QReservationDayInfo.reservationDayInfo;

import com.example.catchclone.reservation.entity.ReservationDayInfo;
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
}
