package com.msa4meerkatgram.domain.post.controllers;

import com.msa4meerkatgram.domain.post.requests.PostIndexReq;
import com.msa4meerkatgram.domain.post.responses.PostIndexRes;
import com.msa4meerkatgram.domain.post.responses.PostWithUserRes;
import com.msa4meerkatgram.domain.post.services.PostService;
import com.msa4meerkatgram.global.Response.GlobalRes;
import com.msa4meerkatgram.global.Response.constant.CustomResponseCode;
import com.msa4meerkatgram.global.config.openapi.CustomApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "게시글 API", description = "게시글 관련")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class PostController {
    private final PostService postService;

    @Operation(summary = "게시글 목록 조회 처리")
    @CustomApiResponse(value = {
            CustomResponseCode.INVALID_PARAMETER_ERROR
            ,CustomResponseCode.DB_ERROR
            ,CustomResponseCode.SYSTEM_ERROR
    })
    @GetMapping("/posts")
    public ResponseEntity<GlobalRes<PostIndexRes>> index(PostIndexReq postIndexReq) {
        // return String.format("page: %d, limit: %d", req.page(), req.limit());
        return ResponseEntity.ok(GlobalRes.success(postService.index(postIndexReq)));
    }

    @Operation(summary = "게시글 상세 조회 처리")
    @CustomApiResponse(value = {
            CustomResponseCode.INVALID_PARAMETER_ERROR
            ,CustomResponseCode.UNAUTHENTICATED_ERROR
            ,CustomResponseCode.INVALID_TOKEN_ERROR
            ,CustomResponseCode.NOT_FOUND_DATA_ERROR
            ,CustomResponseCode.DB_ERROR
            ,CustomResponseCode.SYSTEM_ERROR
    })
    @GetMapping("/posts/{id}")
    public ResponseEntity<GlobalRes<PostWithUserRes>> show(
            @Parameter(description = "게시글 번호", example = "1") @Min(value = 1, message = "1이상 숫자만 허용합니다.") @PathVariable Long id     // /posts/{id} 에서 세크먼트파라메터명{id}과 같은 것으로 id 해야 함.
    ) {
        return ResponseEntity.ok(GlobalRes.success(postService.show(id)));
    }
}