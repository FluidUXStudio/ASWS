// package com.ahlesunnat.asws.service.impl;

// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.security.core.userdetails.UserDetails;

// import com.ahlesunnat.asws.repository.UserRepository;
// import com.ahlesunnat.asws.service.UserDetailService;

// public class UserDetailsServiceImpl implements UserDetailService{

    

   
//     private final UserRepository userRepository;

//     public UserDetailsServiceImpl(UserRepository userRepository) {
//         this.userRepository = userRepository;
//     }

//     @Override
//     public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//         // Find the user by email in the database
//         com.ahlesunnat.asws.domain.User user = userRepository.findByEmail(email)
//                 .orElseThrow(() -> new UsernameNotFoundException("User not found"));

//         // You can return a UserDetails object based on your User entity
//         // For example, you can use the User class from Spring Security
//         return org.springframework.security.core.userdetails.User.builder()
//                 .username(user.getUserName()) // Use email as the username
//                 .password(user.getPassword())
//                 .roles(user.getRoles().toArray(new String[0])) // Convert roles to an array of strings
//                 .build();
//     }


// }
