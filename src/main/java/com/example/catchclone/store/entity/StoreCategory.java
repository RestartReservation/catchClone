package com.example.catchclone.store.entity;

import com.example.catchclone.store.dto.StoreCategoryDto;
import com.example.catchclone.util.enums.StoreCategoryEnum;
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
public class StoreCategory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name="store_id")
  private Store store;

  @Column
  private String categoryName;
  @Column
  private String categoryNum;


  @Builder
  public StoreCategory(Store store,StoreCategoryDto storeCategoryDto){
    this.store = store;
    this.categoryName = StoreCategoryEnum.findNameByValue(storeCategoryDto.getStoreCategoryNum());
    this.categoryNum = storeCategoryDto.getStoreCategoryNum();
  }

}
