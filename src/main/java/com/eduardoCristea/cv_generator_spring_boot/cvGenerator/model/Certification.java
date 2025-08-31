package com.eduardoCristea.cv_generator_spring_boot.cvGenerator.model;

import lombok.Data;

@Data
public class Certification {
    private String name;
    private String issuingOrganization;
    private String issueDate;
    private String credentialId;
    private String credentialUrl;
}