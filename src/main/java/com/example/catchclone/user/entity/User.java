package com.example.catchclone.user.entity;

import com.example.catchclone.util.TimeStamped;
import com.example.catchclone.util.enums.UserRoleEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
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
  private LocalDateTime createdAt;

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

}
