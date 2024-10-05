package com.adjh.springboot3security.config.handler;

import com.adjh.springboot3security.common.utils.TokenUtils;
import com.adjh.springboot3security.model.constant.AuthConstants;
import com.adjh.springboot3security.model.dto.UserDetailsDto;
import com.adjh.springboot3security.model.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 사용자의 '인증'에 대해 성공하였을 경우 수행되는 Handler로 성공에 대한 사용자에게 반환값을 구성하여 전달합니다
 */
@Slf4j
@Configuration
public class CustomAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        log.debug("3.1. CustomLoginSuccessHandler");

        // [STEP1] 사용자와 관련된 정보를 모두 조회합니다.
        UserDto userDto = ((UserDetailsDto) authentication.getPrincipal()).getUserDto();

        // [STEP2] 응답 데이터를 구성합니다.
        Map<String, Object> responseMap = new HashMap<String, Object>();
        responseMap.put("userInfo", userDto);

        // [STEP3] 사용자의 상태에 따라 응답 데이터를 설정합니다.
        if ("D".equals(userDto.getUserSt())) {
            responseMap.put("resultCode", 9001);
            responseMap.put("token", null);
            responseMap.put("failMsg", "휴면 계정입니다.");
        } else {
            responseMap.put("resultCode", 200);
            responseMap.put("failMsg", null);

            String token = TokenUtils.generateJwtToken(userDto);
            responseMap.put("token", token);
            response.addHeader(AuthConstants.AUTH_HEADER, AuthConstants.TOKEN_TYPE + " " + token);
        }

        // [STEP4] 구성한 응답 값을 JSON 형태로 전달합니다.
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(objectMapper.writeValueAsString(responseMap));
        printWriter.flush();
        printWriter.close();
    }
}