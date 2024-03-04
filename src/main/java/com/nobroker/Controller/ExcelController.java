package com.nobroker.Controller;

import com.nobroker.Entity.User;
import com.nobroker.Repository.UserRepository;
import com.nobroker.Service.ExcelExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;


@RestController
@RequestMapping("/api/excel")
public class ExcelController {
    @Autowired
    private ExcelExportService excelExportService;


    /* http://localhost:8080/api/excel/download  */
    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadUsersExcel() throws IOException {

        byte[] excelBytes = excelExportService.generateExcelFile();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "users.xlsx");

        return ResponseEntity.ok().headers(headers).body(excelBytes);
    }
}
