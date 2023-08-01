package com.b5f1.atention.domain.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.b5f1.atention.domain.user.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Getter
@Slf4j
public class JwtService {

    // JWT 서명에 사용되는 비밀 키
     private String secretKey;

    // 액세스 토큰 만료 기간(ms)
    @Value("${jwt.access.expiration}")
    private Long accessTokenExpirationPeriod;

    // 리프레시 토큰 만료 기간(ms)
    @Value("${jwt.refresh.expiration}")
    private Long refreshTokenExpirationPeriod;

    // 액세스 토큰을 응답 헤더에 저장하는데 사용되는 헤더 키
    @Value("${jwt.access.header}")
    private String accessHeader;

    // 리프레시 토큰을 응답 헤더에 저장하는데 사용되는 헤더 키
    @Value("${jwt.refresh.header}")
    private String refreshHeader;

    // email 내용을 담을 클레임 이름
    private static final String EMAIL_CLAIM = "email";

    // 사용자 데이터에 접근하기 위한 리포지토리
    private final UserRepository userRepository;

    /**
     * AccessToken 생성 메소드
     */
    public String createAccessToken(UUID uuid, String email) {
        Date now = new Date();
        // JWT 토큰을 생성하는 빌더 반환
        // create메서드는 라이브러리 내부상 static으로 선언되어 있음.
        return JWT.create()
                // JWT의 Subject 지정 -> AccessToken이므로 AccessToken
                .withSubject(uuid.toString())
                // 토큰 만료 시간 설정
                .withExpiresAt(new Date(now.getTime() + accessTokenExpirationPeriod))
                // 클레임에 email 저장
                .withClaim(EMAIL_CLAIM, email)
                // HMAC512 알고리즘 사용, application-jwt.yml에서 지정한 secret 키로 암호화
                .sign(Algorithm.HMAC512(secretKey));
    }

    /**
     * RefreshToken 생성
     * RefreshToken은 Claim에 email도 넣지 않으므로 withClaim() X
     */
    public String createRefreshToken(UUID uuid) {
        Date now = new Date();
        return JWT.create()
                .withSubject(uuid.toString())
                .withExpiresAt(new Date(now.getTime() + refreshTokenExpirationPeriod))
                .sign(Algorithm.HMAC512(secretKey));
    }

}
