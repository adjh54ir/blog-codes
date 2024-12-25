package com.adjh.springbootoauth2.config.handler;

import com.adjh.springbootoauth2.dto.UserDetailsDto;
import com.adjh.springbootoauth2.dto.UserDto;
import com.adjh.springbootoauth2.utils.TokenUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

/**
 * OAuth2 로그인 성공시 Handler
 *
 * @author : jonghoon
 * @fileName : CustomOAuth2SuccessHandler
 * @since : 11/1/24
 */
@Component
public class CustomOAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private static final String REDIRECT_URI = "/auth/success";

    /**
     * OAuth2 로그인 성공 시 처리
     *
     * @param request        the request which caused the successful authentication
     * @param response       the response
     * @param chain          the {@link FilterChain} which can be used to proceed other filters in
     *                       the chain
     * @param authentication the <tt>Authentication</tt> object which was created during
     *                       the authentication process.
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        UserDto userDto = ((UserDetailsDto) authentication.getPrincipal()).getUserDto();        // 결과 값을 받습니다.
        String accessToken = TokenUtils.generateJwt(userDto);                                   // 접근 토큰(Access Token)
        String refreshToken = TokenUtils.generateRefreshToken(userDto);                         // 갱신 토큰(Refresh Token)

        // 토큰을 리다이렉트 URL의 파라미터로 추가
        String targetUrl = UriComponentsBuilder
                .fromUriString(REDIRECT_URI)
                .queryParam("accessToken", accessToken)
                .queryParam("refreshToken", refreshToken)
                .build().toUriString();

        // 리다이렉트 수행
        response.sendRedirect(targetUrl);

        AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
    }

    /**
     * @param request        the request which caused the successful authentication
     * @param response       the response
     * @param authentication the <tt>Authentication</tt> object which was created during
     *                       the authentication process.
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 기본 구현은 4개 매개변수 메서드를 호출
        onAuthenticationSuccess(request, response, null, authentication);

    }

}
