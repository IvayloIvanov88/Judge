package com.example.demo.service;

import com.example.demo.model.entity.HomeworkEntity;
import com.example.demo.model.service.HomeworkServiceModel;

public interface HomeworkService {
    HomeworkServiceModel findByScoring();

    void addHomework(String exercise, String githubAddress);

    HomeworkEntity findById(String homeworkId);
}
