package com.example.catchclone.user.controller;

import com.example.catchclone.common.dto.StatusResponseDto;
import com.example.catchclone.user.dto.UserLoginRequestDto;
import com.example.catchclone.user.dto.UserRequestDto;
import com.example.catchclone.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ct/users")
public class UserController {

  private final UserService userService;

  @PostMapping("/signUp")
  public StatusResponseDto signUp(@RequestBody UserRequestDto userRequestDto){

   return userService.signUp(userRequestDto);
  }

  @PostMapping("/login")
  public StatusResponseDto login(HttpServletResponse httpServletResponse,@RequestBody UserLoginRequestDto userLoginRequestDto){

   return userService.login(httpServletResponse,userLoginRequestDto);
  }

}
