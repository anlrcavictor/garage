package com.anilyetisgin.garage.controller.handler;

import com.anilyetisgin.garage.base.constants.Constants;
import com.anilyetisgin.garage.base.dto.ErrorResponseDto;
import com.anilyetisgin.garage.base.exception.InvalidActionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class GlobalHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponseDto> handleAllExceptions(Exception e) {
        log.error("Unpredicted Exception {} ", e.getMessage());
        return new ResponseEntity<>(ErrorResponseDto.builder().date(LocalDateTime.now()).message(Constants.UNPREDICTED_EX_MESSAGE).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidActionException.class)
    public final ResponseEntity<Object> handleInvalidTypeException(InvalidActionException e) {
        log.error("Invalid Action --> {} ", e.getMessage());
        return new ResponseEntity<>(ErrorResponseDto.builder().date(LocalDateTime.now()).message(e.getLocalizedMessage()).build(), HttpStatus.BAD_REQUEST);
    }

}
