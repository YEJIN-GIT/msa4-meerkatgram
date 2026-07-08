package com.msa4meerkatgram.domain.post.services;

import com.msa4meerkatgram.domain.post.entities.Post;
import com.msa4meerkatgram.domain.post.repositories.PostQueryRepository;
import com.msa4meerkatgram.domain.post.repositories.PostRespository;
import com.msa4meerkatgram.domain.post.requests.PostIndexReq;
import com.msa4meerkatgram.domain.post.responses.PostIndexRes;
import com.msa4meerkatgram.domain.post.responses.PostWithUserRes;
import com.msa4meerkatgram.global.errors.custom.DeletedRecordException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRespository postRespository;
    private final PostQueryRepository postQueryRepository;

    public PostIndexRes index(PostIndexReq postIndexReq) {
        // 특정 페이지의 게시글 조회
        int offet = (postIndexReq.page() - 1 ) * postIndexReq.limit();

        List<Post> result = postQueryRepository.pagination(offet, postIndexReq.limit());

        // 토탈 획득 (부하발생)
        long total = postRespository.count();
        boolean lastPage = offet + postIndexReq.limit() >= total;

        // 컨트롤러 전달
        return PostIndexRes.from(total, lastPage, result);
    }

    public PostWithUserRes show(long id) {
        Post result = postRespository.findById(id)  // 리턴타입이 Optional<T>
            .orElseThrow(() -> new DeletedRecordException("이미 삭제된 게시글입니다."));   // Optional<T> 결과가 null일 경우에 ()안에 것을 실행하겠다

        return PostWithUserRes.from(result);
    }
}