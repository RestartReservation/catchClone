package com.example.catchclone.store.service;

import com.example.catchclone.common.dto.StatusResponseDto;
import com.example.catchclone.store.dto.StoreCategoryDto;
import com.example.catchclone.store.dto.StoreDetailsResponseDto;
import com.example.catchclone.store.dto.StoreIndexResponseDto;
import com.example.catchclone.store.dto.StoreMenuDto;
import com.example.catchclone.store.dto.StorePageDto;
import com.example.catchclone.store.dto.StoreRequestDto;
import com.example.catchclone.store.entity.Store;
import com.example.catchclone.store.entity.StoreCategory;
import com.example.catchclone.store.entity.StoreMenu;
import com.example.catchclone.store.dao.StoreCategoryRepository;
import com.example.catchclone.store.dao.StoreMenuRepository;
import com.example.catchclone.store.dao.StoreRepository;
import com.example.catchclone.user.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class StoreServiceImpl implements StoreService{

  private final StoreRepository storeRepository;
  private final StoreMenuRepository storeMenuRepository;

  private final StoreCategoryRepository storeCategoryRepository;



  @Transactional
  public Store findStoreById(Long storeId){

    return storeRepository.findById(storeId).orElseThrow(
        ()-> new IllegalArgumentException("존재하지 않는 가게입니다.")
    );
  }

  @Override
  @Transactional
  public StatusResponseDto addMenu(User user,Long storeId,List<StoreMenuDto> storeMenuDtoList) {

    Store store = findStoreById(storeId);


    if(!store.getUser().getId().equals(user.getId())){
      throw new IllegalArgumentException("해당 가맹 점주가 아닙니다!");
    }


    storeMenuDtoList.stream()
        .map(st -> new StoreMenu(store, st))
        .forEach(storeMenuRepository::save);

    return new StatusResponseDto(201,"메뉴 등록이 완료되었습니다!");
  }

  @Override
  @Transactional
  public StatusResponseDto addStore(StoreRequestDto storeRequestDto, User user) {

    if(storeRepository.findByStoreName(storeRequestDto.getStoreName()).isPresent())
    {
      return new StatusResponseDto(409,"이미 존재하는 가게입니다!");
    }

    storeRepository.save(new Store(storeRequestDto,user));

    return  new StatusResponseDto(201,"가게 정보등록이 완료되었습니다!");
  }

  @Override
  @Transactional
  public StatusResponseDto addCategory(Long storeId, StoreCategoryDto storeCategoryDto, User user) {

    Store store = findStoreById(storeId);

    if(!store.getUser().getId().equals(user.getId()))
      throw new IllegalArgumentException("해당 가맹 점주만 카테고리 등록이 가능합니다!");



    StoreCategory storeCategory = StoreCategory.builder()
        .store(store)
        .storeCategoryDto(storeCategoryDto)
        .build();

    storeCategoryRepository.save(storeCategory);
    return new StatusResponseDto(201,"카테코리 정보 등록이 완료되었습니다!");
  }

  @Override
  @Transactional(readOnly = true)
  public Page<StoreIndexResponseDto> getStores(StorePageDto storePageDto) {
    return storeRepository.getStores(storePageDto);
  }

  @Override
  @Transactional(readOnly = true)
  public StoreDetailsResponseDto getStoreDetails(Long storeId) {
    return storeRepository.getStoreDetails(storeId);
  }

  @Override
  @Transactional(readOnly = true)
  public Store findStoreByStoreId(Long storeId) {
    return storeRepository.findById(storeId).orElseThrow(
        () -> new IllegalArgumentException("일치하는 정보가 없습니다")
    );
  }


}
