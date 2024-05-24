package com.example.catchclone.user.controller;

import com.example.catchclone.common.dto.StatusResponseDto;
import com.example.catchclone.security.UserDetailsImpl;
import com.example.catchclone.user.dto.UserLoginRequestDto;
import com.example.catchclone.user.dto.UserProfileResponseDto;
import com.example.catchclone.user.dto.UserRequestDto;
import com.example.catchclone.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
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

  @GetMapping("/profile")
  public ResponseEntity<UserProfileResponseDto> getUserProfile(@AuthenticationPrincipal UserDetailsImpl userDetails){
    UserProfileResponseDto userProfileResponseDto = userService.getUserProfile(userDetails.getUserId());
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    return ResponseEntity.ok().headers(headers).body(userProfileResponseDto);
  }

}
