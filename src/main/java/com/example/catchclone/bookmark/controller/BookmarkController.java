package com.example.catchclone.bookmark.controller;

import com.example.catchclone.bookmark.dto.BookmarkRequestDto;
import com.example.catchclone.bookmark.service.BookmarkService;
import com.example.catchclone.common.dto.StatusResponseDto;
import com.example.catchclone.security.UserDetailsImpl;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ct/reservations/bookmarks")
public class BookmarkController {

  private final BookmarkService bookmarkService;


  @PostMapping("/{storeId}")
  public ResponseEntity<StatusResponseDto> addBookmark(@PathVariable Long storeId,@RequestBody BookmarkRequestDto bookmarkRequestDto,@AuthenticationPrincipal
      UserDetailsImpl userDetails){

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    return ResponseEntity.ok().headers(headers).body(bookmarkService.addBookmark(storeId,bookmarkRequestDto,userDetails.getUser()));

  }

  @DeleteMapping("/{bookmarkId}")
  public ResponseEntity<StatusResponseDto> deleteBookmark(@PathVariable Long bookmarkId,@AuthenticationPrincipal
  UserDetailsImpl userDetails){

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    return ResponseEntity.ok().headers(headers).body(bookmarkService.deleteBookmark(bookmarkId,userDetails.getUser()));

  }

}
