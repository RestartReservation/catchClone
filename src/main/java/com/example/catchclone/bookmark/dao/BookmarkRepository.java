package com.example.catchclone.bookmark.dao;

import com.example.catchclone.bookmark.entity.Bookmark;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface BookmarkRepository extends Repository<Bookmark,Long> {

  void save(Bookmark bookmark);

  Optional<Bookmark> findBookmarkById(Long bookmarkId);

  void deleteBookmarkById(Long bookmarkId);
}
