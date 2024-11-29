package com.adjh.springboot3tierform.controller;

import com.adjh.springboot3tierform.model.dto.CodeDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger 테스트를 위해 구성한 Controller
 */
@Tag(name = "Swagger-Controller", description = "Swagger Parameter 관리 API 엔드포인트")
@RestController
@RequestMapping("/api/v1/swagger")
public class SwaggerParamController {
    /**
     * 어노테이션 @RequestParam으로 전달된 파라미터에 대해 Swagger 정의: @Parameter 활용
     *
     * @param codeSq
     * @param codeNm
     * @return
     */
    @GetMapping("/parameter")
    @Operation(summary = "@Parameter 활용예시", description = "@RequestParam + @Parameter 활용한 예시입니다.")  // Swagger API 메서드 정의
    @ApiResponse(responseCode = "200", description = "성공")                                              // Swagger 응답값 반환
    public ResponseEntity<List<CodeDto>> selectCode(
            @RequestParam @Parameter(description = "코드 시퀀스", required = true) int codeSq,               // Swagger 파리미터 정의
            @RequestParam @Parameter(description = "코드 이름", required = true) String codeNm              // Swagger 파라미터 정의
    ) {
        List<CodeDto> temp = new ArrayList<>();
        return new ResponseEntity<>(temp, HttpStatus.OK);
    }

    /**
     * 어노테이션 @RequestParam으로 전달된 파라미터에 대해 Swagger 정의: @Parameters 활용
     *
     * @param codeSq
     * @param codeNm
     * @return
     */
    @GetMapping("/parameters")
    @Operation(summary = "@Parameters 활용예시", description = "@RequestParam + @Parameters 활용예시입니다.")  // Swagger API 메서드 정의
    @ApiResponse(responseCode = "200", description = "성공")                                              // Swagger 응답값 반환
    // Swagger 파리미터들 일괄 정의
    @Parameters({
            @Parameter(name = "codeSq", required = true, description = "코드 시퀀스"),
            @Parameter(name = "codeNm", required = true, description = "코드 이름")
    })
    public ResponseEntity<List<CodeDto>> selectCode2(@RequestParam int codeSq, @RequestParam String codeNm) {
        List<CodeDto> temp = new ArrayList<>();
        return new ResponseEntity<>(temp, HttpStatus.OK);
    }


    /**
     * 어노테이션 @PathVariable 전달된 파라미터에 대해 Swagger 정의 : @Parameters 활용
     *
     * @return
     */
    @GetMapping("/{codeSq}/{codeNm}")
    @Operation(summary = "@Parameters 활용예시", description = "@PathVariable + @Parameters 활용예시입니다.")  // Swagger API 메서드 정의
    @ApiResponse(responseCode = "200", description = "성공")                                              // Swagger 응답값 반환
    // Swagger 파리미터들 일괄 정의
    @Parameters({
            @Parameter(name = "codeSq", description = "코드 시퀀스", required = true, example = "1"),
            @Parameter(name = "codeNm", description = "코드 이름", required = true, example = "HA264")
    })
    public ResponseEntity<List<CodeDto>> selectCode3(@PathVariable int codeSq, @PathVariable String codeNm) {
        List<CodeDto> temp = new ArrayList<>();
        return new ResponseEntity<>(temp, HttpStatus.OK);
    }
}
