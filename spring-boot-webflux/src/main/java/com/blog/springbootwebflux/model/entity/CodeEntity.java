package com.blog.springbootwebflux.model.entity;

import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : CodeEntity
 * @since : 24. 12. 24.
 */
@Getter
@ToString
@Table("tb_code")                                   // 실제 테이블명 지정
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CodeEntity {

    private String grpCd;        // 그룹 코드
    private String cd;           // 코드
    private String grpCdNm;      // 그룹 코드명
    private String cdNm;         // 코드명
    private Integer sortOrder;    // 정렬 순서
    private LocalDateTime regDt;  // 등록일시
    private Boolean useYn;       // 사용여부

    @Builder(toBuilder = true)
    public CodeEntity(String grpCd, String cd, String grpCdNm, String cdNm, Integer sortOrder, LocalDateTime regDt, Boolean useYn) {
        this.grpCd = grpCd;
        this.cd = cd;
        this.grpCdNm = grpCdNm;
        this.cdNm = cdNm;
        this.sortOrder = sortOrder;
        this.regDt = regDt;
        this.useYn = useYn;
    }
}
