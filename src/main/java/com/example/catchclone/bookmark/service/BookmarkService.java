package com.example.catchclone.bookmark.service;

import com.example.catchclone.bookmark.dto.BookmarkRequestDto;
import com.example.catchclone.common.dto.StatusResponseDto;
import com.example.catchclone.user.entity.User;

public interface BookmarkService {

  StatusResponseDto addBookmark(Long storeId, BookmarkRequestDto bookmarkRequestDto, User user);

  StatusResponseDto deleteBookmark(Long storeId,User user);



}
