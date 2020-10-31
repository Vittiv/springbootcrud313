package ru.vittiv.springbootcrud312.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vittiv.springbootcrud312.model.User;
import ru.vittiv.springbootcrud312.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class RestAdminController {

    @Autowired
    private UserService userService;

//    @GetMapping()
//    public List<User> getUsersList() {
//        List<User> usersList = userService.getAllUsers();
//        return usersList;
//    }

    @GetMapping
    public String getUsers() {
//        Gson gson = new Gson();
//        String json = gson.toJson(userService.getAllUsers());
        return getJson(userService.getAllUsers());
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

    @GetMapping("/new")
    public String addNewUser(@RequestBody User user) {
        userService.updateUser(user);
        List<User> usersList = userService.getAllUsers();
        return getJson(usersList);
    }
    @PutMapping("/edit")
    public String editUser(@RequestBody User user) {
        userService.updateUser(user);
        List<User> usersList = userService.getAllUsers();
        return getJson(usersList);
    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestBody User user) {
        userService.deleteUser(user.getId());
        List<User> usersList = userService.getAllUsers();
        return getJson(usersList);
    }
}
