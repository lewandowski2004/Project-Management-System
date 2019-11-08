package com.example.pm.controller;

import com.example.pm.dto.ModifyUserDto;
import com.example.pm.dto.UserDto;
import com.example.pm.service.RoleService;
import com.example.pm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sun.nio.cs.US_ASCII;

import javax.validation.Valid;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    private static final String NEW_USER = "NEW_USER";
    private static final String ROLE_LIST = "ROLE_LIST";
    private static final String USER_LIST = "USER_LIST";
    private static final String ERROR_MESSAGE = "ERROR_MESSAGE";
    private static final String ERROR_MESSAGE_CONTENT = "Email już istnieje w bazie";
    private static final String ID_USER = "ID_USER";
    private static final String NEW_PASSWORD = "NEW_PASSWORD";
    private static final String NEW_PASSWORD_CONTENT = "Nowe hasło";

    @GetMapping("/admin/users")
    public String admin(Model model) {
        model.addAttribute(USER_LIST, userService.findAllWithoutPassword());
        return "/admin/users";
    }

    @GetMapping("/admin/new-user")
    public String newEmployee(Model model) {
        model.addAttribute(NEW_USER, new UserDto());
        model.addAttribute(ROLE_LIST, roleService.findAll());
        return "/admin/new-user";
    }

    @PostMapping("/admin/save-user")
    public String saveEmployee(@Valid @ModelAttribute(NEW_USER) ModifyUserDto newUser, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(NEW_USER, newUser);
            model.addAttribute(ROLE_LIST, roleService.findAll());
            model.addAttribute(USER_LIST, userService.findAllWithoutPassword());
            return "/admin/new-user";
        }
        if (userService.emailIsUnique(newUser)) {
            model.addAttribute(NEW_USER, newUser);
            model.addAttribute(ROLE_LIST, roleService.findAll());
            model.addAttribute(USER_LIST, userService.findAllWithoutPassword());
            model.addAttribute(ERROR_MESSAGE, ERROR_MESSAGE_CONTENT);
            return "/admin/new-user";
        }
        userService.saveOrUpdateEmployee(newUser);
        return "redirect:/admin/users?success";
    }

    @PostMapping("/admin/delete-user")
    public String deleteUser(@RequestParam(ID_USER) Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users?delete-success";
    }

    @GetMapping("/admin/modify-user")
    public String modifyUser(Model model, @RequestParam Long id) {
        ModifyUserDto modifyUserDto = userService.findModifyUserById(id);
        if (modifyUserDto != null) {
            model.addAttribute(NEW_USER, modifyUserDto);
            model.addAttribute(ROLE_LIST, roleService.findAll());
            model.addAttribute(NEW_PASSWORD, NEW_PASSWORD_CONTENT);
            model.addAttribute(USER_LIST, userService.findAllWithoutPasswordAndYourself(modifyUserDto.getId()));
            return "/admin/new-user";
        } else {
            return "redirect:/admin/users";
        }
    }
}
