package ru.vittiv.springbootcrud312.dao;

import ru.vittiv.springbootcrud312.model.Role;
import ru.vittiv.springbootcrud312.model.User;

import java.util.List;

public interface UserDao{

    List<User> getAllUsers();

    User getUserById(Long id);

    void updateUser(User user);

    void deleteUser(Long id);

    User getUserByName(String login);

    Role getRoleByName(String name);

    void addRole(Role role);
}
