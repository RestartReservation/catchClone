package com.example.catchclone.store.service;

import com.example.catchclone.common.dto.StatusResponseDto;
import com.example.catchclone.store.dto.StoreMenuAddDto;
import com.example.catchclone.store.dto.StoreMenuDto;
import com.example.catchclone.user.entity.User;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface StoreMenuService {
  StatusResponseDto addStoreMenu(
      User user,Long storeId, List<StoreMenuAddDto> storeMenuAddDtoList);
  List<StoreMenuDto> getStoreMenu(Long storeId);
}
