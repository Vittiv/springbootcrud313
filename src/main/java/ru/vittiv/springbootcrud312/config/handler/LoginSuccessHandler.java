package ru.vittiv.springbootcrud312.config.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ru.vittiv.springbootcrud312.dao.UserDao;
import ru.vittiv.springbootcrud312.dao.UserDaoImpl;
import ru.vittiv.springbootcrud312.model.User;
import ru.vittiv.springbootcrud312.service.UserDetailsServiceImpl;
import ru.vittiv.springbootcrud312.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private UserDaoImpl userDao;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());


        if (roles.contains("ROLE_ADMIN")) {
            httpServletResponse.sendRedirect("http://localhost:8080/admin/dashboard");
        } else if (roles.contains("ROLE_USER")) {
            httpServletResponse.sendRedirect("http://localhost:8080/user/dashboard");
        } else {
            httpServletResponse.sendRedirect("http://localhost:8080/hello");
        }
    }
}
