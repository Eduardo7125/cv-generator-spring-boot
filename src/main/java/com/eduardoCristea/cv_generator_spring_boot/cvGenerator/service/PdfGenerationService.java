package com.eduardoCristea.cv_generator_spring_boot.cvGenerator.service;

import java.io.IOException;

/**
 * Interfaz para el servicio de generación de PDFs.
 */
public interface PdfGenerationService {

    /**
     * Genera un archivo PDF a partir de un objeto de datos del CV.
     * @param cvData El objeto que contiene toda la información del CV.
     * @return Un array de bytes que representa el archivo PDF.
     * @throws IOException Si hay un error al procesar la plantilla o el stream.
     * @throws DocumentException Si hay un error durante la creación del documento PDF.
     */
    byte[] generatePdfFromCvData(Object cvData) throws IOException;
}