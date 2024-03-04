package com.nobroker.Service;

import com.nobroker.Entity.User;
import com.nobroker.Repository.UserRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelExportService {


    @Autowired
    private UserRepository userRepository;

    public byte[] generateExcelFile() throws IOException {

        List<User> userList = userRepository.findAll();

        Workbook workbook = WorkbookFactory.create(true);
        Sheet sheet = workbook.createSheet("Users");

        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Name");
        headerRow.createCell(2).setCellValue("Email");
        headerRow.createCell(3).setCellValue("Mobile");
        headerRow.createCell(4).setCellValue("Email Verified");

        // Fill data rows
        int rowNum = 1;
        for (User user : userList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(user.getId());
            row.createCell(1).setCellValue(user.getName());
            row.createCell(2).setCellValue(user.getEmail());
            row.createCell(3).setCellValue(user.getMobile());
            row.createCell(4).setCellValue(user.isEmailVerified());
        }

        // Write workbook to ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);


        return outputStream.toByteArray();
    }

}
