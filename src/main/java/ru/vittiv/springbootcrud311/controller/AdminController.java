package ru.vittiv.springbootcrud311.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.vittiv.springbootcrud311.model.Role;
import ru.vittiv.springbootcrud311.model.User;
import ru.vittiv.springbootcrud311.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

//    private User user;

    @GetMapping(value = "/users")
    public String getUsers(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/users";
    }

    @GetMapping("/dashboard")
    public String dashboard(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/dashboard";
    }

    @GetMapping(value = "/hello")
    public String printUserInfo(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", userService.getUserByName(auth.getName()));

        return "hello";
    }

    //
    @GetMapping("/newUser")
    public String getUser() {
        return "admin/addUser";
    }

    @PostMapping("/create")
    public String createNewUser(@ModelAttribute User user,
                                @RequestParam("role") String[] roles) {
        Set<Role> roleSet = new HashSet<>();
        for (String role : roles) {
            roleSet.add(userService.getRoleByName(role));
        }
        user.setRoles(roleSet);
        userService.updateUser(user);
        return "redirect:users";
    }

    //
    @GetMapping("/edit")
    public String editPage(@RequestParam("id") Long id, ModelMap model){
        model.addAttribute("user", userService.getUserById(id));
        return "admin/editUser";
    }

    @PostMapping("/editSave")
    public String editUser(Model model,
                           @RequestParam("id") Long id,
                           @RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("role") String[] role){
        Set<Role> roleSet = new HashSet<>();
        for (String roles : role) {
            roleSet.add(userService.getRoleByName(roles));
        }
        userService.updateUser(new User(id, username, password, roleSet ));
        return "redirect:users";
    }

    //
    @GetMapping("/delete")
    public String deleteUser(@RequestParam(value = "id") String id) {
        Long userId = Long.parseLong(id);
        userService.deleteUser(userId);
        return "redirect:users";
    }
}

