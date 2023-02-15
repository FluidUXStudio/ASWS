package com.Asws.co.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Asws.co.domain.demo;
import com.Asws.co.domain.demoImages;


@Repository
public interface demoRepository extends JpaRepository<demo,String>{

    
    
}
