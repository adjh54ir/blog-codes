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

    /**
     * 엑셀 업로드를 수행
     *
     * @param file
     * @param model
     * @return
     */
    public String excelUpload(MultipartFile file, Model model) {
        // 파일 정보 출력
        log.debug("파일명: {}", file.getOriginalFilename());
        log.debug("파일 크기: {} bytes", file.getSize());

        try {
            if (file.isEmpty()) {
                model.addAttribute("message", "파일을 선택해주세요.");
                return "/pages/excelUpload";
            }

            // 파일 확장자 검증
            String filename = file.getOriginalFilename();
            if (!filename.endsWith(".xlsx") && !filename.endsWith(".xls")) {
                model.addAttribute("message", "Excel 파일만 업로드 가능합니다.");
                return "/pages/excelUpload";
            }

            List<UserDto> userDtoList = new ArrayList<>();

            // Excel 파일 처리
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);

            // 헤더 행 읽기
            Row headerRow = sheet.getRow(0);
            for (Cell cell : headerRow) {
                log.debug("{}\\t", cell.getStringCellValue());
            }
            // 데이터 행 읽기
            for (int i = 1; i <= 10; i++) {
                Row row = sheet.getRow(i);

                // 행 들을 리스트 객체로 구성
                userDtoList.add(UserDto.builder()
                        .number((int) row.getCell(0).getNumericCellValue()) // 순번
                        .name(row.getCell(1).getStringCellValue())          // 이름
                        .age((int) row.getCell(2).getNumericCellValue())    // 나이
                        .gender(row.getCell(3).getStringCellValue())        // 성별
                        .contact(row.getCell(4).getStringCellValue())       // 연락처
                        .build());
            }
            log.debug("구성한 리스트 객체 :: {}", userDtoList.toString());
            log.debug("=================================================");

            workbook.close();
            model.addAttribute("message", "파일이 성공적으로 업로드되었습니다.");
        } catch (IOException e) {
            model.addAttribute("message", "파일 처리 중 오류가 발생했습니다: " + e.getMessage());
        }
        return "/pages/excelUpload";
    }

}
