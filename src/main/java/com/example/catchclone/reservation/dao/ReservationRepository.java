package com.example.catchclone.reservation.dao;

import com.example.catchclone.reservation.entity.Reservation;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface ReservationRepository extends Repository<Reservation,Long>,ReservationQuery {

  void save(Reservation reservation);

  Optional<Reservation> findById(Long reservationId);

}
