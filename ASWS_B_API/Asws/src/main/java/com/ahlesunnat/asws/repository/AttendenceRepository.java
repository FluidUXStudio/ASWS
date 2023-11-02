package com.ahlesunnat.asws.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ahlesunnat.asws.domain.Attendence;

@Repository
public interface AttendenceRepository extends JpaRepository<Attendence, Long> {
    // List<Attendence> findAllByStudentId(Long studentId);
    List<Attendence> findAllByAttendanceDate(LocalDate attendanceDate);
    // List<Attendence> findAllByStudentIdAndAttendanceDateBetween(Long studentId, LocalDate startDate, LocalDate endDate);

    List<Attendence> findAllByStudentId(Long studentId);

    List<Attendence> findAllByStudentIdAndAttendanceDateBetween(Long studentId, LocalDate startDate, LocalDate endDate);

    List<Attendence> findByAttendanceDateBetween(LocalDate startDate, LocalDate currentDate);

    // Add more query methods as needed
}
