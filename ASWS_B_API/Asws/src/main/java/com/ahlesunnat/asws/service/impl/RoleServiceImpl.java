package com.ahlesunnat.asws.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.ahlesunnat.asws.domain.Role;
import com.ahlesunnat.asws.repository.RoleRepository;
import com.ahlesunnat.asws.service.RoleService;

public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository rolesRepository;

    @Override
    public Role createnewRole(Role obj) {

        Role rl = rolesRepository.save(obj);
        return rl;
    }

    // @Override
    // public List<Role> getAllRoles() {
    //     return rolesRepository.findAll();
    // }
    
}