package com.ahlesunnat.asws.service;

import java.util.List;

import com.ahlesunnat.asws.domain.User;


public interface UserService {

    public User createUser(User obj);

    public List<User> getAllUSers();

}