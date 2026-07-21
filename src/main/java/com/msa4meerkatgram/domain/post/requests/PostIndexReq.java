package com.msa4meerkatgram.domain.post.requests;

// -----------------------------
// Spring Boot에서 request DTO 역할 : 유저가 보내용 데이터를 여기저기 전달. 해당 파라메터들의 유효성 검사
// -----------------------------

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;

public record PostIndexReq(
        @Schema(description = "페이지 번호", example = "1", nullable = false, requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @Min(value = 1, message = "1이상 숫자만 허용합니다.")
        Integer page,
        @Schema(description = "게시글 제한수", example = "6", nullable = false, requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @Min(value = 1, message = "1이상 숫자만 허용합니다.")
        Integer limit
) {
    // 초기값 변경 가능
    public PostIndexReq(Integer page, Integer limit) {
        this.page = (page != null && page > 0) ? page : 1;
        this.limit = (limit != null && limit > 0) ? limit : 6;
    }
}