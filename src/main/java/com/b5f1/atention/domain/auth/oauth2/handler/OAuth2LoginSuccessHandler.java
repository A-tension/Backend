package com.b5f1.atention.domain.auth.oauth2.handler;

import com.b5f1.atention.domain.auth.jwt.JwtService;
import com.b5f1.atention.domain.auth.oauth2.CustomOAuth2User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtService jwtService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        log.debug("OAuth2 Login 성공!");
        try {
            CustomOAuth2User oAuth2User = (CustomOAuth2User)authentication.getPrincipal();
            // 로그인에 성공한 경우 access, refresh 토큰 생성
            System.out.println("oAuth2User : " + oAuth2User);
            log.debug("oAuth2User : " + oAuth2User);
            loginSuccess(response, oAuth2User);
        } catch (Exception e) {
            log.debug("asdasdasdqweqwfqwafsaf");
            throw e;
        }
    }

    // TODO : 소셜 로그인 시에도 무조건 토큰 생성하지 말고 JWT 인증 필터처럼 RefreshToken 유/무에 따라 다르게 처리해보기
    private void loginSuccess(HttpServletResponse response, CustomOAuth2User oAuth2User) throws IOException {
        System.out.println("oAuth2User : " + oAuth2User);
        String accessToken = jwtService.createAccessToken(oAuth2User.getId());
        String refreshToken = jwtService.createRefreshToken(oAuth2User.getId());
        response.addHeader(jwtService.getAccessHeader(), "Bearer " + accessToken);
        response.addHeader(jwtService.getRefreshHeader(), "Bearer " + refreshToken);

        jwtService.sendAccessAndRefreshToken(response, accessToken, refreshToken);
        jwtService.updateRefreshToken(oAuth2User.getId(), refreshToken);
    }
}
