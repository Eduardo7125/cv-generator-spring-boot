package com.eduardoCristea.cv_generator_spring_boot.cvGenerator.service;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

/**
 * Interfaz para el servicio de almacenamiento de archivos.
 * Define las operaciones necesarias para guardar y gestionar archivos.
 */
public interface FileStorageService {

    /**
     * Guarda un archivo subido en el sistema de archivos.
     *
     * @param file El archivo MultipartFile recibido desde el formulario.
     * @return El nombre único del archivo guardado.
     * @throws IOException Si ocurre un error al guardar el archivo.
     */
    String storeFile(MultipartFile file) throws IOException;
}