package com.example.catchclone.user.entity;

import com.example.catchclone.like.entity.commentLike.CommentLike;
import com.example.catchclone.like.entity.reviewLike.ReviewLike;
import com.example.catchclone.util.TimeStamped;
import com.example.catchclone.util.enums.UserRoleEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name="users")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User extends TimeStamped {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 25, nullable = false, unique = true)
  private String username;

  @Column
  @Enumerated(EnumType.STRING)
  private UserRoleEnum role;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String nickName;

  @Column
  private String phoneNumber;

  @Column
  private String aboutMe;

  @Column
  private String profileUrl;

  @Builder
  public User(String username, String password, String nickName, String phoneNumber, String aboutMe, String profileUrl, UserRoleEnum role){
    this.username = username;
    this.password = password;
    this.nickName = nickName;
    this.phoneNumber = phoneNumber;
    this.aboutMe = aboutMe;
    this.profileUrl = profileUrl;
    this.role = role;
  }

  //연관관계
  @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
  private Set<ReviewLike> reviewLikes = new HashSet<>();

  @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
  private Set<CommentLike> commentLikes = new HashSet<>();
}
