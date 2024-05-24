package com.example.catchclone.user.service;

import com.example.catchclone.common.dto.StatusResponseDto;
import com.example.catchclone.user.dto.UserLoginRequestDto;
import com.example.catchclone.user.dto.UserProfileResponseDto;
import com.example.catchclone.user.dto.UserRequestDto;
import com.example.catchclone.user.entity.User;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {

  StatusResponseDto signUp(UserRequestDto userRequestDto); //회원가입

  StatusResponseDto login(HttpServletResponse httpServletResponse,UserLoginRequestDto userLoginRequestDto);

  User findUserByUserId(Long userId);

 UserProfileResponseDto getUserProfile(Long userId);

}
