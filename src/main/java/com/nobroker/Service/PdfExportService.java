package com.nobroker.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.nobroker.Entity.User;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class PdfExportService {

    public byte[] exportToPdf(List<User> userList) throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, byteArrayOutputStream);

        document.open();

        for (User user : userList) {
            document.add(new Paragraph("User ID: " + user.getId()));
            document.add(new Paragraph("Name: " + user.getName()));
            document.add(new Paragraph("Email: " + user.getEmail()));
            document.add(new Paragraph("Mobile: " + user.getMobile()));
            document.add(new Paragraph("Password: " + user.getPassword()));
            document.add(new Paragraph("Email Verified: " + user.isEmailVerified()));
            document.add(new Paragraph("\n"));
        }

        document.close();
        return byteArrayOutputStream.toByteArray();
    }
}
