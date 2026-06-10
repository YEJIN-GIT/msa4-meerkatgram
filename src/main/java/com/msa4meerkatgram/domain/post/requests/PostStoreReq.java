package com.msa4meerkatgram.domain.post.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostStoreReq(
    @NotNull(message = "사용자 아이디는 필수 항목입니다.")
    Long userId,

    @NotBlank(message = "내용은 필수 항목입니다.")
    String content,

    @NotBlank(message = "이미지는 필수 항목입니다.")
    String image
) {
}