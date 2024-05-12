package com.example.catchclone.bookmark.entity;

import com.example.catchclone.bookmark.dto.BookmarkRequestDto;
import com.example.catchclone.store.entity.Store;
import com.example.catchclone.user.entity.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Bookmark {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String memo;

  @JoinColumn(name = "USER_ID")
  @ManyToOne(cascade = CascadeType.REMOVE)
  private User user;

  @JoinColumn(name = "STORE_ID")
  @ManyToOne(cascade = CascadeType.REMOVE)
  private Store store;

  @Builder
  public Bookmark(BookmarkRequestDto bookmarkRequestDto,User user,Store store){

    this.memo = bookmarkRequestDto.getMemo();
    this.user = user;
    this.store = store;
  }




}
