package com.example.demo.service.impl;

import com.example.demo.model.entity.ExerciseEntity;
import com.example.demo.model.service.ExerciseServiceModel;
import com.example.demo.repository.ExerciseRepository;
import com.example.demo.service.ExerciseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, ModelMapper modelMapper) {
        this.exerciseRepository = exerciseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void add(ExerciseServiceModel exerciseServiceModel) {
        exerciseRepository.saveAndFlush(modelMapper.map(exerciseServiceModel, ExerciseEntity.class));
    }

    @Override
    public List<String> findAllExerciseNames() {
        return
                exerciseRepository
                        .findAllExerciseNames();
    }

    @Override
    public boolean checkIfIsLate(String exercise) {
        ExerciseEntity exerciseEntity = findByName(exercise);


        return exerciseEntity != null
                && exerciseEntity.getDueDate().isBefore(LocalDateTime.now());
    }

    @Override
    public ExerciseEntity findByName(String exercise) {
        return exerciseRepository.findByName(exercise).orElse(null);
    }
}
