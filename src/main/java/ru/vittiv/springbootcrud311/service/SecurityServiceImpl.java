package ru.vittiv.springbootcrud311.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements ru.vittiv.springbootcrud311.service.SecurityService {

//    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

//    @Autowired
//    private UserDetailsService userDetailsService;

    @Override
    public String findLoggedInLogin() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails) userDetails).getUsername();
        }
        return null;
    }
}
