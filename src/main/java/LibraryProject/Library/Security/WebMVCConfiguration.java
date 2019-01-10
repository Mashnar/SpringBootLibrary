package LibraryProject.Library.Security;

import LibraryProject.Library.DB.CRUD.RoleRepository;
import LibraryProject.Library.DB.CRUD.UserRepository;
import LibraryProject.Library.DB.Role;
import LibraryProject.Library.DB.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import java.util.Arrays;
import java.util.HashSet;

@Configuration
public class WebMVCConfiguration implements WebMvcConfigurer {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private  RoleRepository roleRepository;
    private UserRepository userRepository;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }




}