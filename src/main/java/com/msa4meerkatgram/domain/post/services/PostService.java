package com.msa4meerkatgram.domain.post.services;

import com.msa4meerkatgram.domain.post.entities.Post;
import com.msa4meerkatgram.domain.post.mapper.PostMapper;
import com.msa4meerkatgram.domain.post.requests.PostIndexReq;
import com.msa4meerkatgram.domain.post.responses.PostIndexRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostMapper postMapper;

    public PostIndexRes index(PostIndexReq postIndexReq) {
        // 특정 페이지의 게시글 조회
        int offet = (postIndexReq.page() - 1 ) * postIndexReq.limit();

        List<Post> posts = postMapper.getPagination(postIndexReq.limit(), offet);

        // 토탈 획득 (부하발생)
        long total = postMapper.getTotal();
        boolean lastPage = offet + postIndexReq.limit() >= total;

        // 컨트롤러 전달
        return PostIndexRes.builder()
                .total(total)
                .lastPage(lastPage)
                .posts(posts)
                .build();

    }
}