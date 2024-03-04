package com.nobroker.Controller;

import com.itextpdf.text.DocumentException;
import com.nobroker.Entity.User;
import com.nobroker.Repository.UserRepository;
import com.nobroker.Service.PdfExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/pdf")
public class PdfExportController {
   @Autowired
   private PdfExportService pdfExportService;
   @Autowired
   private UserRepository userRepository;

    /* http://localhost:8080/api/pdf/export */
    @GetMapping("/export")
    public void exportToPdf(HttpServletResponse response) throws DocumentException, IOException {
        // Assuming you have a service or repository to get the user data
        List<User> userList =userRepository.findAll();

        byte[] pdfBytes = pdfExportService.exportToPdf(userList);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=users.pdf");
        response.getOutputStream().write(pdfBytes);
        response.getOutputStream().flush();
    }
}
