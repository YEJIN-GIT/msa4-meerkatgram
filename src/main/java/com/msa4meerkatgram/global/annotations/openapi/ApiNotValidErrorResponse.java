package com.msa4meerkatgram.global.annotations.openapi;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// @interface: 어노테이션이다.
// 커스텀 어노테이션
@Target(ElementType.METHOD) // 어느 레벨에서 동작할 어노테이션인지
@Retention(RetentionPolicy.RUNTIME) // 어노테이션이 실행되고 있을 때 만든다
@ApiResponse(
        responseCode = "400"
        , description = "유효성 검사 실패"
        , content = @Content(
            mediaType = "application/json"
            , examples = {
                @ExampleObject(
                        name = "유효성 검사 실패 에러"
                        , value = """
                                    {
                                        "code": "E21"
                                        ,"message": "Bad Request"
                                    }
                                """
                )
            }
        )
)
public @interface ApiNotValidErrorResponse {
}
