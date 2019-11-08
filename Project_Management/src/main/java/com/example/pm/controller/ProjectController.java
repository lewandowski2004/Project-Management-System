package com.example.pm.controller;

import com.example.pm.dto.ProjectDtoWithUsers;
import com.example.pm.service.ProjectService;
import com.example.pm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProjectController {

    private static final String PROJECT = "PROJECT";
    private static final String USERS = "USERS";

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @GetMapping("/admin/new-project")
    public String newProject(Model model) {
        model.addAttribute(PROJECT, new ProjectDtoWithUsers());
        model.addAttribute(USERS, userService.findAllWithoutPassword());
        return "/admin/new-project";
    }
}
