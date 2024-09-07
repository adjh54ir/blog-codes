package com.adjh.springboot3tierform.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 코드 관리 DTO
 *
 * @author : jonghoon
 * @fileName : CodeDto
 * @since : 9/7/24
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "코드 관리 DTO")
public class CodeDto {

    @Schema(description = "코드 시퀀스")
    private int codeSq;

    @Schema(description = "코드")
    private String code;

    @Schema(description = "코드명")
    private String codeNm;

    @Schema(description = "코드 타입")
    private String codeType;

    @Builder(toBuilder = true)
    public CodeDto(int codeSq, String code, String codeNm, String codeType) {
        this.codeSq = codeSq;
        this.code = code;
        this.codeNm = codeNm;
        this.codeType = codeType;
    }
}
