package com.eduardoCristea.cv_generator_spring_boot.cvGenerator.controller;

import com.eduardoCristea.cv_generator_spring_boot.cvGenerator.model.CvData;
import com.eduardoCristea.cv_generator_spring_boot.cvGenerator.service.CvInitializationService;
import com.eduardoCristea.cv_generator_spring_boot.cvGenerator.service.FileStorageService;
import com.eduardoCristea.cv_generator_spring_boot.cvGenerator.service.PdfGenerationService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class CvController {

    private final CvInitializationService cvInitializationService;
    private final PdfGenerationService pdfGenerationService;
    private final FileStorageService fileStorageService;

    @GetMapping("/")
    public String home(HttpSession session) {
        session.removeAttribute("cvData");
        return "redirect:/cv-form";
    }

    @GetMapping("/cv-form")
    public String cvForm(Model model) {
        if (!model.containsAttribute("cvData")) {
            model.addAttribute("cvData", cvInitializationService.initializeCvData());
        }
        return "cv-form";
    }

    @PostMapping("/preview-cv")
    public String previewCv(@ModelAttribute CvData cvData,
                            @RequestParam("profileImage") MultipartFile profileImage,
                            HttpSession session) throws IOException {

        if (!profileImage.isEmpty()) {
            String fileName = fileStorageService.storeFile(profileImage);
            cvData.getPersonalDetails().setProfilePicturePath(fileName);
        }

        session.setAttribute("cvData", cvData);

        return "redirect:/cv-preview";
    }

    @GetMapping("/cv-preview")
    public String cvPreview(Model model, HttpSession session) {
        CvData cvData = (CvData) session.getAttribute("cvData");

        if (cvData == null) {
            return "redirect:/cv-form";
        }
        model.addAttribute("cvData", cvData);
        return "cv-template";
    }

    @GetMapping("/download-pdf")
    public ResponseEntity<byte[]> downloadPdf(HttpSession session) {
        CvData cvData = (CvData) session.getAttribute("cvData");

        if (cvData == null) {
            return ResponseEntity.status(404)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE)
                .body("No se encontraron datos del CV para generar el PDF.".getBytes());
        }

        try {
            byte[] pdfBytes = pdfGenerationService.generatePdfFromCvData(cvData);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            String filename = "CV_" + cvData.getPersonalDetails().getFirstName() + "_" + cvData.getPersonalDetails().getLastName() + ".pdf";
            headers.setContentDispositionFormData("attachment", filename);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            return ResponseEntity.ok().headers(headers).body(pdfBytes);

        } catch (Exception e) {
            return ResponseEntity.status(500)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE)
                .body("Error al generar el PDF.".getBytes());
        }
    }
}