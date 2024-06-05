package com.example.catchclone.store.dto;


import com.example.catchclone.store.entity.Store;
import com.example.catchclone.store.entity.StoreMenu;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StoreDetailsResponseDto {
  private String storeName; //가맹점이름
  private String storeLocation; //가맹점위치정보
  private Float starRate; //별점
  private String timeDetail; // 변수명 변경 예정, 운영시간
  private String storePhoneNumber;  //가게전화번호
  private String aboutStore;  //가게소개
  private String storeNotification; //가맹점공지
  private String reservationTypeFlag; //예약 지정일자 오픈 여부(Y,N)
  private String regularHoliday; //정기 휴무일
  private String storeHomepage; //홈페이지 주소
  private List<StoreMenuDto> storeMenuDtoList;

//  public static StoreDetailsResponseDto from(Store store){
//    return new StoreDetailsResponseDto(store.getStoreName(),store.getStoreLocation(),store.getStarRate(),store.getTimeDetail(),store.getStorePhoneNumber(),store.getAboutStore()
//    ,store.getStoreNotification(),store.getReservationTypeFlag(),store.getRegularHoliday(),store.getStoreHomepage());
//  }
}
