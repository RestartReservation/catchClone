package com.example.catchclone.bookmark.service;

import com.example.catchclone.bookmark.dao.BookmarkRepository;
import com.example.catchclone.bookmark.dto.BookmarkRequestDto;
import com.example.catchclone.bookmark.entity.Bookmark;
import com.example.catchclone.common.dto.StatusResponseDto;
import com.example.catchclone.store.entity.Store;
import com.example.catchclone.store.service.StoreService;
import com.example.catchclone.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BookmarkServiceImpl implements BookmarkService{

  private final BookmarkRepository bookmarkRepository;
  private final StoreService storeService;

  @Override
  @Transactional
  public StatusResponseDto addBookmark(Long storeId, BookmarkRequestDto bookmarkRequestDto,
      User user) {

    Store store = storeService.findStoreByStoreId(storeId);

    Bookmark bookmark = Bookmark.builder()
        .bookmarkRequestDto(bookmarkRequestDto)
        .user(user)
        .store(store)
        .build();

    bookmarkRepository.save(bookmark);
    return new StatusResponseDto(201,"북마크 등록에 성공했습니다!");
  }

  @Override
  public StatusResponseDto deleteBookmark(Long bookmarkId, User user) {

    bookmarkRepository.findBookmarkById(bookmarkId).orElseThrow(
        ()-> new IllegalArgumentException("해당 북마크를 찾을 수 없습니다!")
    );

    bookmarkRepository.deleteBookmarkById(bookmarkId);

    return new StatusResponseDto(200,"북마크 삭제 완료!");
  }
}
