package ru.vittiv.springbootcrud311.service;

import ru.vittiv.springbootcrud311.model.Role;
import ru.vittiv.springbootcrud311.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    void updateUser(User user);

    void deleteUser(Long id);

    User getUserByName(String login);

    Role getRoleByName(String name);

    void addRole(Role role);
}
