package com.adjh.springboot3security.config.filter;

import com.adjh.springboot3security.common.utils.TokenUtils;
import com.adjh.springboot3security.model.dto.UserDto;
import com.adjh.springboot3security.model.dto.ValidTokenDto;
import com.adjh.springboot3security.service.TokenBlackListService;
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
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private TokenBlackListService tokenBlackListService;

    private static final String HTTP_METHOD_OPTIONS = "OPTIONS";
    private static final String ACCESS_TOKEN_HEADER_KEY = "Authorization";
    private static final String REFRESH_TOKEN_HEADER_KEY = "x-refresh-token";
    private static final List<String> WHITELIST_URLS = Arrays.asList(
            "/api/v1/user/login",
            "/api/v1/token/token",
            "/user/login",
            "/token/token"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain chain)
            throws IOException, ServletException {

        // [STEP1] 토큰이 필요하지 않는 API 호출 발생 혹은 토큰이 필요없는 HTTP Method OPTIONS 호출 시 : 아래 로직 처리 없이 다음 필터로 이동
        if (WHITELIST_URLS.contains(request.getRequestURI()) || HTTP_METHOD_OPTIONS.equalsIgnoreCase(request.getMethod())) {
            chain.doFilter(request, response);
            return;     // 종료
        }

        try {
            // [STEP2] Header 내에 Authorization, x-refresh-token를 확인하여 접근/갱신 토큰의 존재여부를 체크합니다.
            String accessTokenHeader = request.getHeader(ACCESS_TOKEN_HEADER_KEY);
            String refreshTokenHeader = request.getHeader(REFRESH_TOKEN_HEADER_KEY);


            // [STEP2-1] 토큰이 존재하면 다음 프로세스를 진행합니다.
            if (StringUtils.isNotBlank(accessTokenHeader) || StringUtils.isNotBlank(refreshTokenHeader)) {

                String paramAccessToken = TokenUtils.getHeaderToToken(accessTokenHeader);
                String paramRefreshToken = TokenUtils.getHeaderToToken(refreshTokenHeader);

                // [STEP3] 블랙리스트에 포함된 토큰으로 접근하는 경우, 이를 막아줍니다.
                if (tokenBlackListService.isContainToken(paramAccessToken)) {
                    throw new Exception("<< 경고 >>만료된 토큰으로 접근하려합니다!!!");
                }

                // [STEP4] 접근 토큰(Access Token)의 유효성을 체크합니다.
                ValidTokenDto accTokenValidDto = TokenUtils.isValidToken(paramAccessToken);

                // [STEP5-1] 접근 토큰이 유효하다면 다음 프로세스를 진행합니다.
                if (accTokenValidDto.isValid()) {
                    // [STEP6] 접근 토큰(Access Token)내에 전달하려는 사용자 정보를 확인합니다.
                    // [STEP6-1] 사용자 정보가 존재한다면 다음 필터로 이동을 합니다.
                    if (StringUtils.isNotBlank(TokenUtils.getClaimsToUserId(paramAccessToken))) {
                        chain.doFilter(request, response);
                    }
                    // [STEP6-2] 사용자 정보가 존재하지 않는 다면 에러 메시지를 클라이언트에게 전달합니다.
                    else {
                        throw new Exception("토큰 내에 사용자 아이디가 존재하지 않습니다");
                    }
                }
                // [STEP5-2] 접근 토큰이 존재하지 않으면 접근 토큰의 에러 정보를 확인합니다.
                else {
                    // [STEP6] 접근 토큰(Access Token)에서 발생한 오류가 만료된 (TOKEN_EXPIRED)오류 인지를 체크합니다.
                    // [STEP6-1] 오류가 토큰이 만료된 오류 인 경우 다음 프로세스를 진행합니다.
                    if (accTokenValidDto.getErrorName().equals("TOKEN_EXPIRED")) {

                        // [STEP7] 리프레시 토큰(Refresh Token)이 유효한지 체크를 합니다.
                        // [STEP7-1] 리프레시 토큰이 유효하다면 접근 토큰을 갱신합니다. 갱신하여 재 생성된 접근 토큰을 반환합니다.
                        if (TokenUtils.isValidToken(paramRefreshToken).isValid()) {
                            // Token 내에 사용자 정보를 추출하고 이를 기반으로 토큰을 생성합니다.
                            UserDto claimsToUserDto = TokenUtils.getClaimsToUserDto(paramRefreshToken, false);
                            System.out.println("claimsToUserDto ::  " + claimsToUserDto);
                            String token = TokenUtils.generateJwt(claimsToUserDto);         // 접근 토큰(AccessToken)을 새로 발급합니다.
                            sendToClientAccessToken(token, response);                       // 발급한 접근 토큰을 클라이언트에게 전달합니다.
                            chain.doFilter(request, response);                              // 리소스로 접근을 허용합니다.
                        }
                        // [STEP7-2] 리프레시 토큰이 유효하지 않다면 에러 메시지를 클라이언트에게 전달합니다.
                        else {
                            throw new Exception("재 로그인이 필요합니다.");
                        }
                    }
                    // [STEP7-2] 오류가 토큰이 만료된 경우가 아닌 경우 에러 메시지를 클라이언트에게 전달합니다.
                    throw new Exception("토큰이 유효하지 않습니다.");                      // 토큰이 유효하지 않은 경우
                }
            }
            // [STEP2-2] 토큰이 존재하지 않으면 “토큰이 존재하지 않습니다”라는 에러메시지를 클라이언트에게 전달합니다.
            else {
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
        ObjectMapper om = new ObjectMapper();
        Map<String, Object> resultMap = new HashMap<>();
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
            resultMsg = "OTHER TOKEN ERROR" + e;
        }
        // Custom Error Code 구성
        resultMap.put("status", 403);
        resultMap.put("code", "9999");
        resultMap.put("message", resultMsg);
        resultMap.put("reason", e.getMessage());

        try {
            return om.writeValueAsString(resultMap);
        } catch (JsonProcessingException err) {
            log.error("내부적으로 JSON Parsing Error 발생 " + err);
            return "{}"; // 빈 JSON 객체를 반환
        }
    }

    /**
     * 클라이언트에게 접근 토큰을 전달합니다.
     *
     * @param token
     * @param response
     */
    private void sendToClientAccessToken(String token, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();
        ObjectMapper om = new ObjectMapper();
        resultMap.put("status", 401);
        resultMap.put("failMsg", null);
        resultMap.put("accessToken", token);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        try {
            PrintWriter printWriter = response.getWriter();
            printWriter.write(om.writeValueAsString(resultMap));
            printWriter.flush();
            printWriter.close();
        } catch (IOException e) {
            log.error("[-] 결과값 생성에 실패하였습니다 : {}", e);
        }

    }
}
