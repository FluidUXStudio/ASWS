package com.ahlesunnat.asws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ahlesunnat.asws.domain.Student;

@Repository
public interface TestingRepository extends JpaRepository<Student,Long>{
    
}
