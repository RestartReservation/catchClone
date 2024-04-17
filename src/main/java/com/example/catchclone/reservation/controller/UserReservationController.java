package com.example.catchclone.reservation.controller;

import com.example.catchclone.common.dto.StatusResponseDto;
import com.example.catchclone.reservation.dto.ReservationRequestDto;
import com.example.catchclone.reservation.service.interfaces.ReservationService;
import com.example.catchclone.reservation.service.interfaces.UserReservationService;
import com.example.catchclone.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ct/reservations/users")
public class UserReservationController {
  private final UserReservationService reservationService;
  //예약하기
  @PostMapping("/{storeId}/{dayId}")
  public void addReservation(@PathVariable Long storeId,@PathVariable Long dayId,@RequestBody
  ReservationRequestDto reservationRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){

    reservationService.addReservation(storeId,dayId,reservationRequestDto,userDetails.getUser());
  }

  //예약 취소하기
  @PutMapping("/cancel/{reservationId}")
  public ResponseEntity<StatusResponseDto> cancelReservation(@PathVariable Long reservationId,@AuthenticationPrincipal UserDetailsImpl userDetails){


    return ResponseEntity.ok().body(reservationService.cancelReservation(reservationId,userDetails.getUser()));
  }
}
