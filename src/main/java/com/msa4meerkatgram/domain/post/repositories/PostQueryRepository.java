package com.msa4meerkatgram.domain.post.repositories;

import com.msa4meerkatgram.domain.post.entities.Post;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.msa4meerkatgram.domain.post.entities.QPost.post;
import static com.msa4meerkatgram.domain.user.entities.QUser.user;

// 빌드하면 build 파일경로에 Q-Class 생성됨

@Repository
@RequiredArgsConstructor
public class PostQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

//    SELECT *
//    FROM posts
//         JOIN users
//             ON posts.user_id= users.user_id
//    WHERE deleted_at IS NULL
//    ORDER BY created_at DESC, id ASC
//    LIMIT ? OFFSET ?;
    public List<Post> pagination (int offset, int limit) {
        return jpaQueryFactory
            .selectFrom(post)
            .join(post.user, user).fetchJoin()
            .orderBy(post.createdAt.desc(), post.id.desc())
            .limit(limit)
            .offset(offset)
            .fetch();
    }

    // 동적 쿼리 예시
//    JPAQuery<Post> test = jpaQueryFactory
//            .selectFrom(post);
//    if(limit != 1) {
//        test.limit(limit);
//    }
//    return test.fetch();
}
