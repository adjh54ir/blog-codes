package com.blog.springbootexcelpoi;

import com.blog.springbootexcelpoi.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
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


    /**
     * 엑셀 템플릿 다운로드
     *
     * @param model
     * @return
     */
    public Resource downloadExcelTemplate(Model model) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("사용자 등록 정보");

        // 엑셀 시트 생성
        Row headerRow = sheet.createRow(0);

        // 헤더 행 높이 지정
        headerRow.setHeight((short) 600);

        // 헤더 구성
        String[] headers = {"사용자 번호", "사용자 이름", "사용자 관계", "사용자 연락처(예: 010-0000-0000)"};

        // 테두리와 배경색을 모두 포함하는 헤더 스타일 생성
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 수직 가운데 정렬
        headerStyle.setAlignment(HorizontalAlignment.CENTER); // 수평 가운데 정렬

        // 헤더 값 및 스타일 적용
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

        // 열 너비 자동 조정
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
            // 기본 너비에 약간의 여유 공간 추가
            int currentWidth = sheet.getColumnWidth(i);
            sheet.setColumnWidth(i, (int) (currentWidth * 1.5));
        }

        // 나머지 셀들을 위한 테두리 스타일
        CellStyle borderStyle = workbook.createCellStyle();
        borderStyle.setBorderTop(BorderStyle.THIN);
        borderStyle.setBorderBottom(BorderStyle.THIN);
        borderStyle.setBorderLeft(BorderStyle.THIN);
        borderStyle.setBorderRight(BorderStyle.THIN);

        // 모든 셀에 테두리 적용
        for (int row = 0; row <= 50; row++) {
            Row currentRow = sheet.getRow(row);
            if (currentRow == null) {
                currentRow = sheet.createRow(row);
            }
            for (int col = 0; col < headers.length; col++) {
                Cell cell = currentRow.getCell(col);
                if (cell == null) {
                    cell = currentRow.createCell(col);
                }
                if (row != 0) {  // 첫 번째 행(헤더)가 아닌 경우에만 borderStyle 적용
                    cell.setCellStyle(borderStyle);
                }
            }
        }
        DataValidationHelper dvHelper = sheet.getDataValidationHelper();

        /*
         * 1. "사용자 번호" 유효성 검증
         */
        CellStyle attendanceStyle = workbook.createCellStyle();
        DataFormat attendanceFormat = workbook.createDataFormat();
        attendanceStyle.setDataFormat(attendanceFormat.getFormat("0000"));      // 0 허용 추가

        // 테두리 설정 추가
        attendanceStyle.setBorderTop(BorderStyle.THIN);
        attendanceStyle.setBorderBottom(BorderStyle.THIN);
        attendanceStyle.setBorderLeft(BorderStyle.THIN);
        attendanceStyle.setBorderRight(BorderStyle.THIN);

        // 사용자 번호 열에 형식 적용
        for (int row = 1; row <= 50; row++) {
            Cell attendanceCell = sheet.getRow(row).getCell(0);
            attendanceCell.setCellStyle(attendanceStyle);
        }

        // 숫자만 입력 가능하도록 유효성 검증 추가
        CellRangeAddressList addressList = new CellRangeAddressList(1, 50, 0, 0);
        DataValidationConstraint constraint = dvHelper.createNumericConstraint(
                DataValidationConstraint.ValidationType.INTEGER,
                DataValidationConstraint.OperatorType.BETWEEN,
                "0",
                "99999999"
        );
        DataValidation validation = dvHelper.createValidation(constraint, addressList);
        validation.setShowErrorBox(true);
        validation.setErrorStyle(DataValidation.ErrorStyle.STOP);
        validation.createErrorBox("입력 오류", "숫자만 입력이 가능합니다.");
        sheet.addValidationData(validation);


//
//
        /*
         * 2. "사용자 관계" 유효성 검증
         */
        CellRangeAddressList relationAddressList = new CellRangeAddressList(1, 50, 2, 2);
        String[] relationships = {"본인", "모", "부", "조모", "조부"};
        DataValidationConstraint relationConstraint = dvHelper.createExplicitListConstraint(relationships);
        DataValidation relationValidation = dvHelper.createValidation(relationConstraint, relationAddressList);
        relationValidation.setShowErrorBox(true);
        sheet.addValidationData(relationValidation);

        // 사용자 연락처 셀 형식 설정
        CellStyle phoneStyle = workbook.createCellStyle();
        DataFormat phoneFormat = workbook.createDataFormat();
        phoneStyle.setDataFormat(phoneFormat.getFormat("000-0000-0000")); // 전화번호 형식 적용

        // 테두리 설정 추가
        phoneStyle.setBorderTop(BorderStyle.THIN);
        phoneStyle.setBorderBottom(BorderStyle.THIN);
        phoneStyle.setBorderLeft(BorderStyle.THIN);
        phoneStyle.setBorderRight(BorderStyle.THIN);

        /*
         * 4. 사용자 연락처 유효성 검증 추가
         */
        CellRangeAddressList phoneAddressList = new CellRangeAddressList(1, 50, 3, 3);
        DataValidationConstraint phoneConstraint = dvHelper.createTextLengthConstraint(
                DataValidationConstraint.OperatorType.EQUAL,
                "13", // 000-0000-0000 형식의 길이
                null
        );
        DataValidation phoneValidation = dvHelper.createValidation(phoneConstraint, phoneAddressList);
        phoneValidation.setShowErrorBox(true);
        phoneValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
        phoneValidation.createErrorBox("입력 오류", "올바른 전화번호 형식(000-0000-0000)으로 입력해주세요.");
        sheet.addValidationData(phoneValidation);

        // 사용자 연락처 열에 형식 적용
        for (int row = 1; row <= 50; row++) {
            Cell phoneCell = sheet.getRow(row).getCell(3);
            phoneCell.setCellStyle(phoneStyle);
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
