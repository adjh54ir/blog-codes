package com.blog.springbootexcelpoi.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Please explain the class!!
 *
 * @author : leejonghoon
 * @fileName : ExcelController
 * @since : 2025. 2. 11.
 */
@Slf4j
@Controller
@RequestMapping("/excel")
public class ExcelController {

    /**
     * 엑셀 업로드 화면 출력
     *
     * @return
     */
    @GetMapping("/upload")
    public String uploadForm() {
        return "excelUpload";
    }

    /**
     * 엑셀 업로드 기능 구현
     *
     * @param file
     * @param model
     * @return
     */
    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
        try {
            if (file.isEmpty()) {
                model.addAttribute("message", "파일을 선택해주세요.");
                return "excelUpload";
            }

            // 파일 확장자 검증
            String filename = file.getOriginalFilename();
            if (!filename.endsWith(".xlsx") && !filename.endsWith(".xls")) {
                model.addAttribute("message", "Excel 파일만 업로드 가능합니다.");
                return "excelUpload";
            }

            // Excel 파일 처리
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            log.debug("workbook: {}", workbook);
            Sheet sheet = workbook.getSheetAt(0);
            log.debug("sheetName :: {}", sheet.getSheetName());
            log.debug("row[0] :: {}", sheet.getRow(0));
            log.debug("row[1] :: {}", sheet.getRow(1));
            log.debug("row[2] :: {}", sheet.getRow(2));
            log.debug("row[3] :: {}", sheet.getRow(3));


            workbook.close();
            model.addAttribute("message", "파일이 성공적으로 업로드되었습니다.");

        } catch (IOException e) {
            model.addAttribute("message", "파일 처리 중 오류가 발생했습니다: " + e.getMessage());
        }

        return "excelUpload";
    }
}
