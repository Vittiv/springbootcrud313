package ru.vittiv.springbootcrud312.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.vittiv.springbootcrud312.model.Role;
import ru.vittiv.springbootcrud312.model.User;
import ru.vittiv.springbootcrud312.service.UserService;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

//    private User user;

//    @GetMapping(value = "/users")
//    public String getUsers(ModelMap model) {
//        model.addAttribute("users", userService.getAllUsers());
//        return "admin/users";
//    }

    @GetMapping("/dashboard")
    public String dashboard(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)authentication.getPrincipal();
        String userRoles = user.getAuthorities().stream().map(r -> r.getAuthority().substring(5) + " ").collect(Collectors.joining());
        model.addAttribute("userRoles", userRoles);
//        model.addAttribute("users", userService.getAllUsers());
        return "admin/restFront/dashboard";
    }

    @GetMapping(value = "/hello")
    public String printUserInfo(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", userService.getUserByName(auth.getName()));

        return "old_hello";
    }

    //
    @GetMapping("/newUser")
    public String getUser(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)authentication.getPrincipal();
        String userRoles = user.getAuthorities().stream().map(r -> r.getAuthority().substring(5) + " ").collect(Collectors.joining());
        model.addAttribute("userRoles", userRoles);

        return "admin/newUser";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") Long id, ModelMap model){
        model.addAttribute("user", userService.getUserById(id));
        return "admin/editUser";
    }

//    @PostMapping("/editSave")
//    public String editUser(@ModelAttribute User user,
//                           @RequestParam("role") String[] role
//                           ) {
//        // TODO Перенести в модель User, создав конструктор для String[] role
//        Set<Role> roleSet = new HashSet<>();
//        for (String roles : role) {
//            roleSet.add(userService.getRoleByName(roles));
//        }
//        user.setRoles(roleSet);
//        userService.updateUser(user);
//        return "redirect:dashboard";
//    }

    //
    @PostMapping("/delete")
    public String deleteUser(@RequestParam(value = "id") String id) {
        Long userId = Long.parseLong(id);
        userService.deleteUser(userId);
        return "redirect:dashboard";
    }

    @GetMapping("/delete/{id}")
    public String deleteUserForm(@PathVariable("id") Long id, Model model){

        User user = userService.getUserById(id);
        model.addAttribute("user", user);

        return "admin/delete";
    }

    @GetMapping("/updates/{id}")
    public String updateUsers(@PathVariable("id") Long id, Model model){
        User user = userService.getUserById(id);
        model.addAttribute("user", user);

        return "admin/myForm";
    }
}