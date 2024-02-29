package com.example.catchclone.store.controller;

import com.example.catchclone.common.dto.StatusResponseDto;
import com.example.catchclone.security.UserDetailsImpl;
import com.example.catchclone.store.dto.StoreRequestDto;
import com.example.catchclone.store.service.StoreService;
import com.example.catchclone.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
  public ResponseEntity<StatusResponseDto> addStore(@RequestBody StoreRequestDto storeRequestDto,@AuthenticationPrincipal
  UserDetailsImpl userDetails){

    return ResponseEntity.ok().body(storeService.addStore(storeRequestDto,userDetails.getUser()));
  }
}
