package com.example.catchclone.reservation.controller;

import com.example.catchclone.common.dto.StatusResponseDto;
import com.example.catchclone.reservation.dto.ReservationDayInfoResponseDto;
import com.example.catchclone.reservation.dto.ReservationDayRequestDto;
import com.example.catchclone.reservation.dto.ReservationMonthInfoResponseDto;
import com.example.catchclone.reservation.dto.ReservationMonthRequestDto;
import com.example.catchclone.reservation.dto.ReservationRequestDto;
import com.example.catchclone.reservation.service.ReservationService;
import com.example.catchclone.security.UserDetailsImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ct/reservations")
public class ReservationController {

  private final ReservationService reservationService;

  // 가게 월 예약 정보 등록
  @PostMapping("/month/{storeId}")
  public ResponseEntity<StatusResponseDto> addMonthReservationInfo(@PathVariable Long storeId,@RequestBody ReservationMonthRequestDto reservationMonthRequestDto,@AuthenticationPrincipal
      UserDetailsImpl userDetails){

    return ResponseEntity.ok().body(reservationService.addMonthInfo(storeId,reservationMonthRequestDto,userDetails.getUser()));

  }

  // 가게 일 예약 정보 등록
  @PostMapping("/day/{monthInfoId}")
  public ResponseEntity<StatusResponseDto> addDayReservationInfo(@PathVariable Long monthInfoId,@RequestBody ReservationDayRequestDto reservationDayRequestDto,@AuthenticationPrincipal
  UserDetailsImpl userDetails){

    return ResponseEntity.ok().body(reservationService.addDayInfo(monthInfoId,reservationDayRequestDto,userDetails.getUser()));

  }

  //해당 가맹점 월 예약 정보 조회
  @GetMapping("/month/{storeId}")
  public List<ReservationMonthInfoResponseDto> showReservationMonthInfo(@PathVariable Long storeId,@AuthenticationPrincipal UserDetailsImpl userDetails){
    return reservationService.showReservationMonthInfo(storeId,userDetails.getUser());
  }

  //해당 가맹점 일 예약 정보 조회
  @GetMapping("/day/{monthId}")
  public List<ReservationDayInfoResponseDto> showReservationDayInfo(@PathVariable Long monthId,@AuthenticationPrincipal UserDetailsImpl userDetails){

    return reservationService.showReservationDayInfo(monthId,userDetails.getUser());
  }

  //예약하기
  @PostMapping("/{storeId}/{dayId}")
  public void addReservation(@PathVariable Long storeId,@PathVariable Long dayId,@RequestBody
      ReservationRequestDto reservationRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){

    reservationService.addReservation(storeId,dayId,reservationRequestDto,userDetails.getUser());
  }

  //예약 취소하기
  @PutMapping("/{reservationId}")
  public ResponseEntity<StatusResponseDto> cancelReservation(@PathVariable Long reservationId,@AuthenticationPrincipal UserDetailsImpl userDetails){


    return ResponseEntity.ok().body(reservationService.cancelReservation(reservationId,userDetails.getUser()));
  }

}
