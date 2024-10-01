package com.adjh.springboot3security.config.interceptor;

import com.adjh.springboot3security.common.utils.TokenUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.servlet.HandlerInterceptor;


@Log4j2
public class JwtTokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String header = request.getHeader("Authorization");

        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            log.debug("if request options method is options, return true");

            return true;
        }

        if (header != null) {

            String token = TokenUtils.getTokenFromHeader(header);

            if (TokenUtils.isValidToken(token)) {
                String userId = TokenUtils.getUserIdFromToken(token);
                if (userId == null) {
                    log.error("token isn't userId");
                }
                return true;
            } else {
                log.error("token is invalid");
                return false;
            }
        } else {
            log.error("Header not exist token");
            return false;
        }
    }
}