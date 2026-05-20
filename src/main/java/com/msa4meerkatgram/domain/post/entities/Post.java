package com.msa4meerkatgram.domain.post.entities;

import lombok.Builder;
import lombok.Getter;

// ----------------------------------
// -- 레코드 하나의 정보를 저장할 엔티티 객체
// ----------------------------------

@Getter
@Builder
public class Post {
    private Long id;
    private Long userId;
    private String content;
    private String image;
    private String createdAt;
    private String updatedAt;
    private String deletedAt;
}