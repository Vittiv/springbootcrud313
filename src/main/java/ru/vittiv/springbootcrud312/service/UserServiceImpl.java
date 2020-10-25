package ru.vittiv.springbootcrud312.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vittiv.springbootcrud312.dao.UserDao;
import ru.vittiv.springbootcrud312.model.Role;
import ru.vittiv.springbootcrud312.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Transactional
    @Override
    public void updateUser(User user) {

        userDao.updateUser(user);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    @Transactional
    @Override
    public User getUserByName(String username) {
        return userDao.getUserByName(username);
    }

    @Transactional
    @Override
    public Role getRoleByName(String name) {

        return userDao.getRoleByName(name);
    }

    @Transactional
    @Override
    public void addRole(Role role) {
        userDao.addRole(role);
    }

}
