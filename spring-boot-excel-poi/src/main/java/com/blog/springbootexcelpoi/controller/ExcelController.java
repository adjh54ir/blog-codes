package com.blog.springbootexcelpoi.controller;

import com.blog.springbootexcelpoi.ExcelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 엑셀 처리를 관리하는 Controller
 *
 * @author : leejonghoon
 * @fileName : ExcelController
 * @since : 2025. 2. 11.
 */
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/excel")
public class ExcelController {

    private final ExcelService excelService;

    /**
     * 엑셀 업로드 화면 출력
     *
     * @return
     */
    @GetMapping("/upload")
    public String excelUploadPage() {
        return "/pages/excelUpload";
    }

    /**
     * 엑셀 다운로드 화면 출력
     *
     * @return
     */
    @GetMapping("/download")
    public String excelDownloadPage() {
        return "/pages/excelDownload";
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
        String result = excelService.excelUpload(file, model);
        return result;
    }

    /**
     * 엑셀 다운로드 기능 구현
     *
     * @return
     * @throws IOException
     */
    @PostMapping("/download")
    public ResponseEntity<Resource> downloadExcel(Model model) {

        Resource result = excelService.excelDownload(model);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=userList.xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(result);
    }


    /**
     * 엑셀 템플릿 다운로드
     *
     * @param model
     * @return
     */
    @PostMapping("/template-download")
    public ResponseEntity<Resource> downloadExcelTemplate(Model model) {
        Resource resource = excelService.downloadExcelTemplate(model);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=사용자 등록(양식).xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(resource);
    }


}
