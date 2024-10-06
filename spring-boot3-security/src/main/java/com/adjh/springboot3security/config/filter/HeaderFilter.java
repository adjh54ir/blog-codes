package com.adjh.springboot3security.config.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * HTTP 응답에 CORS(Cross-Origin Resource Sharing) 관련 헤더를 설정하는 필터 역할의 클래스입니다.
 *
 * @author : jonghoon
 * @fileName : CustomAuthenticationFilter
 * @since : 10/1/24
 */

@Slf4j
@Component
public class HeaderFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;
        // 모든 출처에서의 요청을 허용합니다
        res.setHeader("Access-Control-Allow-Origin", "*");

        // GET, POST, PUT, DELETE 메소드를 허용합니다
        res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");

        // 프리플라이트 요청의 결과를 3600초 동안 캐시할 수 있도록 설정합니다
        res.setHeader("Access-Control-Max-Age", "3600");

        // 특정 헤더(X-Requested-With, Content-Type, Authorization, X-XSRF-token)를 허용합니다
        res.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, Authorization, X-XSRF-token");

        // 자격 증명(credentials)을 허용하지 않도록 설정합니다
        res.setHeader("Access-Control-Allow-Credentials", "false");

        chain.doFilter(request, response);
    }
}