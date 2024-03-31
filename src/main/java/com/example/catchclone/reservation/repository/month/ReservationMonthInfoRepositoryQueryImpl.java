package com.example.catchclone.reservation.repository.month;

import com.example.catchclone.reservation.dto.ReservationMonthInfoResponseDto;
import static com.example.catchclone.reservation.entity.QReservationMonthInfo.reservationMonthInfo;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReservationMonthInfoRepositoryQueryImpl implements ReservationMonthInfoRepositoryQuery{

  private final JPAQueryFactory jpaQueryFactory;


  @Override
  public List<ReservationMonthInfoResponseDto> reservationMonthInfoByStoreId(Long storeId) {
        List<ReservationMonthInfoResponseDto> result =
        jpaQueryFactory.select(
        Projections.bean(
            ReservationMonthInfoResponseDto.class
            ,reservationMonthInfo.yearInfo
            ,reservationMonthInfo.monthInfo
    ))
            .from(reservationMonthInfo)
            .where(reservationMonthInfo.store.id.eq(storeId))
            .fetch();

        for(ReservationMonthInfoResponseDto rs : result){
          System.out.println(rs.getYearInfo());
          System.out.println(rs.getMonthInfo());
        }
    return result;
  }
}
