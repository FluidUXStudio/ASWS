package com.ahlesunnat.asws.config;

import com.ahlesunnat.asws.security.*;
import com.ahlesunnat.asws.service.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableGlobalAuthentication
public class SecurityConfiguration {

    public static final String[] Public_URLS = {
            "/v3/api-docs",
            "/docs",
            "/management",
            "/admin/docs",
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/api/swagger-ui/**",
            "/swagger-ui/**",
            "/webjars/**",
            "/authenticate",
            "/registerNewUser",
            "/registerNewTeacher",
            "/d/**",
            "/api-docs",
            "/demo/**",
            "/attendence/**",
            "/api/zones",
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
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private JwtService jwtService;

    // @Bean
    // @Override
    // public AuthenticationManager authenticationManagerBean() throws Exception {
    // return super.authenticationManagerBean();
    // }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(jwtService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    // @Override
    // protected void configure(HttpSecurity httpSecurity) throws Exception {
    // httpSecurity.cors();
    // httpSecurity.csrf().disable()
    // .authorizeRequests().antMatchers(HttpMethod.POST,"/authenticate",
    // "/registerNewUser","/sd/**").permitAll()
    // // .antMatchers(HttpMethod.POST).permitAll()
    // .antMatchers(HttpHeaders.ALLOW).permitAll()
    // .anyRequest().authenticated()
    // .and()
    // .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
    // .and()
    // .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    // ;

    // httpSecurity.addFilterBefore(jwtRequestFilter,
    // UsernamePasswordAuthenticationFilter.class);
    // }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.cors();
        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/admin/**").hasRole("SuperAdmin")
                .requestMatchers("/api/zones/{zoneId}/centers/{centerId}/students/**", "/users", "/api/teacher/**",
                        "/api/students/attendance/**", "/api/testing","/api/events", "/api/subjects/**", "/api/studentPerformance/**",
                        "/api/studentPerformance","/api/{studentId}/subject/{subjectId}/chapters/{chapterId}/testing")
                .hasRole("teacher")
                .requestMatchers("/api/zones/{zoneId}/centers/{centerId}/students/**", "/users", "/api/teacher/**",
                        "/api/students/attendance/**", "/api/events", "/api/subjects/**", "/api/studentPerformance/**",
                        "/api/studentPerformance")
                .hasRole("Admin")
                .requestMatchers("/api/zones/{zoneId}/centers/{centerId}/students/**", "/users", "/api/teacher/**",
                        "/api/students/attendance/**", "/api/events", "/api/subjects/**", "/api/studentPerformance/**",
                        "/api/studentPerformance")
                .hasRole("SuperAdmin")

                // .requestMatchers(HttpMethod.POST,"/api/zs").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/zones").hasRole("teacher")
                .requestMatchers(Public_URLS).permitAll()
                .requestMatchers(HttpMethod.POST, "/student/**", "/teacher/**", "/center/**").hasRole("SuperAdmin")
                // .antMatchers(HttpMethod.POST,"/student/**","/teacher/**","/center/**").hasRole("Admin")
                .requestMatchers("/student/**", "/events/**", "/syllabus/**", "/dashboard/**").hasAnyRole(ROLES)
                .requestMatchers("/student/**", "/events/**", "/syllabus/**", "/dashboard/**").hasRole("Supe")
                .requestMatchers("/admin", "/student/**").hasAnyAuthority("SuperAdmin", "Admin")
                .requestMatchers("/sd/**").hasRole("Admin")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
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
    // registry.addResourceHandler("swagger-ui.html")
    // .addResourceLocations("classpath:/META-INF/resources/");

    // registry.addResourceHandler("/webjars/**")
    // .addResourceLocations("classpath:/META-INF/resources/webjars/");
    // }

    // @Autowired
    // public void configureGlobal(AuthenticationManagerBuilder
    // authenticationManagerBuilder) throws Exception {
    // authenticationManagerBuilder.userDetailsService(jwtService).passwordEncoder(passwordEncoder());
    // }

}