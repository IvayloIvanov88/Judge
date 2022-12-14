package com.example.demo.web;

import com.example.demo.security.CurrentUser;
import com.example.demo.service.CommentService;
import com.example.demo.service.ExerciseService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final CurrentUser currentUser;
    private final ExerciseService exerciseService;
    private final UserService userService;
    private final CommentService commentService;

    @Autowired
    public HomeController(CurrentUser currentUser, ExerciseService exerciseService, UserService userService, CommentService commentService) {
        this.currentUser = currentUser;
        this.exerciseService = exerciseService;
        this.userService = userService;
        this.commentService = commentService;
    }

    @GetMapping("/")
    private String index(Model model) {
        if (currentUser.isAnonymous()) {
            return "index";
        }

        model.addAttribute("exercises", exerciseService.findAllExerciseNames());
        model.addAttribute("students", userService.findAllUsernames());
        model.addAttribute("avg", commentService.findAvgScore());
        model.addAttribute("userCount", userService.findUserCount());
        model.addAttribute("scoreMap", commentService.findScoreMap());


        return "home";
    }

}
