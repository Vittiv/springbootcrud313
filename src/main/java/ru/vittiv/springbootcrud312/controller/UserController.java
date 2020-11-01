package ru.vittiv.springbootcrud312.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vittiv.springbootcrud312.model.User;
import ru.vittiv.springbootcrud312.service.UserService;

import java.util.stream.Collectors;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/hello")
    public String printUserInfo(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByName(auth.getName());
        model.addAttribute("user", user);
        model.addAttribute("userRole", user.getRolesString());
        return "hello";
    }
    private String getJson(Object object) {
        GsonBuilder builder = new GsonBuilder().disableHtmlEscaping();
        builder.excludeFieldsWithoutExposeAnnotation();  //<-- This tells GSON look for @Expose
        Gson gson = builder.create();
        String json = gson.toJson(object);
        return json;
    }
    @GetMapping("/{id}")
    public String getUser(@RequestBody User user) {

        return getJson(userService.getUserById(user.getId()));
    }
    @GetMapping("/dashboard")
    public String dashboard(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)authentication.getPrincipal();
        String userRoles = user.getAuthorities().stream().map(r -> r.getAuthority().substring(5) + " ").collect(Collectors.joining());
        model.addAttribute("userRoles", userRoles);
//        model.addAttribute("users", userService.getAllUsers());
        return "user/restFront/dashboard";
    }
}