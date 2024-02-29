package com.example.catchclone.store.service;

import com.example.catchclone.common.dto.StatusResponseDto;
import com.example.catchclone.store.dto.StoreRequestDto;
import com.example.catchclone.store.entity.Store;
import com.example.catchclone.store.repository.StoreRepository;
import com.example.catchclone.user.entity.User;
import java.util.logging.SocketHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StoreServiceImpl implements StoreService{

  private final StoreRepository storeRepository;

  @Override
  public StatusResponseDto addStore(StoreRequestDto storeRequestDto, User user) {

    if(storeRepository.findByStoreName(storeRequestDto.getStoreName()).isPresent())
    {
      return new StatusResponseDto(409,"이미 존재하는 가게입니다!");
    }

    storeRepository.save(new Store(storeRequestDto,user));

    return  new StatusResponseDto(201,"가게 정보등록이 완료되었습니다!");
  }
}
