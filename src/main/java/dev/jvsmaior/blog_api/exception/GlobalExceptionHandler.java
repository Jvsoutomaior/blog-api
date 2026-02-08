package dev.jvsmaior.blog_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Consider using ProblemDetail https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-ann-rest-exceptions.html, https://www.baeldung.com/spring-boot-return-errors-problemdetail
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
        Map<String, Object> errorMap = new HashMap<>();

        errorMap.put("Timestamp", LocalDateTime.now());
        errorMap.put("Http Status", HttpStatus.NOT_FOUND);
        errorMap.put("Message", resourceNotFoundException.getMessage());

        return new ResponseEntity<>(errorMap, HttpStatus.NOT_FOUND);
    }
}
