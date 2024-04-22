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
import com.example.catchclone.reservation.dao.ReservationRepository;
import com.example.catchclone.reservation.dao.day.ReservationDayInfoRepository;
import com.example.catchclone.reservation.dao.month.ReservationMonthInfoRepository;
import com.example.catchclone.reservation.service.interfaces.ReservationService;
import com.example.catchclone.store.entity.Store;
import com.example.catchclone.store.dao.StoreRepository;
import com.example.catchclone.store.service.StoreServiceImpl;
import com.example.catchclone.user.entity.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

  private final StoreRepository storeRepository;
  private final ReservationRepository reservationRepository;
  private final ReservationMonthInfoRepository reservationMonthInfoRepository;
  private final ReservationDayInfoRepository reservationDayInfoRepository;
  private final StoreServiceImpl storeService;

  @Override
  @Transactional
  public StatusResponseDto visitComplete(Long reservationId, User user) {

    Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(
        () -> new IllegalArgumentException("해당 예약 정보가 없습니다!")
    );

    if(Objects.equals(reservation.getReservationStatus(), "N")) {
      throw new IllegalArgumentException("취소한 예약은 완료처리할 수 없습니다!");
    }

    if(!Objects.equals(reservation.getStore().getUser().getId(), user.getId())){
     throw new IllegalArgumentException("해당 가맹 점주가 아닙니다!");
    }


    reservationRepository.updateReservationFlagVisitComplete(reservationId);
    return new StatusResponseDto(201,"해당 가맹점의 예약이 방문완료 처리 되었습니다!");
  }



  @Override
  public ReservationDayInfo findReservationDayInfoByReservationMonthInfoAndDayInfo(
      ReservationMonthInfo reservationMonthInfo, Integer dayInfo) {
    return reservationDayInfoRepository.findByReservationMonthInfoAndDayInfo(reservationMonthInfo,dayInfo).orElseThrow(
        () -> new IllegalArgumentException("해당 가맹점의 일 예약정보가 존재하지 않습니다!")
    );
  }

  @Override
  public ReservationMonthInfo findReservationMonthInfoByYearInfoAndMonthInfoAndStoreId(Integer yearInfo, Integer monthInfo,
      Store store) {
    return reservationMonthInfoRepository.findByYearInfoAndMonthInfoAndStore(yearInfo,monthInfo,store).orElseThrow(
        () -> new IllegalArgumentException("해당 가맹점의 월 예약정보가 존재하지 않습니다!")
    );
  }

  @Override
  public List<ReservationDayInfo> findAllByReservationMonthInfoAndDayInfo(
      ReservationMonthInfo reservationMonthInfo, Integer dayInfo) {
    return reservationDayInfoRepository.findAllByReservationMonthInfoAndDayInfo(reservationMonthInfo,dayInfo);
  }

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

    reservationDayInfoRepository.save(new ReservationDayInfo(reservationMonthInfo,reservationDayRequestDto));

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
  @Transactional(readOnly = true)
  public List<ReservationDayInfoResponseDto> showReservations(Long storeId,Integer year,Integer month,Integer day) {
    Store store = storeService.findStoreByStoreId(storeId);
    ReservationMonthInfo reservationMonthInfo = findReservationMonthInfoByYearInfoAndMonthInfoAndStoreId(year,month,store);
    List<ReservationDayInfo> reservationDayInfoList =findAllByReservationMonthInfoAndDayInfo(reservationMonthInfo,day);

   return reservationDayInfoList.stream()
       .map(reservationDayInfo -> new ReservationDayInfoResponseDto(
           reservationDayInfo.getId(),
           reservationDayInfo.getDayInfo(),
           reservationDayInfo.getTimeInfo(),
           reservationDayInfo.getIsAvailable(),
           reservationDayInfo.getCapacity()
       ))
       .collect(Collectors.toList());
  }


  @Override
  @Transactional
  public StatusResponseDto addMonthInfo(Long storeId, ReservationMonthRequestDto reservationMonthRequestDto,
      User user) {

    Store store = storeRepository.findById(storeId).orElseThrow(
        ()->new IllegalArgumentException("해당 가맹점은 존재하지 않습니다!"));

    if(!store.getUser().getId().equals(user.getId()))
      throw new IllegalArgumentException("해당 가맹 점주만 예약 정보등록이 가능합니다!");


    reservationMonthInfoRepository.save(new ReservationMonthInfo(store,reservationMonthRequestDto));

    return new StatusResponseDto(201,"가게 월 예약 정보 등록이 완료되었습니다!");

  }


}
