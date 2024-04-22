package com.example.catchclone.util;

import com.example.catchclone.comment.dao.CommentRepository;
import com.example.catchclone.reservation.dao.day.ReservationDayInfoRepository;
import com.example.catchclone.reservation.dao.month.ReservationMonthInfoRepository;
import com.example.catchclone.reservation.entity.ReservationDayInfo;
import com.example.catchclone.reservation.entity.ReservationMonthInfo;
import com.example.catchclone.review.dao.ReviewRepository;
import com.example.catchclone.review.entity.Review;
import com.example.catchclone.store.dao.StoreRepository;
import com.example.catchclone.store.entity.Store;
import com.example.catchclone.user.entity.User;
import com.example.catchclone.user.repository.UserRepository;
import com.example.catchclone.user.service.UserServiceImpl;
import com.example.catchclone.util.enums.UserRoleEnum;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestDataRunner implements ApplicationRunner {

  private final UserRepository userRepository;
  private final UserServiceImpl userService;
  private final PasswordEncoder passwordEncoder;
  private final StoreRepository storeRepository;
  private final ReservationMonthInfoRepository reservationMonthInfoRepository;
  private final ReservationDayInfoRepository reservationDayInfoRepository;
  private final ReviewRepository reviewRepository;
  private final CommentRepository commentRepository;

  @Override
  @Transactional
  public void run(ApplicationArguments args) throws Exception {
    Store store = Store.builder()
        .storeName("TestStore")
        .storeLocation("Undefined")
        .starRate(5F)
        .timeDetail("09:00 ~ 21:00")
        .storePhoneNumber("999-999-9999")
        .aboutStore("Test용입니다")
        .storeNotification("No")
        .reservationTypeFlag("No")
        .regularHoliday("No")
        .storeHomepage("No")
        .build();

    Store store1 = Store.builder()
        .storeName("TestStore2")
        .storeLocation("Undefined")
        .starRate(5F)
        .timeDetail("09:00 ~ 21:00")
        .storePhoneNumber("999-999-9999")
        .aboutStore("Test용입니다")
        .storeNotification("No")
        .reservationTypeFlag("No")
        .regularHoliday("No")
        .storeHomepage("No")
        .build();


    Store store2 = Store.builder()
        .storeName("TestStore3")
        .storeLocation("Undefined")
        .starRate(5F)
        .timeDetail("09:00 ~ 21:00")
        .storePhoneNumber("999-999-9999")
        .aboutStore("Test용입니다")
        .storeNotification("No")
        .reservationTypeFlag("No")
        .regularHoliday("No")
        .storeHomepage("No")
        .build();

    Store store3 = Store.builder()
        .storeName("TestStore4")
        .storeLocation("Undefined")
        .starRate(5F)
        .timeDetail("09:00 ~ 21:00")
        .storePhoneNumber("999-999-9999")
        .aboutStore("Test용입니다")
        .storeNotification("No")
        .reservationTypeFlag("No")
        .regularHoliday("No")
        .storeHomepage("No")
        .build();

    storeRepository.saveAndFlush(store);

    ReservationMonthInfo reservationMonthInfo = ReservationMonthInfo.builder()
        .store(store)
        .monthInfo(4)
        .yearInfo(2024)
        .build();

    reservationMonthInfoRepository.saveAndFlush(reservationMonthInfo);

    ReservationDayInfo reservationDayInfo = ReservationDayInfo.builder()
        .reservationMonthInfo(reservationMonthInfo)
        .dayInfo(23)
        .timeInfo("22:00")
        .capacity(4)
        .build();

    ReservationDayInfo reservationDayInfo1 = ReservationDayInfo.builder()
        .reservationMonthInfo(reservationMonthInfo)
        .dayInfo(23)
        .timeInfo("12:00")
        .capacity(4)
        .build();

    ReservationDayInfo reservationDayInfo2 = ReservationDayInfo.builder()
        .reservationMonthInfo(reservationMonthInfo)
        .dayInfo(23)
        .timeInfo("13:00")
        .capacity(4)
        .build();

    reservationDayInfoRepository.save(reservationDayInfo);
    reservationDayInfoRepository.save(reservationDayInfo1);
    reservationDayInfoRepository.save(reservationDayInfo2);

    storeRepository.save(store2);
    storeRepository.save(store3);
    storeRepository.save(store1);


  }
}
