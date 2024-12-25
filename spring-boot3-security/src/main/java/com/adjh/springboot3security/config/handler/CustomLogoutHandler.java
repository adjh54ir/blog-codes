package com.adjh.springboot3security.config.handler;

import com.adjh.springboot3security.common.utils.TokenUtils;
import com.adjh.springboot3security.service.TokenBlackListService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 로그아웃에 대한 처리를 관리하는 Handler입니다.
 *
 * @author : jonghoon
 * @fileName : UserLogoutService
 * @since : 11/2/24
 */
@Slf4j
@Service
public class CustomLogoutHandler implements LogoutHandler {

    @Autowired
    private TokenBlackListService tokenBlackListService;

    /**
     * 로그아웃 엔드포인트로 호출되면 이에 대해 처리합니다.
     *
     * @param request        the HTTP request
     * @param response       the HTTP response
     * @param authentication the current principal details
     */
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        log.debug("[+] 로그아웃이 수행이 됩니다.");

        // [STEP1] 요청 값에서 토큰을 추출합니다.
        String headerToken = request.getHeader("Authorization");


        // [STEP2-1] 토큰이 존재하는 경우
        if (headerToken != null) {

            // [STEP3] 실제 토큰 값을 확인합니다.
            String token = TokenUtils.getHeaderToToken(headerToken);

            // [STEP4] Redis 내에 토큰이 존재하지 않는 경우
            if (!tokenBlackListService.isContainToken(token)) {

                // [STEP5] BlackList를 추가합니다.
                tokenBlackListService.addTokenToList(token);
                List<Object> blackList = tokenBlackListService.getTokenBlackList();      // BlackList를 조회합니다.
                log.debug("[+] blackList : " + blackList);
            }
        }
        // [STEP2-2] 토큰이 존재하지 않는 경우
        else {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("userInfo", null);
            resultMap.put("resultCode", 9999);
            resultMap.put("failMsg", "로그아웃 과정에서 문제가 발생하였습니다.");

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResponse = null;
            PrintWriter printWriter = null;
            try {
                jsonResponse = objectMapper.writeValueAsString(resultMap);
                printWriter = response.getWriter();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            printWriter.print(jsonResponse);
            printWriter.flush();
            printWriter.close();
        }

    }
}
