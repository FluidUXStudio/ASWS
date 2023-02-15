package com.Asws.co.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Asws.co.domain.Teacher;



@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long>{
    
}
