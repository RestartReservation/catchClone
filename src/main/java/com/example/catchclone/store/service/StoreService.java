package com.example.catchclone.store.service;

import com.example.catchclone.common.dto.StatusResponseDto;
import com.example.catchclone.store.dto.StoreRequestDto;
import com.example.catchclone.user.entity.User;
import org.springframework.http.ResponseEntity;

public interface StoreService {


  StatusResponseDto addStore(StoreRequestDto storeRequestDto, User user);
}
