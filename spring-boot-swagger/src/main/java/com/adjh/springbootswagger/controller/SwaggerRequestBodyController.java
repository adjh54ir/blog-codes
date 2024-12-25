package com.adjh.springbootswagger.controller;

import com.adjh.springbootswagger.dto.CodeDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger 테스트를 위해 구성한 Controller
 */
@Tag(name = "Swagger-Controller", description = "Swagger Parameter 관리 API 엔드포인트")
@RestController
@RequestMapping("/api/v1/swagger")
public class SwaggerRequestBodyController {
    /**
     * 어노테이션 @RequestBody 형태로 전달받은 JSON 데이터에 대해 Swagger 정의: @io.swagger.v3.oas.annotations.parameters.RequestBody 활용
     *
     * @return
     */
    @PostMapping("/requestBody2")
    @Operation(
            summary = "swagger.@RequestBody 활용예시",
            description = "swagger.@RequestBody + @RequestBody 활용한 예시입니다."
    )  // Swagger API 메서드 정의
    @ApiResponse(responseCode = "200", description = "성공")                                              // Swagger 응답값 반환
    public ResponseEntity<List<CodeDto>> selectCodeList2(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "@RequestBody를 위한 요청 데이터",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CodeDto.class),
                            examples = {
                                    @ExampleObject(
                                            name = "기본 예시",
                                            value = """
                                                    {
                                                        "codeSq": 1,
                                                        "code": "CODE001",
                                                        "codeNm": "테스트 코드",
                                                        "codeType": "TYPE_A"
                                                    }
                                                    """
                                    )
                            }
                    )
            )
            CodeDto codeDto
    ) {
        List<CodeDto> temp = new ArrayList<>();
        return new ResponseEntity<>(temp, HttpStatus.OK);
    }


}