package com.example.catchclone.reservation.dao;

import static com.example.catchclone.reservation.entity.QReservation.reservation;
import com.example.catchclone.reservation.entity.Reservation;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReservationQueryImpl implements ReservationQuery{

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public void updateReservationFlagVisitComplete(Long reservationId) {

    jpaQueryFactory.update(reservation)
        .set(reservation.reservationStatus,"V")
        .where(reservation.id.eq(reservationId))
        .execute();
  }

  @Override
  public Reservation findByUserIdAndStoreId(Long userId, Long storeId) {

    return  jpaQueryFactory.select(reservation)
        .from(reservation)
        .where(reservation.user.id.eq(userId),reservation.store.id.eq(storeId),reservation.reservationStatus.eq("V"))
        .fetchOne();

  }

  @Override
  public List<Reservation> findByUserIdAndDayId(Long userId,Long dayId) {

    return jpaQueryFactory.select(reservation
    ).from(reservation).where(reservation.user.id.eq(userId),reservation.reservationDayInfoId.eq(dayId))
        .fetch();


  }

  @Override
  public void updateReservationFlag(Long reservationId) {

    jpaQueryFactory.update(reservation)
        .set(reservation.reservationStatus,"N")
        .where(reservation.id.eq(reservationId))
        .execute();
  }
}
