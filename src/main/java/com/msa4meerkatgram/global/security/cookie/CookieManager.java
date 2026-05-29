package com.msa4meerkatgram.global.security.cookie;

import com.msa4meerkatgram.global.security.jwt.JwtConfig;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CookieManager {

    private final JwtConfig jwtConfig;

    // ---------------------------------------------------
    // 설명 : request Header에서 특정 쿠키를 획득(Optional 반환) 메소드
    // ---------------------------------------------------
    // Optional : null 반환 가능성에 대해 팀원이 알고 있어야 한다!!  자바에서 에러 위험
    //           개발자에게 null 처리 오류 대해 Optional 객체로 감싸주어 IDE가 빨간줄로 알려준다.
    /**
     * request Header에서 특정 쿠키를 획득(Optional 반환) 메소드
     * @param request 리퀘스트
     * @param name 찾고자하는 쿠키명
     * @return Optional<Cookie>
     */
    public Optional<Cookie> getCookie(HttpServletRequest request, String name) {
        // case1. 쿠키배열에 값이 없는 경우
        if(request.getCookies() == null) {
            return Optional.empty(); // 데이터가 없다는 의미의 만들어주는 메소드

        }

        // case2. 쿠키배열에 값이 있는 경우
        return Arrays.stream(request.getCookies())  // stream으로 변환
                .filter(cookie -> cookie.getName().equals(name))    // name과 일치하는 쿠키 획득
                .findFirst();
    }

    // ---------------------------------------------------
    // 설명 : 쿠기 생성 메소드
    // ---------------------------------------------------
    public void setCookie(HttpServletResponse response, String name, String value, int maxAge, String path) {
        Cookie cookie = new Cookie(name, value); // 해당 이름과 값으로 쿠기 인스턴스 생성
        cookie.setPath(path);       // 쿠키를 사용할 path 설정 : 그 패스로 왔을 때만 쿠키를 전송하도록
        cookie.setMaxAge(maxAge);   // 쿠기 유효 시간 설정
        cookie.setHttpOnly(true);   // XSS 공격 방지 설정 : 자바스크립트 코드가 쿠키에 접근 불가하도록 설정
        // 시큐어 설정(MITM 공격 방지).
        // true 설정 시 HTTPS(암호화된 통신) 환경에서만 쿠키를 서버로 전송. 개발환경 false / 운영환경 true 로 해주어야 함.
        cookie.setSecure(jwtConfig.secure());

        response.addCookie(cookie);
    }
}
