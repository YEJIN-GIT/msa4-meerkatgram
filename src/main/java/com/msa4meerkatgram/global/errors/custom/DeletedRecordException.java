package com.msa4meerkatgram.global.errors.custom;

// 커스텀 exception 처리
// 우리가 생각했을 때 중간에 문제시 롤백 처리 하기 위함
// 보통 RuntimeException 을 상속받는다.

public class DeletedRecordException extends RuntimeException {
    public DeletedRecordException(String message) {
        super(message); // 부모 클래스의 생성자를 호출
    }
}
