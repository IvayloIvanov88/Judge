package com.example.demo.service.impl;

import com.example.demo.model.entity.HomeworkEntity;
import com.example.demo.model.service.HomeworkServiceModel;
import com.example.demo.repository.HomeworkRepository;
import com.example.demo.security.CurrentUser;
import com.example.demo.service.ExerciseService;
import com.example.demo.service.HomeworkService;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HomeworkServiceImpl implements HomeworkService {
    private final HomeworkRepository homeworkRepository;
    private final ModelMapper modelMapper;
    private final ExerciseService exerciseService;
    private final CurrentUser currentUser;
    private final UserService userService;

    @Autowired
    public HomeworkServiceImpl(HomeworkRepository homeworkRepository, ModelMapper modelMapper, ExerciseService exerciseService, CurrentUser currentUser, UserService userService) {
        this.homeworkRepository = homeworkRepository;
        this.modelMapper = modelMapper;
        this.exerciseService = exerciseService;
        this.currentUser = currentUser;
        this.userService = userService;
    }

    @Override
    public HomeworkServiceModel findByScoring() {
        return homeworkRepository
                .findTop1ByOrderByCommentEntitySet()
                .map(h -> modelMapper.map(h, HomeworkServiceModel.class)).orElseThrow();
    }

    @Override
    public void addHomework(String exercise, String githubAddress) {
        HomeworkEntity homeworkEntity = new HomeworkEntity();

        homeworkEntity.setGithubAddress(githubAddress);
        homeworkEntity.setAddedOn(LocalDateTime.now());
        homeworkEntity.setExerciseEntity(exerciseService.findByName(exercise));
        homeworkEntity.setUser(userService.findById(currentUser.getId()));
        homeworkRepository.saveAndFlush(homeworkEntity);
    }

    @Override
    public HomeworkEntity findById(String homeworkId) {
        return homeworkRepository.findById(homeworkId).orElseThrow();
    }
}
