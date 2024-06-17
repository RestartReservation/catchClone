package com.example.catchclone.util;

import com.example.catchclone.comment.dao.CommentRepository;
import com.example.catchclone.reservation.dao.day.ReservationDayInfoRepository;
import com.example.catchclone.reservation.dao.month.ReservationMonthInfoRepository;
import com.example.catchclone.reservation.entity.ReservationDayInfo;
import com.example.catchclone.reservation.entity.ReservationMonthInfo;
import com.example.catchclone.review.dao.ReviewRepository;
import com.example.catchclone.review.dto.ReviewRequestDto;
import com.example.catchclone.review.entity.Review;
import com.example.catchclone.store.dao.StoreMenuRepository;
import com.example.catchclone.store.dao.StoreRepository;
import com.example.catchclone.store.entity.Store;
import com.example.catchclone.store.entity.StoreMenu;
import com.example.catchclone.user.entity.User;
import com.example.catchclone.user.repository.UserRepository;
import com.example.catchclone.user.service.UserServiceImpl;
import com.example.catchclone.util.enums.UserRoleEnum;
import jakarta.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestDataRunner implements ApplicationRunner {
  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;
  private final UserServiceImpl userService;
  private final StoreRepository storeRepository;
  private final ReservationMonthInfoRepository reservationMonthInfoRepository;
  private final ReservationDayInfoRepository reservationDayInfoRepository;
  private final ReviewRepository reviewRepository;
  private final CommentRepository commentRepository;
  private final StoreMenuRepository storeMenuRepository;
  @Override
  @Transactional
  public void run(ApplicationArguments args) throws Exception {
    System.out.println("현재시각");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    System.out.println(simpleDateFormat.format(new Date()));

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
        .monthInfo(6)
        .yearInfo(2024)
        .build();

    reservationMonthInfoRepository.saveAndFlush(reservationMonthInfo);

    ReservationDayInfo reservationDayInfo = ReservationDayInfo.builder()
        .reservationMonthInfo(reservationMonthInfo)
        .dayInfo(29)
        .timeInfo("22:00")
        .capacity(4)
        .build();

    ReservationDayInfo reservationDayInfo1 = ReservationDayInfo.builder()
        .reservationMonthInfo(reservationMonthInfo)
        .dayInfo(29)
        .timeInfo("12:00")
        .capacity(4)
        .build();

    ReservationDayInfo reservationDayInfo2 = ReservationDayInfo.builder()
        .reservationMonthInfo(reservationMonthInfo)
        .dayInfo(29)
        .timeInfo("13:00")
        .capacity(4)
        .build();

    User user = User.builder()
        .username("1111")
        .password(passwordEncoder.encode("1111"))
        .phoneNumber("1111")
        .aboutMe("1111")
        .nickName("1111")
        .profileUrl("1111")
        .role(UserRoleEnum.CUSTOMER)
        .build();


    StoreMenu storeMenu = StoreMenu.builder()
          .store(store)
              .menuNm("삼겹살")
                  .menuUrl("https://i.namu.wiki/i/tmV0sOpMjUBeo-aw6RMm4aRJFIcz3S_aj0kOCgQ65EWkA4Yjxs1NEBIZ4M6Nb_eHZJVut-Bt7832c-phZlWzmw.webp")
                      .menuPrice("10000")
                          .menuMain("??")
                              .build();
    StoreMenu storeMenu1 = StoreMenu.builder()
        .store(store)
        .menuNm("삼겹살1")
        .menuUrl("https://i.namu.wiki/i/tmV0sOpMjUBeo-aw6RMm4aRJFIcz3S_aj0kOCgQ65EWkA4Yjxs1NEBIZ4M6Nb_eHZJVut-Bt7832c-phZlWzmw.webp")
        .menuPrice("10000")
        .menuMain("??")
        .build();
    StoreMenu storeMenu2 = StoreMenu.builder()
        .store(store)
        .menuNm("삼겹살2")
        .menuUrl("https://i.namu.wiki/i/tmV0sOpMjUBeo-aw6RMm4aRJFIcz3S_aj0kOCgQ65EWkA4Yjxs1NEBIZ4M6Nb_eHZJVut-Bt7832c-phZlWzmw.webp")
        .menuPrice("10000")
        .menuMain("??")
        .build();
    StoreMenu storeMenu3 = StoreMenu.builder()
        .store(store)
        .menuNm("삼겹살3")
        .menuUrl("https://i.namu.wiki/i/tmV0sOpMjUBeo-aw6RMm4aRJFIcz3S_aj0kOCgQ65EWkA4Yjxs1NEBIZ4M6Nb_eHZJVut-Bt7832c-phZlWzmw.webp")
        .menuPrice("10000")
        .menuMain("??")
        .build();
    StoreMenu storeMenu4 = StoreMenu.builder()
        .store(store)
        .menuNm("삼겹살4")
        .menuUrl("https://i.namu.wiki/i/tmV0sOpMjUBeo-aw6RMm4aRJFIcz3S_aj0kOCgQ65EWkA4Yjxs1NEBIZ4M6Nb_eHZJVut-Bt7832c-phZlWzmw.webp")
        .menuPrice("10000")
        .menuMain("??")
        .build();
    List<String> list = new ArrayList<>();
    String reviewContent = "맛있어요";




    reservationDayInfoRepository.save(reservationDayInfo);
    reservationDayInfoRepository.save(reservationDayInfo1);
    reservationDayInfoRepository.save(reservationDayInfo2);

    storeRepository.save(store2);
    storeRepository.save(store3);
    storeRepository.save(store1);
    userRepository.save(user);
    storeMenuRepository.save(storeMenu);
    storeMenuRepository.save(storeMenu1);
    storeMenuRepository.save(storeMenu2);
    storeMenuRepository.save(storeMenu3);
    storeMenuRepository.save(storeMenu4);

    for(int i = 0; i < 7; i++){
      ReviewRequestDto reviewRequestDto = new ReviewRequestDto(1L,reviewContent + i,"testTitle" + i,((float)Math.floor((Math.random() * 5) + 1)),((float)Math.floor((Math.random() * 5) + 1)),((float)Math.floor((Math.random() * 5) + 1)),list);
      Review review = new Review(1L,1L,reviewRequestDto);
      reviewRepository.save(review);
    }


  }
}
