package com.msa4meerkatgram.global.security.jwt;

import com.msa4meerkatgram.domain.user.entities.User;
import com.msa4meerkatgram.global.errors.custom.InvalidTokenException;
import com.msa4meerkatgram.global.security.cookie.CookieManager;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Optional;

// bean : 스프링이 자동으로 인스턴스화 하여 관리해주는 객체
// @Component 어노테이션 : bean 으로 자동 등록 해준다.
@Component
public class JwtProvider {
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;
    private final CookieManager cookieManager;

    public JwtProvider(JwtConfig jwtConfig, CookieManager cookieManager) {
        this.jwtConfig = jwtConfig;
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtConfig.secret()));
        this.cookieManager = cookieManager;
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

    // 쿠키에서 리프래쉬 토큰 획득
    public Optional<String> extractRefreshToken(HttpServletRequest request) {
        return cookieManager.getCookie(request, jwtConfig.refreshTokenCookieName())
            .map(Cookie::getValue);
    }

    /**
     * 헤더에서 베어럴토큰(엑세스토큰) 추출
     * @param request 리퀘스트
     * @return Optional 엑세스 토큰
     */
    public Optional<String> extractAccessToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(jwtConfig.headerKey());

        if(bearerToken == null || !bearerToken.startsWith(jwtConfig.scheme())) {
            return Optional.empty();
        }

        return Optional.of(bearerToken.substring(jwtConfig.scheme().length()).trim());
    }

    // 토큰 검증 및 클래임 추출 : JJWT라이브러리는 클래임 추출과 검증을 같이 해버림
    public Claims extractClaims(String token) {
        try {
            return Jwts.parser()
                .verifyWith(this.secretKey)
                .build()
                .parseSignedClaims(token)   // 클래임의 유효성과 분해
                .getPayload();
        } catch (ExpiredJwtException e) {
            throw new InvalidTokenException("토큰이 만료됐습니다.");
        } catch (UnsupportedJwtException e) {
            throw new InvalidTokenException("서명이 위조된 토큰입니다.");
        } catch (MalformedJwtException e) {
            throw new InvalidTokenException("토큰 형식이 옳바르지 않습니다.");
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidTokenException("토큰 검증에 실패했습니다.");
        }
    }

}
