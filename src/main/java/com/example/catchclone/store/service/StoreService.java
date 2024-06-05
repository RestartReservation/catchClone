package com.example.catchclone.store.service;

import com.example.catchclone.common.dto.StatusResponseDto;
import com.example.catchclone.store.dto.StoreCategoryDto;
import com.example.catchclone.store.dto.StoreDetailsResponseDto;
import com.example.catchclone.store.dto.StoreIndexResponseDto;
import com.example.catchclone.store.dto.StoreMenuDto;
import com.example.catchclone.store.dto.StorePageDto;
import com.example.catchclone.store.dto.StoreRequestDto;
import com.example.catchclone.store.entity.Store;
import com.example.catchclone.user.entity.User;
import java.util.List;
import org.springframework.data.domain.Page;

public interface StoreService {


  StatusResponseDto addStore(StoreRequestDto storeRequestDto, User user);

  StatusResponseDto addMenu(User user,Long storeId,List<StoreMenuDto> storeMenuDtoList);

  StatusResponseDto addCategory(Long storeId, StoreCategoryDto storeCategoryDto,User user);
  Page<StoreIndexResponseDto> getStores(StorePageDto storePageDto);
  StoreDetailsResponseDto getStoreDetails(Long storeId);

  Store findStoreByStoreId(Long storeId);
}
