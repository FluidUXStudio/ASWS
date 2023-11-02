package com.ahlesunnat.asws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ahlesunnat.asws.domain.Teacher;


@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query(value = "SELECT * FROM Teacher t WHERE JSON_VALUE(t.teacher_details, '$.email') = :email", nativeQuery = true)
    Teacher findByEmail(@Param("email") String email);    
    
    List<Teacher> findByZoneIdAndCenterId(Long zoneId, Long centerId);

    Teacher findByZoneIdAndCenterIdAndId(Long zoneId, Long centerId, Long teacherId);

    void deleteByZoneIdAndCenterIdAndId(Long zoneId, Long centerId, Long teacherId);

}
