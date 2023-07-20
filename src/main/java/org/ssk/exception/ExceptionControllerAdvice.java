package org.ssk.exception;

import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.ssk.dto.ErrorResponseDto;

/**
 * title        : ControllerAdvice
 * author       : sim
 * date         : 2023-07-19
 * description  : 컨트롤러 레이어에서 발생한 예외 핸들러 클래스
 */

@RestControllerAdvice
public class ExceptionControllerAdvice {
    private static final String UNKNOWN_ERROR_MESSAGE = "시스템에서 알 수 없는 에러가 발생하였습니다. 관리자에게 문의해주세요.";

    @ExceptionHandler(DuplicationNicknameException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDto NotFoundExceptionHandler(DuplicationNicknameException e){
        e.printStackTrace();
        return new ErrorResponseDto(e.getMessage());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        return new ErrorResponseDto(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDto ExceptionHandler(Exception e){
        e.printStackTrace();
        return new ErrorResponseDto(UNKNOWN_ERROR_MESSAGE);
    }
}
