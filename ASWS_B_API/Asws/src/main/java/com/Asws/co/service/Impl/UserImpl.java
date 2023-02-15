package com.Asws.co.service.Impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Asws.co.domain.Role;
import com.Asws.co.domain.User;
import com.Asws.co.repository.RolesRepository;
import com.Asws.co.repository.UserRepository;
import com.Asws.co.service.UserService;

@Service
public class UserImpl implements UserService{


    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User obj) {
        User us = userRepository.save(obj);
        return us;
    }

    @Override
    public List<User> getAllUSers() {
        return userRepository.findAll();
    }

    public void initAdminRole(){}

    public void initRoleAndUser() {

        
        //super Admin
        Role superadminRole = new Role();
        superadminRole.setName("SuperAdmin");
        rolesRepository.save(superadminRole);


        User superadminUser = new User();
        superadminUser.setUsername("super");
        superadminUser.setPassword(getEncodedPassword("super@pass"));
        Set<Role> superadminRoles = new HashSet<>();
        superadminRoles.add(superadminRole);
        superadminUser.setRoles(superadminRoles);
        userRepository.save(superadminUser);

         //teacher
         Role teacherRole = new Role();
         teacherRole.setName("teacher");
         rolesRepository.save(teacherRole);

         User teacherUser = new User();
         teacherUser.setUsername("teacher");
         teacherUser.setPassword(getEncodedPassword("teacher@pass"));
         Set<Role> teacherRoles = new HashSet<>();
         teacherRoles.add(teacherRole);
         teacherUser.setRoles(teacherRoles);
         userRepository.save(teacherUser);

         //admin
        Role adminRole = new Role();
        adminRole.setName("Admin");
        rolesRepository.save(adminRole);

        //user
        Role userRole = new Role();
        userRole.setName("User");
        //userRole.setRoleDescription("Default role for newly created record");
        rolesRepository.save(userRole);

        User adminUser = new User();
        adminUser.setUsername("umran");
        adminUser.setPassword(getEncodedPassword("admin@pass"));
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRoles(adminRoles);
        userRepository.save(adminUser);

//        User user = new User();
//        user.setUserName("raj123");
//        user.setUserPassword(getEncodedPassword("raj@123"));
//        user.setUserFirstName("raj");
//        user.setUserLastName("sharma");
//        Set<Role> userRoles = new HashSet<>();
//        userRoles.add(userRole);
//        user.setRole(userRoles);
//        userDao.save(user);
    }


    //register new user
    public User registerNewUser(User user) {
        Role role = rolesRepository.findById("User").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRoles(userRoles);
        user.setPassword(getEncodedPassword(user.getPassword()));

        return userRepository.save(user);
    }


    //for creating admin
    public User registerNewAdmin(User user) {
        Role role = rolesRepository.findById("Admin").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRoles(userRoles);
        user.setPassword(getEncodedPassword(user.getPassword()));

        return userRepository.save(user);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
    
}
