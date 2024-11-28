package com.adjh.springboot3tierform.controller;

import com.adjh.springboot3tierform.model.dto.CodeDto;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
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
@RestController
@RequestMapping("/api/v1/code")
public class CodeController {

    /**
     * 두 개의 파라미터가 있는 API를 정의합니다-1 : @Parameter 활용
     *
     * @param codeSq
     * @param codeNm
     * @return
     */
    @GetMapping("/code")
    @Operation(summary = "@Parameter 활용예시", description = "@Parameter 활용한 예시입니다.")          // Swagger API 메서드 정의
    @ApiResponse(responseCode = "200", description = "성공")                                      // Swagger 응답값 반환
    public ResponseEntity<List<CodeDto>> selectCode(
            @RequestParam @Parameter(description = "코드 시퀀스", required = true) int codeSq,      // Swagger 파리미터 정의
            @RequestParam @Parameter(description = "코드 이름", required = true) String codeNm      // Swagger 파라미터 정의
    ) {
        List<CodeDto> temp = new ArrayList<>();
        return new ResponseEntity<>(temp, HttpStatus.OK);
    }

    /**
     * 두 개의 파라미터가 있는 API를 정의합니다-2 : @Parameters 활용
     *
     * @param codeSq
     * @param codeNm
     * @return
     */
    @GetMapping("/code2")
    @Operation(summary = "@Parameters 활용예시", description = "@Parameters 활용예시입니다.")  // Swagger API 메서드 정의
    @ApiResponse(responseCode = "200", description = "성공")                              // Swagger 응답값 반환
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
     * PathVariable 있는 API를 정의합니다-2 : @Parameters 활용
     *
     * @return
     */
    @PostMapping("/code/{codeSq}")
    @Operation(summary = "코드 리스트 조회", description = "코드 리스트를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    public ResponseEntity<List<CodeDto>> selectCode3(@PathVariable int codeSq) {
        List<CodeDto> temp = new ArrayList<>();
        return new ResponseEntity<>(temp, HttpStatus.OK);
    }


    /**
     * 코드 전체 수정
     *
     * @param codeDto
     * @return
     */
    @PutMapping("/code")
    @Operation(summary = "코드 전체 수정", description = "코드를 전체 수정 합니다.")
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
    @ApiResponse(responseCode = "200", description = "성공")
    public ResponseEntity<Integer> selectUserCode(
            @RequestBody @Schema(implementation = CodeDto.class) CodeDto codeDto
    ) {
        int result = 0;
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
