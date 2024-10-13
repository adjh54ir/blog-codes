package com.adjh.springboot3security.config.filter;

import com.adjh.springboot3security.common.utils.TokenUtils;
import com.adjh.springboot3security.model.dto.ValidTokenDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 지정한 URL 별 JWT 유효성 검증을 수행하며 직접적인 사용자 '인증'을 확인하는 필터 역할의 클래스입니다.
 *
 * @author : jonghoon
 * @fileName : CustomAuthenticationFilter
 * @since : 10/1/24
 */
@Slf4j
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private static final String HTTP_METHOD_OPTIONS = "OPTIONS";
    private static final List<String> WHITELIST_URLS = Arrays.asList(
            "/api/v1/user/login",
            "/api/v1/token/token",
            "/user/login",
            "/token/token"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain chain)
            throws IOException, ServletException {
        // 2. 토큰이 필요하지 않는 API 호출 발생 시 || 토큰이 필요없는 HTTP Method OPTIONS 호출 발생 시 : 아래 로직 처리 없이 다음 필터로 이동
        if (WHITELIST_URLS.contains(request.getRequestURI()) || request.getMethod().equalsIgnoreCase(HTTP_METHOD_OPTIONS)) {
            chain.doFilter(request, response);
            return;
        }
        // [STEP1] Client API 호출에서 "Authorization"를 속성을 확인합니다.
        String accessTokenHeader = request.getHeader("Authorization");
        String refreshTokenHeader = request.getHeader("x-refresh-token");

        try {
            // [STEP2-1] accessToken, refreshToken 존재여부를 체크합니다.
            if (StringUtils.isNotBlank(accessTokenHeader) || StringUtils.isNotBlank(refreshTokenHeader)) {

                // [STEP2] Header 내에 accessToken, refreshToken을 추출합니다.
                String accessToken = TokenUtils.getHeaderToToken(accessTokenHeader);
                String refreshToken = TokenUtils.getHeaderToToken(accessTokenHeader);

                ValidTokenDto accTokenValidDto = TokenUtils.isValidToken(accessToken);

                // [STEP3-1] accessToken이 유효한 경우
                if (accTokenValidDto.isValid()) {

                    // [STEP4] Claim 내에 사용자 정보가 있는 경우
                    if (StringUtils.isNotBlank(TokenUtils.getClaimsToUserId(accessToken))) {
                        chain.doFilter(request, response);      // 리소스로 접근을 허용합니다.
                    } else {
                        throw new Exception("토큰 내에 사용자 아이디가 존재하지 않습니다");    // 사용자 아이디가 존재하지 않는 경우
                    }
                }
                // [STEP3-2] accessToken이 유효하지 않은 경우
                else {

                    // [STEP4] 에러 메시지를 확인하여 만료가 된 경우 => Refresh 토큰을 확인합니다.
                    if (accTokenValidDto.getErrorName().equals("TOKEN_EXPIRED")) {
                        log.debug("headerRefreshToken :: " + refreshToken);

                        // Refresh Token이 유효한 경우
                        if (TokenUtils.isValidToken(refreshToken).isValid()) {
                            String userId = TokenUtils.getClaimsToUserId(refreshToken);
                            // [STEP5] 사용자 아이디가 존재하는지 여부 체크
                            if (StringUtils.isNotBlank(userId)) {

                                // ==========> refresh Token을 기반으로 Access Token을 발급해줍니다.

                                chain.doFilter(request, response);      // 리소스로 접근
                            } else {

                                // 만료된 경우
                                if (TokenUtils.isValidToken(refreshToken).getErrorName().equals("TOKEN_EXPIRED")) {

                                }

                                throw new Exception("토큰 내에 사용자 아이디가 존재하지 않습니다");    // 사용자 아이디가 존재하지 않는 경우
                            }
                        } else {
                            System.out.println("refresh Token!! Error ::");
                        }
                    }
                    throw new Exception("토큰이 유효하지 않습니다.");                      // 토큰이 유효하지 않은 경우
                }
            } else {
                throw new Exception("토큰이 존재하지 않습니다.");                          // 토큰이 존재하지 않는 경우
            }
        }
        // Token 내에 Exception 발생 하였을 경우 : 클라이언트에 응답값을 반환하고 종료합니다.
        catch (Exception e) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            PrintWriter printWriter = response.getWriter();
            String jsonResponse = jwtTokenError(e);
            printWriter.print(jsonResponse);
            printWriter.flush();
            printWriter.close();
        }
    }

    /**
     * JWT 내에 Exception 발생 시 JSON 형태의 예외 응답값을 구성하는 메서드
     *
     * @param e Exception
     * @return String
     */
    private String jwtTokenError(Exception e) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonMap = new HashMap<>();
        String resultMsg = "";

        // [CASE1] JWT 기간 만료
        if (e instanceof ExpiredJwtException) {
            resultMsg = "토큰 기간이 만료되었습니다.";
        }
        // [CASE2] JWT내에서 오류 발생 시
        else if (e instanceof JwtException) {
            resultMsg = "잘못된 토큰이 발급되었습니다.";
        }
        // [CASE3] 이외 JWT내에서 오류 발생
        else {
            resultMsg = "OTHER TOKEN ERROR";
        }
        // Custom Error Code 구성
        jsonMap.put("status", 403);
        jsonMap.put("code", "9999");
        jsonMap.put("message", resultMsg);
        jsonMap.put("reason", e.getMessage());

        try {
            return objectMapper.writeValueAsString(jsonMap);
        } catch (JsonProcessingException err) {
            log.error("내부적으로 JSON Parsing Error 발생 " + err);
            return "{}"; // 빈 JSON 객체를 반환
        }
    }
}
