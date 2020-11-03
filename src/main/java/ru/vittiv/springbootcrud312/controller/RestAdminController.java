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
        //This tells GSON look for @Expose, could be change to @Transient on Role.users field
        GsonBuilder builder = new GsonBuilder().disableHtmlEscaping();
        builder.excludeFieldsWithoutExposeAnnotation();
        Gson gson = builder.create();
        String json = gson.toJson(object);
        return json;
    }

    @GetMapping("/{id}")
    public String getUser(@RequestBody User user) {
        return getJson(userService.getUserById(user.getId()));
    }

    @PostMapping("/new")
    public String addNewUser(@RequestBody User user) {
        userService.updateUser(user);
        return getJson(user);
    }
    @PutMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        userService.updateUser(user);
        return getJson(user);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return getJson(userService.getUserById(id));
    }
}
