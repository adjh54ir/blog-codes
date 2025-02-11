package com.blog.springbootexcelpoi;

import com.blog.springbootexcelpoi.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 엑셀 처리를 위한 서비스
 *
 * @author : leejonghoon
 * @fileName : ExcelService
 * @since : 2025. 2. 11.
 */
@Slf4j
@Service
public class ExcelService {

    /**
     * 엑셀 다운로드
     *
     * @param file
     * @param model
     * @return
     */
    public Resource excelDownload(Model model) {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("사용자 정보");

        // 헤더 행 생성
        Row headerRow = sheet.createRow(0);
        String[] headers = {"순번", "이름", "나이", "성별", "연락처"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // 데이터 행 생성
        Object[][] data = {
                {1, "한국인", 35, "남", "010-0000-0000"},
                {2, "박원희", 11, "여", "010-1234-0000"},
                {3, "이국한", 23, "여", "010-5678-0000"},
                {4, "김명희", 27, "여", "010-9010-0000"},
                {5, "김철민", 29, "남", "010-8888-0000"},
        };

        // Sheet 내에 헤더 / 데이터 행 구성
        for (int i = 0; i < data.length; i++) {
            Row row = sheet.createRow(i + 1);
            for (int j = 0; j < data[i].length; j++) {
                Cell cell = row.createCell(j);

                // 문자 처리
                if (data[i][j] instanceof String) {
                    cell.setCellValue((String) data[i][j]);
                }
                // 숫자 처리
                if (data[i][j] instanceof Integer) {
                    cell.setCellValue((Integer) data[i][j]);
                }
            }
        }

        // 열 너비 자동 조정
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // 파일 생성
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());
        model.addAttribute("downloadMessage", "다운로드가 완료되었습니다.");
        return resource;

    }

}
