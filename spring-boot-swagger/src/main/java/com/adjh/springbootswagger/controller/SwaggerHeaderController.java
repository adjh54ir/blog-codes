package com.adjh.springbootswagger.controller;


import com.adjh.springbootswagger.dto.CodeDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger Header 테스트를 위해 구성한 Controller
 */
@Tag(name = "Swagger-Controller", description = "Swagger Parameter 관리 API 엔드포인트")
@RestController
@RequestMapping("/api/v1/swagger")
public class SwaggerHeaderController {

    /**
     * 어노테이션 @PathVariable 전달된 파라미터에 대해 Swagger 정의 : @Parameters 활용
     *
     * @return
     */
    @GetMapping("/header")
    @Operation(summary = "@Parameters 활용예시", description = "@RequestHeader 활용예시입니다.")  // Swagger API 메서드 정의
    @ApiResponse(responseCode = "200", description = "성공")                                 // Swagger 응답값 반환
    // Swagger 파리미터들 일괄 정의
    @Parameters({
            @Parameter(in = ParameterIn.HEADER, name = "x-api-key", description = "API 인증 키"),
            @Parameter(in = ParameterIn.HEADER, name = "authToken", description = "Bearer 인증 토큰")
    })
    public ResponseEntity<List<CodeDto>> addHeader(
            @RequestHeader(value = "x-api-key") String apiKey,
            @RequestHeader(value = "authToken") String authToken
    ) {
        List<CodeDto> temp = new ArrayList<>();
        return new ResponseEntity<>(temp, HttpStatus.OK);
    }
}
