package com.eduardoCristea.cv_generator_spring_boot.cvGenerator.service.impl;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.eduardoCristea.cv_generator_spring_boot.cvGenerator.service.PdfGenerationService;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PdfGenerationServiceImpl implements PdfGenerationService {

    private final TemplateEngine templateEngine;

    public PdfGenerationServiceImpl() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");

        this.templateEngine = new TemplateEngine();
        this.templateEngine.setTemplateResolver(templateResolver);
    }

    @Override
    public byte[] generatePdfFromCvData(Object cvData) throws IOException {
        Context context = new Context();
        context.setVariable("cvData", cvData);

        String htmlContent = templateEngine.process("cv-template", context);

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withHtmlContent(htmlContent, null);
            builder.toStream(outputStream);
            builder.run();
            return outputStream.toByteArray();
        }
    }
}