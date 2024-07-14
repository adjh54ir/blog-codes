package com.adjh.springboottest.service;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : CodeService
 * @since : 6/16/24
 */


import com.adjh.multiflexapi.model.CodeDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CodeService {

    // 코드 리스트 조회
    List<CodeDto> selectCodeList(CodeDto codeDto);

    // 코드 단건 조회
    CodeDto selectCodeByCd(String cd);

    // 코드 생성
    int insertCode(CodeDto codeDto);

    // 코드 수정
    int updateCode(CodeDto codeDto);

    // 코드 삭제
    int deleteCode(CodeDto codeDto);

}
