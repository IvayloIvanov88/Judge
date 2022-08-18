package com.example.demo.service;

import com.example.demo.model.entity.ExerciseEntity;
import com.example.demo.model.service.ExerciseServiceModel;

import java.util.List;

public interface ExerciseService {
    void add(ExerciseServiceModel exerciseServiceModel);

    List<String> findAllExerciseNames();

    boolean checkIfIsLate(String exercise);

    ExerciseEntity findByName(String exercise);
}
