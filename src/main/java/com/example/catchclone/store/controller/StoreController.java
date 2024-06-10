package com.example.catchclone.store.controller;

import com.example.catchclone.common.dto.StatusResponseDto;
import com.example.catchclone.security.UserDetailsImpl;
import com.example.catchclone.store.dto.StoreCategoryDto;
import com.example.catchclone.store.dto.StoreDetailsResponseDto;
import com.example.catchclone.store.dto.StoreIndexResponseDto;
import com.example.catchclone.store.dto.StoreMenuDto;
import com.example.catchclone.store.dto.StorePageDto;
import com.example.catchclone.store.dto.StoreRequestDto;
import com.example.catchclone.store.service.StoreService;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ct/stores")
public class StoreController {


  private final StoreService storeService;

  @PostMapping
  public ResponseEntity<StatusResponseDto> addStore(@RequestBody StoreRequestDto storeRequestDto,
      @AuthenticationPrincipal
      UserDetailsImpl userDetails) {
    return ResponseEntity.ok().body(storeService.addStore(storeRequestDto, userDetails.getUser()));
  }


  @PostMapping("/categories/{storeId}")
  public ResponseEntity<StatusResponseDto> addCategory(@PathVariable Long storeId,@RequestBody
      StoreCategoryDto storeCategoryDto,@AuthenticationPrincipal UserDetailsImpl userDetails){
    return ResponseEntity.ok()
        .body(storeService.addCategory(storeId,storeCategoryDto,userDetails.getUser()));
  }
  @GetMapping
  public ResponseEntity<Page<StoreIndexResponseDto>> getStores(StorePageDto storePageDto){
    Page<StoreIndexResponseDto> storeList = storeService.getStores(storePageDto);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    return ResponseEntity.ok().headers(headers).body(storeList);
  }

  @GetMapping("/{storeId}")
  public ResponseEntity<StoreDetailsResponseDto> getStore(@PathVariable Long storeId){
    StoreDetailsResponseDto storeDetails = storeService.getStoreDetails(storeId);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    return ResponseEntity.ok().headers(headers).body(storeDetails);
  }

}
