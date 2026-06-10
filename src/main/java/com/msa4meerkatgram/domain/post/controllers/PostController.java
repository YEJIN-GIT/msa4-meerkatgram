package com.msa4meerkatgram.domain.post.controllers;

import com.msa4meerkatgram.domain.post.entities.Post;
import com.msa4meerkatgram.domain.post.requests.PostStoreReq;
import com.msa4meerkatgram.domain.post.requests.PostIndexReq;
import com.msa4meerkatgram.domain.post.responses.PostIndexRes;
import com.msa4meerkatgram.domain.post.services.PostService;
import com.msa4meerkatgram.global.Response.GlobalRes;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class PostController {
    private final PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<GlobalRes<PostIndexRes>> index(PostIndexReq postIndexReq) {
        PostIndexRes postIndexRes = postService.index(postIndexReq);

        // return String.format("page: %d, limit: %d", req.page(), req.limit());
        return ResponseEntity.status(200).body(
                GlobalRes.<PostIndexRes>builder()
                        .code("00")
                        .message("정상처리")
                        .data(postIndexRes)
                        .build()
        );
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<GlobalRes<Post>> show(
            @Min(value = 1, message = "1이상 숫자만 허용합니다.") @PathVariable Long id     // /posts/{id} 에서 세크먼트파라메터명{id}과 같은 것으로 id 해야 함.
    ) {
        // 스프링부트에서 ResponseEntity로 레스폰스객체를 받는다.

        Post result = postService.show(id);

            return ResponseEntity.<Post>status(200).body(
                    GlobalRes.<Post>builder()
                    .code("00")
                    .message("게시글 상세 정상 처리 ")
                    .data(result)
                    .build()
            );
    }

    @PostMapping("/posts")
    public ResponseEntity<GlobalRes<Post>> store(
            @Valid @RequestBody PostStoreReq postStoreReq
            ) {
        Post result = postService.store(postStoreReq);

        return ResponseEntity.status(200).body(
                GlobalRes.<Post>builder()
                        .code("00")
                        .message("게시글 등록 정상 처리")
                        .data(result)
                        .build()
        );
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<GlobalRes<String>> deletePosts(
            @Min(value = 1, message = "1이상 숫자만 허용합니다.") @PathVariable Long id
    ) {
        postService.deletePosts(id);

        return ResponseEntity.<String>status(200).body(
                GlobalRes.<String>builder()
                        .code("00")
                        .message("게시글 삭제 정상 처리 ")
                        .data("")
                        .build()
        );
    }
}