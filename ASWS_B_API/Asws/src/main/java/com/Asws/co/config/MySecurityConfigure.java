// package com.Asws.co.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// import com.Asws.co.service.Impl.UserDetailServiceImpl;

// @Configuration
//@EnableWebSecurity
// public class MySecurityConfigure extends WebSecurityConfigurerAdapter{


    // @Bean
    // public UserDetailsService userDetailsService() {
    //     return new UserDetailServiceImpl();
    // }
     
    // @Bean
    // public BCryptPasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }
     
    // @Bean
    // public DaoAuthenticationProvider authenticationProvider() {
    //     DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    //     authProvider.setUserDetailsService(userDetailsService());
    //     authProvider.setPasswordEncoder(passwordEncoder());
         
    //     return authProvider;
    // }
 
    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //     auth.authenticationProvider(authenticationProvider());
    // }
 
    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    //     http.authorizeRequests()
    //         .antMatchers("/api/**").permitAll()
    //         .antMatchers("/new").hasAnyAuthority("ADMIN", "CREATOR")
    //         .antMatchers("/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
    //         .antMatchers("/delete/**").hasAuthority("ADMIN")
    //         .anyRequest().authenticated()
    //         .and()
    //         .formLogin().permitAll()
    //         .and()
    //         .logout().permitAll()
    //         .and()
    //         .exceptionHandling().accessDeniedPage("/403")
    //         ;
    // }
    



    
// }
