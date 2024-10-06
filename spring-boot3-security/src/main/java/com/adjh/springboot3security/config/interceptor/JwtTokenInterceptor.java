package com.adjh.springboot3security.config.interceptor;

import com.adjh.springboot3security.common.utils.TokenUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.servlet.HandlerInterceptor;


/**
 * API 요청 시 사전에 토큰에 대한 정합성을 체크하는 인터셉터 역할을 하는 클래스입니다.
 *
 * @author : jonghoon
 * @fileName : JwtTokenInterceptor
 * @since : 10/1/24
 */
@Log4j2
public class JwtTokenInterceptor implements HandlerInterceptor {

    /**
     * 요청이 컨트롤러에 도달하기 전에 실행됩니다.
     *
     * @param request  current HTTP request
     * @param response current HTTP response
     * @param handler  chosen handler to execute, for type and/or instance evaluation
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // [STEP1] 요청의 HTTP Method를 조회하여 결과를 반환합니다. : OPTION의 경우 통과
        if (isOptionsRequest(request)) {
            return true;
        }

        // [STEP2] 요청의 헤더 내에 값이 존재하는지 확인하고 존재하는 경우 Token 정보 반환
        String token = extractToken(request);

        // [STEP3] 추출된 토큰을 기반으로 토큰의 유효성을 체크합니다.
        return validateToken(token);
    }

    /**
     * 요청의 HTTP Method를 조회하여 결과를 반환합니다.
     *
     * @param request
     * @return
     */
    private boolean isOptionsRequest(HttpServletRequest request) {
        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            log.debug("OPTIONS request, allowing passage");
            return true;
        }
        return false;
    }

    /**
     * 요청의 헤더 내에 값이 존재하는지 확인하고 존재하는 경우 Token 정보 반환
     *
     * @param request
     * @return
     */
    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null) {
            log.error("Authorization header is missing");
            return null;
        }
        return TokenUtils.getHeaderToToken(header);
    }

    /**
     * 추출된 토큰을 기반으로 토큰의 유효성을 체크합니다.
     *
     * @param token
     * @return
     */
    private boolean validateToken(String token) {

        // [STEP1] 토큰이 존재하지 않는 경우
        if (token == null) {
            return false;
        }

        // [STEP2] 토큰이 유효하지 않는 경우
        if (!TokenUtils.isValidToken(token)) {
            log.error("Token is invalid");
            return false;
        }

        // [STEP3] 토큰내에 클레임을 조회하였을때 존재하는지 체크
        String userId = TokenUtils.getClaimsToUserId(token);
        if (userId == null) {
            log.error("UserId not found in token");
            return false;
        }

        return true;
    }
}