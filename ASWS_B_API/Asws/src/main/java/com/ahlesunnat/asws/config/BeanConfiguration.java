package com.ahlesunnat.asws.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import com.ahlesunnat.asws.service.JwtService;
import com.ahlesunnat.asws.service.impl.AttendenceServiceImpl;
import com.ahlesunnat.asws.service.impl.RoleServiceImpl;
import com.ahlesunnat.utils.JwtUtils;


@Configuration
public class BeanConfiguration {


    // @Bean
    // public TokenProvider tokenProvider(){
    //     return new TokenProvider();
    // }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        return userDetailsManager;
    }

    @Bean
    public JwtUtils jwtUtils(){
        return new JwtUtils();
    }

    @Bean
    public JwtService jwtService(){
        return new JwtService();
    }

    @Bean
    public RoleServiceImpl roleServiceImpl(){
        return new RoleServiceImpl();
    }

    @Bean
    public AttendenceServiceImpl attendenceServiceImpl(){
        return new AttendenceServiceImpl();
    }
    // @Bean
    // public JsonbConverter jsonbConverter(){
    //     return new JsonbConverter();
    // }



    
}
