package com.eduardoCristea.cv_generator_spring_boot.cvGenerator.model;

import lombok.Data;
import java.util.List;

@Data
public class CvData {
    private PersonalDetails personalDetails;
    private List<Experience> experiences;
    private List<Education> educations;
    private List<Certification> certifications;
    private List<Project> projects;
    private List<Skill> skills;
    private List<Language> languages;
}