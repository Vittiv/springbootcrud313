package ru.vittiv.springbootcrud312.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vittiv.springbootcrud312.model.User;
import ru.vittiv.springbootcrud312.service.UserService;

@RestController
@RequestMapping("/user/restFront")
public class RestUserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String userForPage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByName(auth.getName());
//        User recievedUser = new Gson().fromJson(gson, User.class);

        return getJson(user);
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
}
