package com.example.catchclone.globalException;



import com.example.catchclone.common.dto.StatusResponseDto;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionController {
  @ExceptionHandler(IllegalArgumentException.class)
  private ResponseEntity<StatusResponseDto> illegalArgumentExceptionHandler(
      IllegalArgumentException e) {
    StatusResponseDto statusResponse = new StatusResponseDto(HttpStatus.BAD_REQUEST.value(),
        e.getMessage());
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    return ResponseEntity.badRequest().headers(httpHeaders)
        .body(statusResponse);
  }

  @ExceptionHandler(ConstraintViolationException.class) //유효성 검사 실패 exception
  private ResponseEntity<StatusResponseDto> ConstraintViolationExceptionHandler(
      ConstraintViolationException e) {
    String message = e.getConstraintViolations()
        .stream()
        .map(ConstraintViolation::getMessage)
        .toList()
        .get(0);
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    StatusResponseDto statusResponseDto = new StatusResponseDto(HttpStatus.BAD_REQUEST.value(), message);
    return new ResponseEntity<>(statusResponseDto, httpHeaders,
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(AccessDeniedException.class)
  private ResponseEntity<StatusResponseDto> AccessDeniedExceptionHandler(AccessDeniedException e) {
    StatusResponseDto statusResponse = new StatusResponseDto(HttpStatus.FORBIDDEN.value(),
        e.getMessage());
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    return new ResponseEntity<>(statusResponse, httpHeaders,
        HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(JwtException.class)
  private ResponseEntity<StatusResponseDto> JwtExceptionHandler(JwtException e) {
    StatusResponseDto statusResponse = new StatusResponseDto(HttpStatus.UNAUTHORIZED.value(),
        e.getMessage());
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(statusResponse);
  }

  @ExceptionHandler(SecurityException.class)
  private ResponseEntity<StatusResponseDto> SecurityExceptionHandler(SecurityException e) {
    StatusResponseDto statusResponse = new StatusResponseDto(HttpStatus.UNAUTHORIZED.value(),
        e.getMessage());
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(statusResponse);
  }

  @ExceptionHandler(MalformedJwtException.class)
  private ResponseEntity<StatusResponseDto> MalformedJwtExceptionHandler(MalformedJwtException e) {
    StatusResponseDto statusResponse = new StatusResponseDto(HttpStatus.UNAUTHORIZED.value(),
        e.getMessage());
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(statusResponse);
  }

  @ExceptionHandler(SignatureException.class)
  private ResponseEntity<StatusResponseDto> SignatureExceptionHandler(SignatureException e) {
    StatusResponseDto statusResponse = new StatusResponseDto(HttpStatus.UNAUTHORIZED.value(),
        e.getMessage());;
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(statusResponse);
  }
}
