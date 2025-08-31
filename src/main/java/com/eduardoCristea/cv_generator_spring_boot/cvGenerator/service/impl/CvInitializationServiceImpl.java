package com.eduardoCristea.cv_generator_spring_boot.cvGenerator.service.impl;

import com.eduardoCristea.cv_generator_spring_boot.cvGenerator.model.*;
import com.eduardoCristea.cv_generator_spring_boot.cvGenerator.service.CvInitializationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class CvInitializationServiceImpl implements CvInitializationService {

    @Override
    public CvData initializeCvData() {
        CvData cvData = new CvData();

        cvData.setPersonalDetails(new PersonalDetails());

        cvData.setExperiences(new ArrayList<>(Collections.singletonList(new Experience())));
        cvData.setEducations(new ArrayList<>(Collections.singletonList(new Education())));
        cvData.setCertifications(new ArrayList<>(Collections.singletonList(new Certification())));
        cvData.setProjects(new ArrayList<>(Collections.singletonList(new Project())));
        cvData.setSkills(new ArrayList<>(Collections.singletonList(new Skill())));
        cvData.setLanguages(new ArrayList<>(Collections.singletonList(new Language())));

        return cvData;
    }
}