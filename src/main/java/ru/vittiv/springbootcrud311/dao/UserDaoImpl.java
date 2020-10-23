package ru.vittiv.springbootcrud311.dao;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.vittiv.springbootcrud311.model.Role;
import ru.vittiv.springbootcrud311.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List getAllUsers() {
        return entityManager.createQuery("from User").getResultList();
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.createQuery("from User where id = '" + id + "'", User.class).getSingleResult();
    }

    @Override
    public void updateUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

//        Set<Role> userRoles = user.getRoles();
//        if (userRoles.containsAll(Role.FULL_SET)) {
//            // set FULL_SET roles
//            user.setRoleById(3);
//        } else if (userRoles.contains(Role.ROLE_ADMIN)) {
//            // set ADMIN roles
//            user.setRoleById(2);
//        }else if(userRoles.contains(Role.ROLE_USER)){
//            // set USER roles
//            user.setRoleById(1);
//        } else user.setRoleById(2);

        entityManager.merge(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = getUserById(id);
        entityManager.remove(user);
    }

    @Override
    public User getUserByName(String username) {
        return entityManager.createQuery("from User where username = '" + username + "'", User.class).getSingleResult();
    }

    @Override
    public Role getRoleByName(String name) {

        return entityManager.createQuery("from Role where name = '" + name + "'", Role.class).getSingleResult();
    }

    @Override
    public void addRole(Role role) {

        entityManager.persist(role);
    }

}
