package com.msa4meerkatgram.domain.post.services;

import com.msa4meerkatgram.domain.post.entities.Post;
import com.msa4meerkatgram.domain.post.mapper.PostMapper;
import com.msa4meerkatgram.domain.post.requests.PostIndexReq;
import com.msa4meerkatgram.domain.post.responses.PostIndexRes;
import com.msa4meerkatgram.global.errors.custom.DeletedRecordException;
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

    public Post show(long id) {
        Post post = postMapper.findByPk(id);

        if(post == null) {   // 해당 게시글 존재 여부 (그사이 게시자가 삭제할 수도 있는 경우)
            throw new DeletedRecordException("이미 삭제된 게시글입니다.");
        }

        return post;
    }
}