package com.example.catchclone.store.controller;

import com.example.catchclone.common.dto.StatusResponseDto;
import com.example.catchclone.security.UserDetailsImpl;
import com.example.catchclone.store.dto.StoreMenuAddDto;
import com.example.catchclone.store.dto.StoreMenuDto;
import com.example.catchclone.store.dto.StoreRequestDto;
import com.example.catchclone.store.service.StoreMenuService;
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
@RequestMapping("/ct/stores/menu")
public class StoreMenuController {

  private final StoreMenuService storeMenuService;

  @PostMapping("/{storeId}")
  public ResponseEntity<StatusResponseDto> addMenu(@PathVariable Long storeId,
      @RequestBody List<StoreMenuAddDto> storeMenuAddDtoList,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    return ResponseEntity.ok()
        .body(storeMenuService.addStoreMenu(userDetails.getUser(), storeId, storeMenuAddDtoList));
  }
}
