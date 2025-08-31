package com.eduardoCristea.cv_generator_spring_boot.cvGenerator.service;

import com.eduardoCristea.cv_generator_spring_boot.cvGenerator.model.CvData;

/**
 * Interfaz para el servicio encargado de inicializar los datos de un CV.
 */
public interface CvInitializationService {

    /**
     * Crea y devuelve un objeto CvData con valores predeterminados y listas vacías
     * para ser usado en el formulario.
     * @return Un objeto CvData inicializado.
     */
    CvData initializeCvData();
}