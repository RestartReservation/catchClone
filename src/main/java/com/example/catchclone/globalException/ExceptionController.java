package com.example.catchclone.globalException;



import com.example.catchclone.common.dto.StatusResponseDto;
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
}
