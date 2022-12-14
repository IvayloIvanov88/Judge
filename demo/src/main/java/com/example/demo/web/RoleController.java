package com.example.demo.web;

import com.example.demo.model.entity.enumeration.RoleName;
import com.example.demo.security.CurrentUser;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/roles")
public class RoleController {

    private final CurrentUser currentUser;
    private final UserService userService;

    public RoleController(CurrentUser currentUser, UserService userService) {
        this.currentUser = currentUser;
        this.userService = userService;
    }

    @GetMapping("/change")
    private String change(Model model) {

        if (currentUser.isAnonymous()) {
            return "redirect:/users/login";
        }

        model.addAttribute("names", userService.findAllUsernames());
        return "role-change";
    }

    @PostMapping("/change")
    private String changeConfirm(@RequestParam String username,
                                 @RequestParam String role) {

        userService.changeRole(username, RoleName.valueOf(role.toUpperCase()));

        return "redirect:/";
    }
}
