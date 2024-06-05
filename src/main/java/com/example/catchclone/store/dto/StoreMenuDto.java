package com.example.catchclone.store.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StoreMenuDto {
  private String menuNm;
  private String menuUrl;
  private String menuPrice;
  private String menuMain;
}
