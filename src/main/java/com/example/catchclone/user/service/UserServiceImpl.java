package com.example.catchclone.user.service;

import com.example.catchclone.jwt.JwtUtil;
import com.example.catchclone.security.JwtAuthFilter;
import com.example.catchclone.security.dto.StatusResponseDto;
import com.example.catchclone.user.dto.UserLoginRequestDto;
import com.example.catchclone.user.dto.UserRequestDto;
import com.example.catchclone.user.entity.User;
import com.example.catchclone.user.repository.UserRepository;
import com.example.catchclone.util.enums.UserRoleEnum;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import java.net.http.HttpRequest;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  private final JwtUtil jwtUtil;

  @Override
  public StatusResponseDto signUp(UserRequestDto userRequestDto) {

    if(userRepository.existsByUsername(userRequestDto.username())) return new StatusResponseDto(400,"Already exist!");

    String encodedPwd = passwordEncoder.encode(userRequestDto.password());

    User user = User.builder()
        .username(userRequestDto.username())
        .password(encodedPwd)
        .phoneNumber(userRequestDto.phoneNumber())
        .aboutMe(userRequestDto.aboutMe())
        .nickName(userRequestDto.nickName())
        .profileUrl(userRequestDto.profileUrl())
        .role(UserRoleEnum.CUSTOMER)
        .build();

    userRepository.save(user);


    return new StatusResponseDto(201,"created!");
  }

  @Override
  public StatusResponseDto login(HttpServletResponse httpServletResponse,UserLoginRequestDto userLoginRequestDto) {

    Optional<User> user = userRepository.findByUsername(userLoginRequestDto.getUsername());

    if(user.isEmpty()) return new StatusResponseDto(401,"user is Not exist!");

    if(!passwordEncoder.matches(userLoginRequestDto.getPassword(),user.get().getPassword())) return new StatusResponseDto(401,"password is not matches!");

    String accessToken = jwtUtil.createAccessToken(user.get().getUsername(),user.get().getRole());
    //httpServletResponse.addCookie(accessToken);
    httpServletResponse.addHeader(JwtUtil.AUTHORIZATION_HEADER, accessToken);


    return new StatusResponseDto(201,"success!");
  }
}
