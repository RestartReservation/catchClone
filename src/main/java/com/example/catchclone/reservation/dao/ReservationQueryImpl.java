package com.example.catchclone.reservation.dao;

import static com.example.catchclone.reservation.entity.QReservation.reservation;
import static com.example.catchclone.reservation.entity.QReservationDayInfo.reservationDayInfo;
import static com.example.catchclone.reservation.entity.QReservationMonthInfo.reservationMonthInfo;
import static com.example.catchclone.store.entity.QStore.store;

import com.example.catchclone.reservation.dto.ReservationDayInfoResponseDto;
import com.example.catchclone.reservation.dto.UserReservationResponseDto;
import com.example.catchclone.reservation.entity.QReservation;
import com.example.catchclone.reservation.entity.Reservation;
import com.example.catchclone.store.entity.QStore;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class ReservationQueryImpl implements ReservationQuery{

  private final JPAQueryFactory jpaQueryFactory;
  @Override
  @Transactional(readOnly = true)
  public boolean existsReservationById(Long reservationId) {
    return jpaQueryFactory.from(reservation).where(reservation.id.eq(reservationId)).select(reservation.id)
        .setHint("org.hibernate.readOnly", true).fetchFirst() != null;
  }

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
  public List<UserReservationResponseDto> findUserReservationsByUserId(Long userId) {
    QReservation qReservation = QReservation.reservation;
    QStore qStore = QStore.store;

    return jpaQueryFactory
        .select(Projections.bean(
            UserReservationResponseDto.class,
            qReservation.id.as("reservationId"),
            qStore.id.as("storeId"),
            qStore.storeName,
            qReservation.yearInfo,
            qReservation.monthInfo,
            qReservation.dayInfo,
            qReservation.timeInfo,
            qReservation.reservationStatus
        ))
        .from(qReservation)
        .leftJoin(qReservation.store, qStore)
        .where(qReservation.user.id.eq(userId))
        .fetch();
  }

  @Override
  public boolean findMontInfoByYearAndMonth(Integer yearInfo, Integer monthInfo) {
    return jpaQueryFactory.select(reservationMonthInfo)
        .from(reservationMonthInfo)
        .where(reservationMonthInfo.yearInfo.eq(yearInfo),reservationMonthInfo.monthInfo.eq(monthInfo))
        .fetchOne()!=null;
  }

  @Override
  public Optional<Reservation> findReservationStatusVById(Long reservationId) {

    Optional<Reservation> rs = Optional.ofNullable(
        jpaQueryFactory.select(reservation)
            .from(reservation)
            .where(reservation.id.eq(reservationId),reservation.reservationStatus.eq("V"))
            .fetchOne()
    );

    if(rs.isEmpty()) throw new IllegalArgumentException("해당 예약이 없거나, 방문완료 상태가 아닙니다!");
    return rs;
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
