package com.msa4meerkatgram.global.errors;

// -------------------------
// 커스텀한 예외처리 위한 객체
// -------------------------

import com.msa4meerkatgram.global.Response.GlobalRes;
import com.msa4meerkatgram.global.errors.custom.NotRegisteredException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotRegisteredException.class)
    public ResponseEntity<GlobalRes<String>> notRegisteredHandle(NotRegisteredException e) {
        return ResponseEntity.status(400).body(
                GlobalRes.<String>builder()
                        .code("E01")
                        .message("로그인 에러")
                        .data(e.getMessage())
                        .build()
        );
    }

    // MethodArgumentTypeMismatchException : 하나의 데이터를 클라이언트로 부터 받을 때 오류
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<GlobalRes<String>> methodArgumentTypeMismatchHandle(MethodArgumentTypeMismatchException e) {
        return ResponseEntity.status(400).body(
                GlobalRes.<String>builder()
                        .code("E21")
                        .message("요청 파라미터에 이상이 있습니다.")
                        .data(String.format("%s : 필드를 확인해 주세요.", e.getName()))
                        .build()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalRes<List<String>>> methodArgumentNotValidHandle(MethodArgumentNotValidException e) {
        return ResponseEntity.status(400).body(
                GlobalRes.<List<String>>builder()
                        .code("E21")
                        .message("요청 파라미터에 이상이 있습니다.")
                        .data(e.getBindingResult()
                                .getAllErrors()
                                // .stream().map(ObjectError::getDefaultMessage)
                                .stream().map(item -> String.format("%s :잘못된 값입니다.", item.getObjectName()))
                                .toList()
                        )
                        .build()
        );
    }

    // Exception : 예기치 못한 모든 에러
    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalRes<String>> otherHandle(Exception e) {
        log.error(String.format("시스템 에러: %s\n%s", e.getMessage(), Arrays.toString(e.getStackTrace())));

        return ResponseEntity.status(500).body(
                GlobalRes.<String>builder()
                        .code("E99")
                        .message("시스템 에러")
                        .data("현재 서비스 이용이 불가합니다. 잠시후 다시 시도해 주십시오.")
                        .build()
        );
    }
}
