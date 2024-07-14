package com.example.catchclone.follow.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Follow {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "follow_id", nullable = false)
  private Long id;

  @Column
  private Long ownerId;

  @Column
  private Long followerId;

  public Follow(Long ownerId,Long followerId){
    this.ownerId = ownerId;
    this.followerId = followerId;
  }


}
