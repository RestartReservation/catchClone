package com.example.catchclone.reservation.controller;

import com.example.catchclone.common.dto.StatusResponseDto;
import com.example.catchclone.reservation.dto.ReservationDayInfoResponseDto;
import com.example.catchclone.reservation.dto.ReservationRequestDto;
import com.example.catchclone.reservation.dto.UserReservationResponseDto;
import com.example.catchclone.reservation.service.interfaces.ReservationService;
import com.example.catchclone.reservation.service.interfaces.UserReservationService;
import com.example.catchclone.security.UserDetailsImpl;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
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
  //유저 예약 조회
  @GetMapping
  public ResponseEntity<List<UserReservationResponseDto>> getUserReservations(@AuthenticationPrincipal UserDetailsImpl userDetails){
    List<UserReservationResponseDto> dtoList = reservationService.getUserReservations(userDetails.getUserId());
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    return ResponseEntity.ok().headers(headers).body(dtoList);
  }
}
