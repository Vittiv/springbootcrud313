package ru.vittiv.springbootcrud312.dao;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.vittiv.springbootcrud312.model.Role;
import ru.vittiv.springbootcrud312.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

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
////        if (userRoles.containsAll(Role.FULL_SET)) {
////            user.setRoles(Role.FULL_SET);
////        }
//////            // set FULL_SET roles
////            user.setRoleById(3);
////        } else
//        if (userRoles.contains(Role.ROLE_ADMIN)) {
//            // set ADMIN roles
//            user.setRoles(Role.ADMIN);
//        }
//        else if(userRoles.contains(Role.ROLE_USER)){
//            // set USER roles
//            user.setRoles(Role.USER);
//        }
////        else user.setRoleById(2);

        entityManager.merge(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = getUserById(id);
        entityManager.remove(user);
    }

    @Override
    public User getUserByName(String username) {
        try {
            return entityManager.createQuery("from User where username = '" + username + "'", User.class).getSingleResult();
        }catch (NoResultException e){
            return getUserById(1L);
        }
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
