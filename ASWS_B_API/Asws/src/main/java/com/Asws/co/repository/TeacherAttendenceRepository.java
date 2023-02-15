package com.Asws.co.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Asws.co.domain.TeacherAttendence;

@Repository
public interface TeacherAttendenceRepository extends JpaRepository<TeacherAttendence,Long>{
    
}
