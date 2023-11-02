package com.ahlesunnat.asws.service;


import java.util.List;

import com.ahlesunnat.asws.domain.Student;
import com.ahlesunnat.asws.response.StudentResponse;

public interface StudentService {

    Student createStudent(Student student);

    List<StudentResponse> getAllStudentsInCenter(Long zoneId, Long centerId);

    Student getStudentInCenterById(Long zoneId, Long centerId, Long studentId);

    Student updateStudent(Student student);

    // void deleteStudent(Long zoneId, Long centerId, Long studentId);

}
