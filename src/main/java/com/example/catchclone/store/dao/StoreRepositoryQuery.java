package com.example.catchclone.store.dao;

import com.example.catchclone.store.dto.StoreDetailsResponseDto;
import com.example.catchclone.store.dto.StoreIndexResponseDto;
import com.example.catchclone.store.dto.StorePageDto;
import org.springframework.data.domain.Page;

public interface StoreRepositoryQuery {
  Page<StoreIndexResponseDto> getStores(StorePageDto storePageDto);
  StoreDetailsResponseDto getStoreDetails(Long storeId);
}
