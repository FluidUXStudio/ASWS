package com.Asws.co.config;

import java.lang.reflect.Method;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.Asws.co.service.JwtService;


@Configuration
@EnableWebMvc
@EnableWebSecurity
@EnableGlobalAuthentication
public class WebSecurityConfiguration {

    public static final String[] Public_URLS = {
        "/v3/api-docs",
        "/swagger-resources/**",
        "/swagger-ui/**",
        "/webjars/**",
        "/authenticate",
        "/registerNewUser",
        "/d/**",
        "/demo/**",
        "/attendence/**",
        "/zone/**",
        "/event/**"
        // "/student/**"
    };
    public static final String[] ROLES = {
        "teacher",
        "SuperAdmin"
    };


    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtRequestfilter jwtRequestFilter;

    @Autowired
    private JwtService jwtService;

    // @Bean
    // @Override
    // public AuthenticationManager authenticationManagerBean() throws Exception {
    //     return super.authenticationManagerBean();
    // }
    

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(jwtService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
        

    // @Override
    // protected void configure(HttpSecurity httpSecurity) throws Exception {
    //     httpSecurity.cors();
    //     httpSecurity.csrf().disable()
    //             .authorizeRequests().antMatchers(HttpMethod.POST,"/authenticate", "/registerNewUser","/sd/**").permitAll()
    //           //  .antMatchers(HttpMethod.POST).permitAll()
    //             .antMatchers(HttpHeaders.ALLOW).permitAll()
    //             .anyRequest().authenticated()
    //             .and()
    //             .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
    //             .and()
    //             .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    //     ;

    //     httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    // }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception  {


        http.cors();
        http.csrf().disable()
            .authorizeHttpRequests()
            .antMatchers(HttpMethod.POST,"/admin/**").hasRole("SuperAdmin")
            .antMatchers("/admin/admins").permitAll()
            .antMatchers(Public_URLS).permitAll()
             .antMatchers(HttpMethod.POST,"/student/**","/teacher/**","/center/**").hasRole("SuperAdmin")
            // .antMatchers(HttpMethod.POST,"/student/**","/teacher/**","/center/**").hasRole("Admin")
            .antMatchers("/student/**","/events/**","/syllabus/**","/dashboard/**").hasAnyRole(ROLES)
            .antMatchers("/student/**","/events/**","/syllabus/**","/dashboard/**").hasRole("Supe")
            .antMatchers("/admin","/student/**").hasAnyAuthority("SuperAdmin","Admin")
            .antMatchers("/sd/**").hasRole("Admin")
            .anyRequest().authenticated()
            .and()
            .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            ;
            http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
            http.authenticationProvider(daoAuthenticationProvider());
            SecurityFilterChain sc = http.build();
        return sc;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // @Bean
    // public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //     registry.addResourceHandler("swagger-ui.html")
    //       .addResourceLocations("classpath:/META-INF/resources/");
    
    //     registry.addResourceHandler("/webjars/**")
    //       .addResourceLocations("classpath:/META-INF/resources/webjars/");
    // }

    // @Autowired
    // public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
    //     authenticationManagerBuilder.userDetailsService(jwtService).passwordEncoder(passwordEncoder());
    // }

    
}
