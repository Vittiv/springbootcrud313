package ru.vittiv.springbootcrud312.controller;

import com.google.gson.Gson;
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
        Gson gson = new Gson();
        String json = gson.toJson(userService.getAllUsers());
        return json;
    }
    private String getJson(Object object) {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        return json;
    }


    @GetMapping("/{id}")
    public User getUser(@RequestBody User user) {
        return userService.getUserById(user.getId());
    }

    @GetMapping("/new")
    public List<User> addNewUser(@RequestBody User user) {
        userService.updateUser(user);
        List<User> usersList = userService.getAllUsers();
        return usersList;
    }
    @PutMapping("/edit")
    public List<User> editUser(@RequestBody User user) {
        userService.updateUser(user);
        List<User> usersList = userService.getAllUsers();
        return usersList;
    }

    @DeleteMapping("/delete")
    public List<User> deleteUser(@RequestBody User user) {
        userService.deleteUser(user.getId());
        List<User> usersList = userService.getAllUsers();
        return usersList;
    }
}
