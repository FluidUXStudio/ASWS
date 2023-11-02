package com.ahlesunnat.asws.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ahlesunnat.asws.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User,String>{    
     
    // @Query("SELECT u FROM User u WHERE u.username = :username")
    // public User getUserByUsername(@Param("username") String username);

    // public Optional<User> findById(String userName);
    
}
