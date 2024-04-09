package com.example.catchclone.store.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StoreIndexResponseDto {
  private Long id;
  private String storeName;
  private String storeLocation;
  private Float starRate;
}
