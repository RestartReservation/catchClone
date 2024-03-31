package com.example.catchclone.reservation.repository;

import static com.example.catchclone.reservation.entity.QReservation.reservation;
import com.example.catchclone.reservation.entity.Reservation;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReservationQueryImpl implements ReservationQuery{

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public List<Reservation> findByUserIdAndDayId(Long userId,Long dayId) {

    return jpaQueryFactory.select(reservation
    ).from(reservation).where(reservation.user.id.eq(userId),reservation.reservationDayInfoId.eq(dayId))
        .fetch();


  }
}
