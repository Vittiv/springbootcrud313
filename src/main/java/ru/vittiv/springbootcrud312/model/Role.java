package ru.vittiv.springbootcrud312.model;

import com.google.gson.annotations.Expose;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Этот класс реализует интерфейс GrantedAuthority, в котором необходимо переопределить только один метод getAuthority() (возвращает имя роли).
// Имя роли должно соответствовать шаблону: «ROLE_ИМЯ», например, ROLE_USER

@Entity
@Table
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Expose
     Long id;

    @Column(unique = true)
    @Expose
     String name;

//    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;


    public static final Role ROLE_ADMIN = new Role(1,"ROLE_ADMIN");
    public static final Role ROLE_USER = new Role(2, "ROLE_USER");
    public static final Role ROLE_GUEST = new Role(4,"ROLE_GUEST");


    public static final Set<Role> FULL_SET = Stream.of(ROLE_USER, ROLE_ADMIN)
                                              .collect(Collectors.toCollection(HashSet::new));
    public static final Set<Role> ADMIN = Stream.of(ROLE_ADMIN)
            .collect(Collectors.toCollection(HashSet::new));
    public static final Set<Role> USER = Stream.of(ROLE_USER)
            .collect(Collectors.toCollection(HashSet::new));
    public static final Set<Role> GUEST = Stream.of(ROLE_GUEST)
            .collect(Collectors.toCollection(HashSet::new));

    public Role() {
    }

    public Role(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {

//        if (getName().equals(ROLE_ADMIN.name)){ this.id = 1L;}
//        else if (getName().equals(ROLE_USER.name)){ this.id = 2L;}
        this.id = id;
        }


    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String getAuthority() {
        return name;
    }

    @Override
    public String toString() {
        return getAuthority();
    }
}
