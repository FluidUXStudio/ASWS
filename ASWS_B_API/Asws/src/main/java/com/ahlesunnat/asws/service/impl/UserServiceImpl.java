package com.ahlesunnat.asws.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ahlesunnat.asws.domain.Role;
import com.ahlesunnat.asws.domain.Teacher;
import com.ahlesunnat.asws.domain.User;
import com.ahlesunnat.asws.repository.RoleRepository;
import com.ahlesunnat.asws.repository.TeacherRepository;
import com.ahlesunnat.asws.repository.UserRepository;
import com.ahlesunnat.asws.service.UserService;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private TeacherRepository teacherRepository;


    @Autowired
    private RoleRepository rolesRepository;

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
      //register new user
    public User registerNewUser(User user) {
        Role role = rolesRepository.findById("User").orElseThrow(() -> new NoSuchElementException("Role "+ " not found"));
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRoles(userRoles);
        user.setPassword(getEncodedPassword(user.getPassword()));

        return userRepository.save(user);
    }

        public User registerNewTeacher(User user) {
        System.out.println(user.getUsername());
        Role role = rolesRepository.findById("teacher").orElseThrow(() -> new NoSuchElementException("Role "+ " not found"));
            
        List<Teacher> t = getTeachersByEmail(user.getUsername());
        System.out.println(t);
        if(!t.isEmpty()){
            
            Set<Role> userRoles = new HashSet<>();
            userRoles.add(role);
            user.setRoles(userRoles);
            user.setPassword(getEncodedPassword(user.getPassword()));
            userRepository.save(user);
        }else{
            System.out.println("Your email is not registerd as a teacher");
        }
        
        return user;
    }

    //for creating admin
    public User registerNewAdmin(User user) {
        Role role = rolesRepository.findById("Admin").orElseThrow(() -> new NoSuchElementException("Role "+ " not found"));
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRoles(userRoles);
        user.setPassword(getEncodedPassword(user.getPassword()));

        return userRepository.save(user);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }


    public List<Teacher> getTeachersByEmail(String email) {
        List<Teacher> teachers = teacherRepository.findAll();
        List<Teacher> filteredTeachers = teachers.stream()
                .filter(teacher -> doesTeacherDetailsContainEmail(teacher, email))
                .collect(Collectors.toList());
        
        return filteredTeachers;
    }

    private boolean doesTeacherDetailsContainEmail(Teacher teacher, String email) {
        return Optional.ofNullable(teacher.getTeacher_details())
                .map(details -> details.get("email"))
                .map(String::valueOf)
                .map(email::equals)
                .orElse(false);
    }
    
}
