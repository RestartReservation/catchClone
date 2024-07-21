package com.example.catchclone.follow.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FollowResponseDto {

  private Long followerId;
  private String nickName;

  private String profileUrl;


  public FollowResponseDto(Long followerId,String nickName,String profileUrl){
    this.followerId = followerId;
    this.nickName = nickName;
    this.profileUrl = profileUrl;
  }


}
