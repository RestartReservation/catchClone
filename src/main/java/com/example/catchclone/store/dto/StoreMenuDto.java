package com.example.catchclone.store.dto;

import com.example.catchclone.store.entity.StoreMenu;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreMenuDto {
  private String menuNm;
  private String menuUrl;
  private String menuPrice;
  private String menuMain;

  public static StoreMenuDto from(StoreMenu storeMenu) {
    return new StoreMenuDto(storeMenu.getMenuNm(),storeMenu.getMenuUrl(),storeMenu.getMenuPrice(),storeMenu.getMenuMain());
  }
}

