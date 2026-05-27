package com.msa4meerkatgram.global.security.jwt;

import com.msa4meerkatgram.domain.user.entities.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

// bean : 스프링이 자동으로 인스턴스화 하여 관리해주는 객체
// @Component 어노테이션 : bean 으로 자동 등록 해준다.
@Component
public class JwtProvider {
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;

    public JwtProvider(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtConfig.secret()));
    }

    // private 외부 접근 안됨
    private String generateToken(User user, long ttl) {
        Date now = new Date();

        return Jwts.builder()
                .header()               // 헤더 세팅하겠다.
                .type(jwtConfig.type()) // 토큰 유형 설정
                .and()                  // 추가 연결
                .subject(String.valueOf(user.getId()))      // subject : 유저를 특정하는 아이디 세팅에 주로 사용
                .issuer(jwtConfig.issuer())                 // 토큰 발급자
                .issuedAt(now)                              // 토큰 발급 시간
                .expiration(new Date(now.getTime() + ttl))  // 만료 시간
                .claim("role", user.getRole())        // private claim 설정
                .signWith(secretKey)
                .compact();
    }

    // public 외부에서 접근
    public String generateAccessToken(User user) {
        return this.generateToken(user, jwtConfig.accessTokenExpiry());
    }

    public String generateRefreshToken(User user) {
        return this.generateToken(user, jwtConfig.refreshTokenExpiry());
    }

}
