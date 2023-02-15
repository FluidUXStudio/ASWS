package com.Asws.co.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Asws.co.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User,String>{    
     
    // @Query("SELECT u FROM User u WHERE u.username = :username")
    // public User getUserByUsername(@Param("username") String username);

    // public Optional<User> findById(String userName);
    
}
