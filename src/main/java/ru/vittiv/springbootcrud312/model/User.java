package ru.vittiv.springbootcrud312.model;

import com.google.gson.annotations.Expose;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private Long id;

    @Column
    @Expose
    private String username;

    @Column
    @Expose
    private String password;

    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private int age;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User(String username, String password, String firstName, String lastName, int age, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.roles = roles;
    }
    @Expose
    @ManyToMany(cascade=CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Role> roles;

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(Long id, Set<Role> roles) {
        this.id = id;
        this.roles = roles;
    }
    public User(Long id, String username, String password)
    {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(Long id, String username, String password, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public User(String username, String password, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String login) {
        this.username = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }


    public String getRolesString() {
        return getAuthorities().stream().map(r -> r.getAuthority().substring(5) + " ")
                .collect(Collectors.joining());
    }

    public void setRoles(Set<Role> roles) {
//        if (roles.contains(Role.ROLE_ADMIN)){
//            if (roles.contains(Role.ROLE_USER)) roles = Role.FULL_SET;
//            else roles = new HashSet((Collection) Role.ROLE_ADMIN);
//        }else if (roles.contains(Role.ROLE_USER)) roles = Role.USER;
        this.roles = roles;
    }

//    public void setRoleById(int id) {

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }
//        switch (id){
//            case 1: this.setRoles(Role.USER);
//            case 2: this.setRoles(Role.ADMIN);
//            case 3: this.setRoles(Role.FULL_SET);
//            default: this.setRoles(Role.GUEST);
//        }
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles(); //roles
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
