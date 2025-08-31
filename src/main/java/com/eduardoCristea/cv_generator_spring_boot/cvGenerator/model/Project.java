package com.eduardoCristea.cv_generator_spring_boot.cvGenerator.model;

import lombok.Data;

@Data
public class Project {
    private String name;
    private String period;
    private String url;
    private String description;
}