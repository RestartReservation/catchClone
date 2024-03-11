package com.example.catchclone.util.enums;

import java.util.Arrays;

public enum StoreCategoryEnum {

  JAPAN(CategoryCode.JAPAN),
  KOREAN(CategoryCode.KOREAN),
  CHINESE(CategoryCode.CHINESE),
  ITALIAN(CategoryCode.ITALIAN);

  private final String categoryCode;

  StoreCategoryEnum(String CategoryCode){
    this.categoryCode = CategoryCode;
  }

  public String getCategoryCode(){
    return this.categoryCode;
  }

  public static String findNameByValue(String value) {
    for (StoreCategoryEnum storeCategoryEnumValue : StoreCategoryEnum.values()) {
      if (storeCategoryEnumValue.getCategoryCode().equals(value)) {
        System.out.println(value);
        System.out.println(storeCategoryEnumValue.name());
        return storeCategoryEnumValue.name();
      }
    }
    return null; // 일치하는 값이 없을 경우
  }



  public static class CategoryCode{

    public static final String JAPAN ="1";
    public static final String KOREAN ="2";
    public static final String CHINESE ="3";
    public static final String ITALIAN ="4";

  }
}
