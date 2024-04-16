package com.example.catchclone.util;

import com.example.catchclone.comment.dao.CommentRepository;
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

    storeRepository.saveAndFlush(store);


  }
}
