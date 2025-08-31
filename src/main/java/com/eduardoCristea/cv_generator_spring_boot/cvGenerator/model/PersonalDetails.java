package com.eduardoCristea.cv_generator_spring_boot.cvGenerator.model;

import lombok.Data;

@Data
public class PersonalDetails {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String province;
    private String postalCode;
    private String jobTitle;
    private String professionalProfile;
    private String linkedinUrl;
    private String githubUrl;
    private String portfolioUrl;
    private String profilePicturePath;
}