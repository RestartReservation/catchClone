package com.example.catchclone.store.service;

import com.example.catchclone.common.dto.StatusResponseDto;
import com.example.catchclone.store.dao.StoreMenuRepository;
import com.example.catchclone.store.dto.StoreMenuAddDto;
import com.example.catchclone.store.dto.StoreMenuDto;
import com.example.catchclone.store.entity.Store;
import com.example.catchclone.store.entity.StoreMenu;
import com.example.catchclone.user.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class StoreMenuServiceImpl implements StoreMenuService{
  private final StoreMenuRepository storeMenuRepository;
  private final StoreService storeService;
  @Override
  @Transactional
  public StatusResponseDto addStoreMenu(User user,Long storeId, List<StoreMenuAddDto> storeMenuAddDtoList) {

    Store store = storeService.findStoreByStoreId(storeId);


    if(!store.getUser().getId().equals(user.getId())){
      throw new IllegalArgumentException("해당 가맹 점주가 아닙니다!");
    }


    storeMenuAddDtoList.stream()
        .map(st -> new StoreMenu(store, st))
        .forEach(storeMenuRepository::save);

    return new StatusResponseDto(201,"메뉴 등록이 완료되었습니다!");
  }

  @Override
  public List<StoreMenuDto> getStoreMenu(Long storeId) {
    Store store = storeService.findStoreByStoreId(storeId);
    List<StoreMenu> menuList = storeMenuRepository.findAllByStore(store);
    return menuList.stream().map(StoreMenuDto::from).toList();
  }

}
