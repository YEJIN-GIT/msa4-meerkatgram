package com.msa4meerkatgram.domain.auth.repositories;

import com.msa4meerkatgram.domain.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);    // 단순이 이메일의 정보가 있는지만 체크하여 boolean으로 받는다
}
