package com.example.catchclone.store.controller;

import com.example.catchclone.common.dto.StatusResponseDto;
import com.example.catchclone.security.UserDetailsImpl;
import com.example.catchclone.store.dto.StoreCategoryDto;
import com.example.catchclone.store.dto.StoreMenuDto;
import com.example.catchclone.store.dto.StoreRequestDto;
import com.example.catchclone.store.service.StoreService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ct/shop")
public class StoreController {


  private final StoreService storeService;

  @PostMapping("/addShopInfo")
  public ResponseEntity<StatusResponseDto> addStore(@RequestBody StoreRequestDto storeRequestDto,
      @AuthenticationPrincipal
      UserDetailsImpl userDetails) {

    return ResponseEntity.ok().body(storeService.addStore(storeRequestDto, userDetails.getUser()));
  }


  @PostMapping("/addShopMenu/{storeId}")
  public ResponseEntity<StatusResponseDto> addMenu(@PathVariable Long storeId,
      @RequestBody List<StoreMenuDto> storeMenuDtoList,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    return ResponseEntity.ok()
        .body(storeService.addMenu(userDetails.getUser(), storeId, storeMenuDtoList));
  }

  @PostMapping("/addCategory/{storeId}")
  public ResponseEntity<StatusResponseDto> addCategory(@PathVariable Long storeId,@RequestBody
      StoreCategoryDto storeCategoryDto,@AuthenticationPrincipal UserDetailsImpl userDetails){
    return ResponseEntity.ok()
        .body(storeService.addCategory(storeId,storeCategoryDto,userDetails.getUser()));
  }
}