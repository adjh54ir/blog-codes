package com.adjh.springboot3security.config;

import com.adjh.springboot3security.config.filter.CustomAuthenticationFilter;
import com.adjh.springboot3security.config.filter.JwtAuthorizationFilter;
import com.adjh.springboot3security.config.handler.CustomAuthFailureHandler;
import com.adjh.springboot3security.config.handler.CustomAuthSuccessHandler;
import com.adjh.springboot3security.config.handler.CustomAuthenticationProvider;
import com.adjh.springboot3security.config.handler.CustomLogoutHandler;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * Spring Security 환경 설정을 구성하기 위한 설정 클래스입니다.
 * 웹 서비스가 로드 될때 Spring Container 의해 관리가 되는 클래스이며 사용자에 대한 ‘인증’과 ‘인가’에 대한 구성을 Bean 메서드로 주입을 합니다.
 *
 * @author : jonghoon
 * @fileName : WebSecurityConfig
 * @since : 10/1/24
 */
@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    /**
     * 1. 정적 자원(Resource)에 대해서 인증된 사용자가 정적 자원의 접근에 대해 ‘인가’에 대한 설정을 담당하는 메서드입니다.
     *
     * @return WebSecurityCustomizer
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // 정적 자원에 대해서 Security를 적용하지 않음으로 설정
        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    /**
     * 2. HTTP에 대해서 ‘인증’과 ‘인가’를 담당하는 메서드이며 필터를 통해 인증 방식과 인증 절차에 대해서 등록하며 설정을 담당하는 메서드입니다.
     *
     * @param http HttpSecurity
     * @return SecurityFilterChain
     * @throws Exception Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)                                                          // CSRF 보호 비활성화
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))                              // CORS 커스텀 설정 적용
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())                                   // 우선 모든 요청에 대한 허용
                .addFilterBefore(jwtAuthorizationFilter(), BasicAuthenticationFilter.class)                     // JWT 인증 (커스텀 필터)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))   // 세션 미사용 (JWT 사용)
                .addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)      // 사용자 인증(커스텀 필터)
                .formLogin(AbstractHttpConfigurer::disable)                                                     // 폼 로그인 비활성화
                .logout(this::configureLogout)                                                                  // 로그아웃 처리를 합니다.
                .build();
    }


    /**
     * 3. authenticate 의 인증 메서드를 제공하는 매니져로'Provider'의 인터페이스를 의미합니다.
     * - 과정: CustomAuthenticationFilter → AuthenticationManager(interface) → CustomAuthenticationProvider(implements)
     *
     * @return AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(customAuthenticationProvider());
    }

    /**
     * 4. '인증' 제공자로 사용자의 이름과 비밀번호를 데이터베이스에 제공하여 반환받습니다.
     * - 과정: CustomAuthenticationFilter → AuthenticationManager(interface) → CustomAuthenticationProvider(implements)
     *
     * @return CustomAuthenticationProvider
     */
    @Bean
    public CustomAuthenticationProvider customAuthenticationProvider() {
        return new CustomAuthenticationProvider(bCryptPasswordEncoder());
    }

    /**
     * 5. 비밀번호를 암호화하기 위한 BCrypt 인코딩을 통하여 비밀번호에 대한 암호화를 수행합니다.
     *
     * @return BCryptPasswordEncoder
     */
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * 6. 커스텀을 수행한 '인증' 필터로 접근 URL, 데이터 전달방식(form) 등 인증 과정 및 인증 후 처리에 대한 설정을 구성하는 메서드입니다.
     *
     * @return CustomAuthenticationFilter
     */
    @Bean
    public CustomAuthenticationFilter customAuthenticationFilter() {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager());
        customAuthenticationFilter.setFilterProcessesUrl("/api/v1/user/login");     // 접근 URL
        customAuthenticationFilter.setAuthenticationSuccessHandler(customLoginSuccessHandler());    // '인증' 성공 시 해당 핸들러로 처리를 전가한다.
        customAuthenticationFilter.setAuthenticationFailureHandler(customLoginFailureHandler());    // '인증' 실패 시 해당 핸들러로 처리를 전가한다.
        customAuthenticationFilter.afterPropertiesSet();
        return customAuthenticationFilter;
    }

    /**
     * 7. Spring Security 기반의 사용자의 정보가 '맞을 경우' 수행이 되며 결과값을 리턴해주는 Handler
     *
     * @return CustomLoginSuccessHandler
     */
    @Bean
    public CustomAuthSuccessHandler customLoginSuccessHandler() {
        return new CustomAuthSuccessHandler();
    }

    /**
     * 8. Spring Security 기반의 사용자의 정보가 '맞지 않을 경우' 수행이 되며 결과값을 리턴해주는 Handler
     *
     * @return CustomAuthFailureHandler
     */
    @Bean
    public CustomAuthFailureHandler customLoginFailureHandler() {
        return new CustomAuthFailureHandler();
    }


    /**
     * 9. JWT 토큰을 통하여서 사용자를 인증합니다.
     *
     * @return JwtAuthorizationFilter
     */
    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() {
        return new JwtAuthorizationFilter();
    }


    /**
     * 10. CORS에 대한 설정을 커스텀으로 구성합니다.
     *
     * @return CorsConfigurationSource
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));      // 허용할 오리진
        configuration.setAllowedMethods(List.of("*"));                          // 허용할 HTTP 메서드
        configuration.setAllowedHeaders(List.of("*"));                          // 모든 헤더 허용
        configuration.setAllowCredentials(true);                                    // 인증 정보 허용
        configuration.setMaxAge(3600L);                                             // 프리플라이트 요청 결과를 3600초 동안 캐시
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);             // 모든 경로에 대해 이 설정 적용
        return source;
    }


    /**
     * 11. 로그아웃에 대한 설정을 관리합니다.
     *
     * @param logout
     */
    private void configureLogout(@NonNull LogoutConfigurer<HttpSecurity> logout) {
        logout
                // 1. 로그아웃 엔드포인트를 지정합니다.
                .logoutUrl("/api/v1/user/logout")
                // 2. 엔드포인트 호출에 대한 처리 Handler를 구성합니다.
                .addLogoutHandler(customLogoutHandler())
                // 3. 로그아웃 처리가 완료되었을때 처리를 수행합니다.
                .logoutSuccessHandler((request, response, authentication) -> response.setStatus(HttpServletResponse.SC_OK));
    }

    /**
     * 12. 로그아웃 처리를 위한 Handler를 커스텀으로 구성합니다.
     *
     * @return
     */
    @Bean
    public LogoutHandler customLogoutHandler() {
        return new CustomLogoutHandler();
    }
}
