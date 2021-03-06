package ru.vittiv.springbootcrud312.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.vittiv.springbootcrud312.model.User;
import ru.vittiv.springbootcrud312.service.UserService;


@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {

        return "login";
    }
    @GetMapping("/registration")
    public String registrationPage(){
        return "registration";
    }

    @PostMapping("/create")
    public String createNewUser(@ModelAttribute User user) {
        userService.updateUser(user);
        return "redirect:/user/hello";
    }

}

