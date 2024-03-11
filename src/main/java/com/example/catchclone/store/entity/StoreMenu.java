package com.example.catchclone.store.entity;

import com.example.catchclone.store.dto.StoreMenuDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class StoreMenu {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name="store_id")
  private Store store;

  @Column
  private String menuNm ; //메뉴이름

  @Column
  private String menuUrl ; //메뉴사진

  @Column
  private String menuPrice ; //메뉴가격

  @Column
  private String menuMain ; //메인메뉴

  @Builder
  public StoreMenu(Store store,String menuNm, String menuUrl, String menuPrice,String menuMain){

    this.store = store;
    this.menuNm = menuNm;
    this.menuUrl = menuUrl;
    this.menuPrice = menuPrice;
    this.menuMain = menuMain;

  }

  public StoreMenu(Store store, StoreMenuDto storeMenuDto){

    this.store = store;
    this.menuNm = storeMenuDto.getMenuNm();
    this.menuUrl = storeMenuDto.getMenuUrl();
    this.menuPrice = storeMenuDto.getMenuPrice();
    this.menuMain = storeMenuDto.getMenuMain();

  }

}
