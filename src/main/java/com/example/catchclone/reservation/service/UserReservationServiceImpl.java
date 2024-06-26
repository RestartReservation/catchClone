package com.example.catchclone.reservation.service;

import com.example.catchclone.common.dto.StatusResponseDto;
import com.example.catchclone.reservation.dao.ReservationRepository;
import com.example.catchclone.reservation.dao.day.ReservationDayInfoRepository;
import com.example.catchclone.reservation.dao.month.ReservationMonthInfoRepository;
import com.example.catchclone.reservation.dto.ReservationDayInfoResponseDto;
import com.example.catchclone.reservation.dto.ReservationRequestDto;
import com.example.catchclone.reservation.dto.UserReservationResponseDto;
import com.example.catchclone.reservation.entity.Reservation;
import com.example.catchclone.reservation.entity.ReservationDayInfo;
import com.example.catchclone.reservation.entity.ReservationMonthInfo;
import com.example.catchclone.reservation.service.interfaces.UserReservationService;
import com.example.catchclone.store.dao.StoreRepository;
import com.example.catchclone.store.entity.Store;
import com.example.catchclone.user.entity.User;
import com.example.catchclone.user.repository.UserRepository;
import com.example.catchclone.user.service.UserService;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.swing.text.html.Option;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
public class UserReservationServiceImpl implements UserReservationService {
  private final StoreRepository storeRepository;
  private final ReservationRepository reservationRepository;
  private final ReservationDayInfoRepository reservationDayInfoRepository;

  private final ReservationMonthInfoRepository reservationMonthInfoRepository;
  private final UserService userService;

  @Override
  public Optional<Reservation> findReservationStatusVById(Long reservationId) {
    return reservationRepository.findReservationStatusVById(reservationId);
  }

  @Override
  @Transactional
  public StatusResponseDto cancelReservation(Long reservationId, User user) {

    Reservation reservation = reservationRepository.findById(reservationId)
        .orElseThrow( () -> new IllegalArgumentException("해당 예약 정보가 없습니다!"));

    if(reservation.getUser().getId()!=user.getId()) throw new IllegalArgumentException("예약자만 해당 예약을 취소할 수 있습니다!");

    if(Objects.equals(reservation.getReservationStatus(), "V")) {
      throw new IllegalArgumentException("방문 완료한 예약은 완료처리할 수 없습니다!");
    }

    Long reservationDayInfoId = reservation.getReservationDayInfoId();

    ReservationDayInfo reservationDayInfo = reservationDayInfoRepository.findById(reservationDayInfoId).orElseThrow(
        () -> new IllegalArgumentException("일 예약 정보를 찾을 수 없습니다!")
    );
    reservationRepository.updateReservationFlag(reservationId);
    reservationDayInfoRepository.updateCapacity(reservation.getReservationDayInfoId(),reservation.getNumberOfPeople() + reservationDayInfo.getCapacity());

    return new StatusResponseDto(201,"해당 가맹점의 예약이 취소되었습니다!");

  }

  @Override
  @Transactional(readOnly = true)
  public List<UserReservationResponseDto> getUserReservations(Long userId) {
    return reservationRepository.findUserReservationsByUserId(userId);
  }

  @Override
  @Transactional
  @Synchronized // 중복 예약 방지를 위해
  public StatusResponseDto addReservation(Long storeId,Long dayId, ReservationRequestDto reservationRequestDto,User user) {
    //가게 있는지 확인
    //중복 예약 있는지 확인
    // 예약 가능한지 확인
    // 예약 했다 -> 해당 예약 정보에서 인원수 감소시키기
    // 예약한다 -> 내가 예약하는 정보보다 인원수가 적다 -> 막기


    Store store = storeRepository.findById(storeId).orElseThrow(
        ()->new IllegalArgumentException("해당 가맹점은 존재하지 않습니다!"));

    ReservationDayInfo reservationDayInfo = reservationDayInfoRepository.findById(dayId).orElseThrow(
        ()-> new IllegalArgumentException("가게의 일 예약정보가 존재하지 않습니다!")
    );

    ReservationMonthInfo reservationMonthInfo = reservationMonthInfoRepository.findById(reservationDayInfo.getReservationMonthInfo().getId()).orElseThrow(
        () -> new IllegalArgumentException("해당 월 예약 정보가 없습니다!")
    );

    //현재 시간과 예약 정보의 시간을 비교하여, 현재 시간 기준 이전 예약은 할 수 없도록 막는 부분입니다.
    int timeInfo = Integer.parseInt(reservationDayInfo.getTimeInfo().split(":")[0]);
    int minuteInfo = Integer.parseInt(reservationDayInfo.getTimeInfo().split(":")[1]);

    LocalDateTime reservationTime = LocalDateTime.of(reservationMonthInfo.getYearInfo(),reservationMonthInfo.getMonthInfo(),reservationDayInfo.getDayInfo(),timeInfo,minuteInfo);

    if(!reservationTime.isAfter(LocalDateTime.now())) throw new IllegalArgumentException("현재 시간보다 이전의 예약은 진행할 수 없습니다!");
    List<Reservation> reservation = reservationRepository.findByUserIdAndDayId(user.getId(), dayId);


    if(!reservation.isEmpty()){

      if (reservation.stream().anyMatch(res -> res.getReservationStatus().equals("Y"))) {
        throw new IllegalArgumentException("이미 예약 정보가 존재합니다.");
      }

    }

    if(reservationDayInfo.getCapacity() < reservationRequestDto.getNumberOfPeople()) throw new IllegalArgumentException("예약가능 인원보다 예약요청 인원이 더 많습니다!!");

    Reservation forAddreservation = Reservation.builder()
        .user(user)
        .store(store)
        .reservationDayInfoId(dayId)
        .numberOfPeople(reservationRequestDto.getNumberOfPeople())
        .yearInfo(reservationRequestDto.getYearInfo())
        .dayInfo(reservationRequestDto.getDayInfo())
        .monthInfo(reservationRequestDto.getMonthInfo())
        .timeInfo(reservationRequestDto.getTimeInfo())
        .build();

    reservationRepository.save(forAddreservation);
    reservationDayInfoRepository.updateCapacity(dayId,reservationDayInfo.getCapacity()-forAddreservation.getNumberOfPeople());
    return new StatusResponseDto(201,"예약완료");

  }


}
