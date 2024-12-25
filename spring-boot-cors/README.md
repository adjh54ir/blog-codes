# Spring Boot Cors

    ⭕️ Kotlin 언어를 기반으로 Spring Boot 를 활용한 예시를 담은 Repository 입니다.

⭕️ 가이드 링크

- [Spring Boot 환경에서 CORS(Cross Origin Resource Sharing) 이해하고 활용하기 -1](https://adjh54.tistory.com/587)

<br/>
<br/>

## 1. 개발환경

    ⭕️ Spring Boot 환경에서 CORS(Cross Origin Resource Sharing)를 활용하는 방법에 대해 알아봅니다.

| 환경 분류                   | 버전      |
|-------------------------|---------|
| JDK                     | Java 17 |
| Kotlin                  | 1.9.25  |
| spring-boot             | 3.3.5   |
| spring-boot-starter-web | 3.3.5   |
| Lombok                  | -       |

<br/>
<br/>

## 2. 처리 과정

    💡동일한 출처(Same Origin)와 다른 출처(Cross Origin)

    💡동일한 출처(Same Origin)

    - domain-a.com이라는 웹 브라우저와 domain-a.com이라는 웹 서버는 동일한 출처(Origin)를 가지고 있습니다.
    
      1. 웹 브라우저에서는 [GET] /index.html 페이지를 웹 서버로 요청을 합니다.
      - 해당 페이지에는 styles.css, header.png 파일이 포함되어 있어 함께 요청이 됩니다.
    
      2. 웹 서버에서는 동일한 출처(Same-origin) 임을 판단하여 리소스 접근에 대한 요청을 수락(allow)하여 페이지와 이미지를 제공합니다.
    
    
    💡 다른 출처(Cross Origin)
    
    - domain-b.com이라는 웹 브라우저와 domain-a.com이라는 웹 서버는 서로 다른 출처(Origin)를 가지고 있습니다.
    
      1. 웹 브라우저에서는 [GET] img.png 파일을 웹 서버에 요청합니다.
      - 웹 브라우저에서는 동일한 출처(Same-Origin)가 아님이 판단되어서 CORS 오류를 웹 브라우저에게 반환합니다.
    
      2. 해당 상황에서 CORS를 허용하였다면 서로 다른 출처(Origin)에서도 제공을 해줄 수 있습니다.

![img](https://github.com/user-attachments/assets/2ae448a3-7272-43fe-b4c0-4ead9e21193e)
https://developer.mozilla.org/ko/docs/Web/HTTP/CORS



<br/>
<br/>

## 3. 개발 초점

### 3.1. CORS 모두 전역 허용

```java

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
```

</br>
</br>

### 3.2. CORS 특정 도메인 허용

```java 

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("<http://localhost:3000>")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("Authorization", "x-refresh-token", "Content-Type")
                .exposedHeaders("Authorization", "x-refresh-token")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
```


</br>
</br>

<br/>
<br/>

### 3.3. Controller 내에서 허용
```java
package com.adjh.springbootcors.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : UserController
 * @since : 10/26/24
 */
@Slf4j
@Controller
@RequestMapping("api/v1/user")
@CrossOrigin(origins = "<http://localhost:3001>",
        methods = {RequestMethod.GET, RequestMethod.POST},
        maxAge = 3600,
        allowedHeaders = "*")
public class UserController {
    /**
     * [API] 사용자 리스트 조회
     *
     * @return ResponseEntity
     */

    @PostMapping("/user")
    public ResponseEntity<Object> selectUser() {
        List<Object> resultList = new ArrayList<>();
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    /**
     * [API] 사용자 리스트 조회
     *
     * @return ResponseEntity
     */
    @PostMapping("/login")
    public ResponseEntity<Object> login() {
        Object resultObj = new Object();
        return new ResponseEntity<>(resultObj, HttpStatus.OK);
    }
}
```

![img_1](https://github.com/user-attachments/assets/dcae924e-428c-48fb-a08e-5dfc9e6ba041)

<br/>
<br/>

### 3.4. 특정 Controller 메서드의 엔드포인트에 적용하는 방법

```java
package com.adjh.springbootcors.controller;
@Slf4j
@Controller
@RequestMapping("api/v1/user")
public class UserController {
    /**
     * [API] 사용자 리스트 조회
     *
     * @return ResponseEntity
     */
    @CrossOrigin(origins = "<http://localhost:3000>", allowedHeaders = "*")
    @PostMapping("/user")
    public ResponseEntity<Object> selectUser() {
        List<Object> resultList = new ArrayList<>();
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    /**
     * [API] 사용자 리스트 조회
     *
     * @return ResponseEntity
     */
    @CrossOrigin(origins = "<http://localhost:3001>", allowedHeaders = "*")
    @PostMapping("/login")
    public ResponseEntity<Object> login() {
        Object resultObj = new Object();
        return new ResponseEntity<>(resultObj, HttpStatus.OK);
    }
}
```


![img_2](https://github.com/user-attachments/assets/a387aacd-44ca-43f0-a343-0bc42c288811)

### 3.5. Spring Boot Security 내에서 CORS 적용 방법 : CorsConfigurationSource

```java
@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
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
                .build();
    }

    /**
     * 10. CORS에 대한 설정을 커스텀으로 구성합니다.
     *
     * @return CorsConfigurationSource
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("<http://localhost:3000>"));      // 허용할 오리진
        configuration.setAllowedMethods(List.of("*"));                          // 허용할 HTTP 메서드
        configuration.setAllowedHeaders(List.of("*"));                          // 모든 헤더 허용
        configuration.setAllowCredentials(true);                                    // 인증 정보 허용
        configuration.setMaxAge(3600L);                                             // 프리플라이트 요청 결과를 3600초 동안 캐시
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);             // 모든 경로에 대해 이 설정 적용
        return source;
    }
}

```
