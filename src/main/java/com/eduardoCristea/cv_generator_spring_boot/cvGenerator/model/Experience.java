package com.eduardoCristea.cv_generator_spring_boot.cvGenerator.model;

import lombok.Data;

@Data
public class Experience {
    private String jobTitle;
    private String companyName;
    private String location;
    private String period;
    private String description;
}