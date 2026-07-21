package com.msa4meerkatgram.domain.auth.services;

import com.msa4meerkatgram.domain.auth.repositories.AuthRepository;
import com.msa4meerkatgram.domain.auth.requests.LoginReq;
import com.msa4meerkatgram.domain.auth.requests.RegistrationReq;
import com.msa4meerkatgram.domain.auth.responses.AuthRes;
import com.msa4meerkatgram.domain.post.repositories.PostRespository;
import com.msa4meerkatgram.domain.user.entities.User;
import com.msa4meerkatgram.global.errors.custom.DuplicatedRecordException;
import com.msa4meerkatgram.global.errors.custom.InvalidTokenException;
import com.msa4meerkatgram.global.errors.custom.NotRegisteredException;
import com.msa4meerkatgram.global.security.constant.ProviderPolicy;
import com.msa4meerkatgram.global.security.constant.RolePolicy;
import com.msa4meerkatgram.global.security.cookie.CookieManager;
import com.msa4meerkatgram.global.security.jwt.JwtConfig;
import com.msa4meerkatgram.global.security.jwt.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtProvider jwtProvider;
    private final CookieManager cookieManager;
    private final JwtConfig jwtConfig;
    private final PasswordEncoder passwordEncoder;
    private final AuthRepository authRepository;
    private final PostRespository postRespository;

    // @Transactional   하나의 update 문이라서 트랜젝션을 굳이 묶지 않았다.
    public AuthRes login(HttpServletResponse response, LoginReq loginReq) {
        // 유저정보 획득 + 유저 가입 여부 확인
        User user = authRepository.findByEmail(loginReq.email())
                .orElseThrow(() -> new NotRegisteredException("아이디와 비밀번호를 확인해주세요."));

        // 비밀번호 체크
        if(!passwordEncoder.matches(loginReq.password(), user.getPassword())) {
            throw new NotRegisteredException("아이디와 비밀번호를 확인해주세요."); // 보안상 아이디와 비밀번호를 둘다 확인해달고 뭉뚱그려 메시지 사용한다.
        }

        return this.generateAuthentication(response, user);
    }

    public AuthRes reissue(HttpServletRequest request, HttpServletResponse response) {
        // 리프래쉬 토큰 획득
//        Optional<String> refreshTokenOptional = jwtProvider.extractRefreshToken(request);
//        if(refreshTokenOptional.isEmpty()) {
//            throw new InvalidTokenException("토큰이 없습니다.");
//        }
//        String extractRefreshToken = refreshTokenOptional.get();

        String extractRefreshToken = jwtProvider.extractRefreshToken(request)
                .orElseThrow(() -> new InvalidTokenException("토큰이 없습니다."));

        long id = Long.parseLong(jwtProvider.extractClaims(extractRefreshToken).getSubject());

        // 유저정보 획득
        User user = authRepository.findById(id).orElseThrow(() -> new InvalidTokenException("유효하지 않은 회원의 토큰입니다."));

        // 비로그인 상태 확인
        if(user.getRefreshToken() == null) {
            throw new InvalidTokenException("유효하지 않은 회원의 토큰입니다.");
        }

        // DB에 리프래시 토큰과 유저가 보내온 리프래시 토큰 비교
        if(!user.getRefreshToken().equals(extractRefreshToken)) {
            throw new InvalidTokenException("토큰이 일치하지 않습니다.");
        }

        return this.generateAuthentication(response, user);
    }

    /**
     * (login와 reissue 에서 호출하므로 공통 모듈로 뺌)
     * 엑세스 토크 및 리프래시 토큰 생성 후, 리프래시 토큰 DB&Cookie에 저장, AuthRes로 반환
     * @param response HttpServletResponse
     * @param user 유저 Entity
     * @return AuthRes
     */
    private AuthRes generateAuthentication(HttpServletResponse response, User user) {
        // 작성 게시글 수 획등
        long countPosts = postRespository.countByUser(user);

        // 토큰 생성
        String newAccessToken = jwtProvider.generateAccessToken(user);
        String newRefreshToken = jwtProvider.generateRefreshToken(user);

        // 리프래시 토큰을 DB 저장
        user.setRefreshToken(newRefreshToken);
        authRepository.save(user);  // 영속성 컨텍스트: 이전값과 바뀐값을 비교해서 실제로 변경된 것만 update해준다

        // 리프래시 토큰을 Cookie에 저장
        cookieManager.setCookie(response, jwtConfig.refreshTokenCookieName(), newRefreshToken, jwtConfig.refreshTokenCookieExpiry(), jwtConfig.reissUri());

        // 리턴
        return AuthRes.from(user, newAccessToken, countPosts);
    }

    @Transactional(rollbackFor = Exception.class)   // 런타입 익셉션을 보통 롤백이 안일어나기 때문에, rollbackFor = Exception.class로 하여 어떠한 예외가 발생하더라도 롤백한다.
    public void logout(HttpServletResponse response, Long id) {
        // 유저 정보 획득
        User user = authRepository.findById(id) // 한건을 가져오면 Optional로, 여러건은 List로 가져온다
            .orElseThrow(() -> new InvalidTokenException("유효하지 않은 회원의 토큰입니다."));

        // DB에 저장한 리프래시 토큰 파기
        user.setRefreshToken(null);
        authRepository.save(user);  // 내가 save할 때 던저준 아규먼터의 타입 User를 반환하지만, 반환값을 사용하지는 않는다.

        // Cookie에 지정한 리프래시 토큰 파기
        cookieManager.setCookie(response, jwtConfig.refreshTokenCookieName(), null, 0, jwtConfig.reissUri());
    }

    @Transactional(rollbackFor = Exception.class)
    public void registration(RegistrationReq registrationReq) {
        // 이미 가입한 회원인지 체크
        // 방법1.
//        Optional<User> user = authRepository.findByEmail(registrationReq.email());
//        if(user.isPresent()) {
//            throw new DuplicatedRecordException("이미 가입된 회원입니다.");
//        }

        // 방법2.
        // 유저 가입여부 확인 (exists 쿼리를 사용하면 대용량 환경에서 효율이 증가)
        if(authRepository.existsByEmail(registrationReq.email())) {
            throw new DuplicatedRecordException("이미 가입된 회원입니다.");
        }

//        User newUser = new User();
//        newUser.setEmail(registrationReq.email());
//        newUser.setPassword(passwordEncoder.encode(registrationReq.password()));
//        newUser.setNick(registrationReq.nick());
//        newUser.setProfile(registrationReq.profile());
//        newUser.setProvider(ProviderPolicy.NONE.getProvider());
//        newUser.setRole(RolePolicy.NORMAL.getRole());
//        authMapper.create(newUser);

        User newUser = new User();
        newUser.setEmail(registrationReq.email());
        newUser.setPassword(passwordEncoder.encode(registrationReq.password()));
        newUser.setNick(registrationReq.nick());
        newUser.setProfile(registrationReq.profile());
        newUser.setProvider(ProviderPolicy.NONE);
        newUser.setRole(RolePolicy.NORMAL);
        authRepository.save(newUser);
    }
}
