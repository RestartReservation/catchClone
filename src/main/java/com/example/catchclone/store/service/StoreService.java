package com.example.catchclone.store.service;

import com.example.catchclone.common.dto.StatusResponseDto;
import com.example.catchclone.store.dto.StoreCategoryDto;
import com.example.catchclone.store.dto.StoreMenuDto;
import com.example.catchclone.store.dto.StoreRequestDto;
import com.example.catchclone.store.entity.Store;
import com.example.catchclone.user.entity.User;
import java.util.List;

public interface StoreService {


  StatusResponseDto addStore(StoreRequestDto storeRequestDto, User user);

  StatusResponseDto addMenu(User user,Long storeId,List<StoreMenuDto> storeMenuDtoList);

  StatusResponseDto addCategory(Long storeId, StoreCategoryDto storeCategoryDto,User user);
}
