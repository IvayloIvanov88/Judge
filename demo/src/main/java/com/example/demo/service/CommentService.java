package com.example.demo.service;

import com.example.demo.model.service.CommentServiceModel;

import java.util.Map;

public interface CommentService {
    void add(CommentServiceModel commentServiceModel, String homeworkId);

    Double findAvgScore();

    Map<Integer, Integer> findScoreMap();
}
