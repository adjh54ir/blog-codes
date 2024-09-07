package com.adjh.springboot3tierform.controller;

import com.adjh.springboot3tierform.model.dto.CodeDto;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 코드를 관리하는 Controller
 *
 * @author : jonghoon
 * @fileName : TestController
 * @since : 9/7/24
 */
@Tag(name = "Code-Controller", description = "Code 관리 API 엔드포인트")
//@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
@RestController
public class CodeController {
    /**
     * 코드 단건 조회
     *
     * @param codeSq
     * @param codeNm
     * @return
     */

    @GetMapping("/code")
    @Operation(summary = "코드 단건 조회", description = "코드 단건을 조회합니다.")
//    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "200", description = "성공",
            content = @Content(schema = @Schema(implementation = CodeDto.class))
    )
    public ResponseEntity<List<CodeDto>> selectCode(
            @RequestParam @Parameter(description = "코드 시퀀스") int codeSq,
            @RequestParam @Parameter(description = "코드 이름") String codeNm
    ) {
        List<CodeDto> temp = new ArrayList<>();
        return new ResponseEntity<>(temp, HttpStatus.OK);
    }


    /**
     * 코드 리스트 조회
     *
     * @param codeDto
     * @return
     */
    @PostMapping("/code")
    @Operation(summary = "코드 리스트 조회", description = "코드 리스트를 조회합니다.")
//    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(
            responseCode = "200",
            description = "성공",
            content = @Content(schema = @Schema(implementation = CodeDto.class))
    )
    public ResponseEntity<CodeDto> selectCodeList(
            @RequestBody @Schema(implementation = CodeDto.class) CodeDto codeDto
    ) {
        CodeDto result = CodeDto.builder().build();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 코드 전체 수정
     *
     * @param codeDto
     * @return
     */
    @PutMapping("/code")
    @Operation(summary = "코드 전체 수정", description = "코드를 전체 수정 합니다.")
//    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "200", description = "성공")
    public ResponseEntity<Integer> updateCode(
            @RequestBody @Schema(implementation = CodeDto.class) CodeDto codeDto
    ) {
        int result = 0;
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 코드 일부 수정
     *
     * @param codeDto
     * @return
     */
    @PatchMapping("/code")
    @Operation(summary = "코드 일부 수정", description = "코드를 일부 수정 합니다.")
//    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "200", description = "성공")
    public ResponseEntity<Integer> patchCode(
            @RequestBody @Schema(implementation = CodeDto.class) CodeDto codeDto
    ) {
        int result = 0;
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 코드 삭제
     *
     * @param codeDto
     * @return
     */
    @DeleteMapping("/code")
    @Operation(summary = "코드 삭제", description = "코드를 삭제합니다.")
//    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "200", description = "성공")
    public ResponseEntity<Integer> deleteCode(
            @RequestBody @Schema(implementation = CodeDto.class) CodeDto codeDto
    ) {
        int result = 0;
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 사용자 코드 조회 (API Document) 제외
     *
     * @param codeDto
     * @return
     */
    @PostMapping("/codes")
    @Hidden
    @Operation(summary = "사용자 코드 조회", description = "사용자 코드를 조회합니다.")
//    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "200", description = "성공")
    public ResponseEntity<Integer> selectUserCode(
            @RequestBody @Schema(implementation = CodeDto.class) CodeDto codeDto
    ) {
        int result = 0;
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
