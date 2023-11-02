package com.ahlesunnat.asws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ahlesunnat.asws.domain.Teacher;
import com.ahlesunnat.asws.domain.TeacherAttendence;


@Repository
public interface TeacherAttendeceRepository extends JpaRepository<TeacherAttendence,Long>{
    TeacherAttendence findByTeacherAndCheckOutTimeIsNull(Teacher teacher);

}
