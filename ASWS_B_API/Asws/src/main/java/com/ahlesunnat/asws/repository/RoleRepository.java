package com.ahlesunnat.asws.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahlesunnat.asws.domain.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

    Role findByName(String name);

}
