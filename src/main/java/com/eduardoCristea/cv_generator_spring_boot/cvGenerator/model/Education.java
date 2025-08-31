package com.eduardoCristea.cv_generator_spring_boot.cvGenerator.model;

import lombok.Data;

@Data
public class Education {
    private String institution;
    private String degree;
    private String fieldOfStudy;
    private String period;
    private String description;
}