package com.Asws.co.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Asws.co.domain.User;
import com.Asws.co.service.Impl.UserImpl;

@RestController
public class UserController {


    private static final Logger logger = LoggerFactory.getLogger(UserController.class);




    @Autowired
    private UserImpl userImpl;

    @PostConstruct
    public void initRoleAndUser() {
        userImpl.initRoleAndUser();
    }

    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user) {
        logger.info("register new User");
        return userImpl.registerNewUser(user);
    }

    @PostMapping("/admin")
	public ResponseEntity<User> createadmin(@RequestBody User obj){
        logger.info("creating new admin");
		User us = userImpl.registerNewAdmin(obj);
		return ResponseEntity.status(HttpStatus.OK).body(us);
	}

    @GetMapping("/users")
	public ResponseEntity<List<User>> getAllUser() {
		List<User> list = userImpl.getAllUSers();
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

    @PostMapping("/user")
	public ResponseEntity<User> createuser(@RequestBody User obj){
		User us = userImpl.createUser(obj);
		return ResponseEntity.status(HttpStatus.OK).body(us);
	}
    
}
