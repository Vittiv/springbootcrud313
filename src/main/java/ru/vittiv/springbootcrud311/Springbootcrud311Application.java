package ru.vittiv.springbootcrud311;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.vittiv.springbootcrud311.model.Role;

@SpringBootApplication
public class Springbootcrud311Application {

    public static void main(String[] args) {
        SpringApplication.run(Springbootcrud311Application.class, args);
    }

//        Role admin = new Role(1,"ROLE_ADMIN");
//        Role user = new Role(2, "ROLE_USER");

}
