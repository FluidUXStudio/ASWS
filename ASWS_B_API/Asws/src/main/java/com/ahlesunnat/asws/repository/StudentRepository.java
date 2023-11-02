package com.ahlesunnat.asws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ahlesunnat.asws.domain.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByZoneIdAndCenterId(Long zoneId, Long centerId);

    List<Student> findAllByZoneIdAndCenterIdAndTeacherId(Long zoneId, Long centerId, Long teacherId);

    Student findByZoneIdAndCenterIdAndId(Long zoneId, Long centerId, Long studentId);

    void deleteByZoneIdAndCenterIdAndId(Long zoneId, Long centerId, Long studentId);

}
