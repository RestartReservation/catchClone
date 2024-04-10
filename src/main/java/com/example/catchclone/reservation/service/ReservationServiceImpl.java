package com.example.catchclone.reservation.service;

import com.example.catchclone.common.dto.StatusResponseDto;
import com.example.catchclone.reservation.dto.ReservationDayInfoResponseDto;
import com.example.catchclone.reservation.dto.ReservationMonthInfoResponseDto;
import com.example.catchclone.reservation.dto.ReservationRequestDto;
import com.example.catchclone.reservation.entity.Reservation;
import com.example.catchclone.reservation.entity.ReservationDayInfo;
import com.example.catchclone.reservation.entity.ReservationMonthInfo;
import com.example.catchclone.reservation.dto.ReservationDayRequestDto;
import com.example.catchclone.reservation.dto.ReservationMonthRequestDto;
import com.example.catchclone.reservation.repository.ReservationQuery;
import com.example.catchclone.reservation.repository.ReservationRepository;
import com.example.catchclone.reservation.repository.day.ReservationDayInfoRepository;
import com.example.catchclone.reservation.repository.month.ReservationMonthInfoRepository;
import com.example.catchclone.store.entity.Store;
import com.example.catchclone.store.repository.StoreRepository;
import com.example.catchclone.user.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{

  private final StoreRepository storeRepository;

  private final ReservationRepository reservationRepository;
  private final ReservationMonthInfoRepository reservationMonthInfoRepository;
  private final ReservationDayInfoRepository reservationDayInfoRepository;

  @Override
  @Transactional
  public StatusResponseDto addDayInfo(Long mothInfoId, ReservationDayRequestDto reservationDayRequestDto,
      User user) {

    ReservationMonthInfo reservationMonthInfo = reservationMonthInfoRepository.findById(mothInfoId).
        orElseThrow(
            () -> new IllegalArgumentException("해당 가맹점의 월 예약 정보가 없습니다!")
        );

    Store store = storeRepository.findById(reservationMonthInfo.getStore().getId()).orElseThrow(
        ()->new IllegalArgumentException("해당 가맹점은 존재하지 않습니다!"));

    reservationDayInfoRepository.save(ReservationDayInfo.builder()
            .reservationMonthInfo(reservationMonthInfo)
            .reservationDayRequestDto(reservationDayRequestDto)
            .build());

    return new StatusResponseDto(201,"가게 일 예약 정보 등록이 완료되었습니다!");
  }

  @Override
  @Transactional(readOnly = true)
  public List<ReservationMonthInfoResponseDto> showReservationMonthInfo(Long storeId, User user) {

    Store store = storeRepository.findById(storeId).orElseThrow(
        ()->new IllegalArgumentException("해당 가맹점은 존재하지 않습니다!"));

    //예약 정보 조회는 비로그인 상태로 열람가능 -> 로그인 정보 확인 필요없을 듯함
    return reservationMonthInfoRepository.reservationMonthInfoByStoreId(storeId);
  }

  @Override
  @Transactional(readOnly = true)
  public List<ReservationDayInfoResponseDto> showReservationDayInfo(Long monthId, User user) {

    ReservationMonthInfo reservationMonthInfo = reservationMonthInfoRepository.findById(monthId).orElseThrow(
        () -> new IllegalArgumentException("해당 가맹점의 월 예약정보가 존재하지 않습니다!")
    );

    Store store = storeRepository.findById(reservationMonthInfo.getStore().getId()).orElseThrow(
        ()->new IllegalArgumentException("해당 가맹점은 존재하지 않습니다!"));

    return reservationDayInfoRepository.reservationDayInfoByMonthId(monthId);
  }

  @Override
  @Transactional
  public StatusResponseDto cancelReservation(Long reservationId, User user) {

    Reservation reservation = reservationRepository.findById(reservationId)
        .orElseThrow( () -> new IllegalArgumentException("해당 예약 정보가 없습니다!"));

    if(reservation.getUser().getId()!=user.getId()) throw new IllegalArgumentException("예약자만 해당 예약을 췩소할 수 있습니다!");

    Long reservationDayInfoId = reservation.getReservationDayInfoId();

    ReservationDayInfo reservationDayInfo = reservationDayInfoRepository.findById(reservationDayInfoId).orElseThrow(
        () -> new IllegalArgumentException("일 예약 정보를 찾을 수 없습니다!")
    );
    reservationRepository.updateReservationFlag(reservationId);
    reservationDayInfoRepository.updateCapacity(reservation.getReservationDayInfoId(),reservation.getReservationCount() + reservationDayInfo.getCapacity());

    return new StatusResponseDto(201,"해당 가맹점의 예약이 취소되었습니다!");

  }

  @Override
  @Transactional
  @Synchronized // 중복 예약 방지를 위해
  public void addReservation(Long storeId,Long dayId, ReservationRequestDto reservationRequestDto,User user) {
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

    List<Reservation> reservation = reservationRepository.findByUserIdAndDayId(user.getId(), dayId);


    if(!reservation.isEmpty()){

      if (reservation.stream().anyMatch(res -> res.getReservationStatus().equals("Y"))) {
        throw new IllegalArgumentException("이미 예약 정보가 존재합니다.");
      }

    }

    if(reservationDayInfo.getCapacity() < reservationRequestDto.getReservationCount()) throw new IllegalArgumentException("예약가능 인원보다 예약요청 인원이 더 많습니다!!");

    Reservation forAddreservation = Reservation.builder()
        .user(user)
        .store(store)
        .reservationDayInfoId(dayId)
        .reservationRequestDto(reservationRequestDto)
        .build();

    reservationRepository.save(forAddreservation);
    reservationDayInfoRepository.updateCapacity(dayId,reservationDayInfo.getCapacity()-forAddreservation.getReservationCount());


  }

  @Override
  @Transactional
  public StatusResponseDto addMonthInfo(Long storeId, ReservationMonthRequestDto reservationMonthRequestDto,
      User user) {

    Store store = storeRepository.findById(storeId).orElseThrow(
        ()->new IllegalArgumentException("해당 가맹점은 존재하지 않습니다!"));

    if(!store.getUser().getId().equals(user.getId()))
      throw new IllegalArgumentException("해당 가맹 점주만 예약 정보등록이 가능합니다!");


    reservationMonthInfoRepository.save(ReservationMonthInfo.builder()
        .store(store)
        .reservationMonthRequestDto(reservationMonthRequestDto)
        .build());

    return new StatusResponseDto(201,"가게 월 예약 정보 등록이 완료되었습니다!");

  }
}
