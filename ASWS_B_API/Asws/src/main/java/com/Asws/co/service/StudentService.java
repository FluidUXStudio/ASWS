package com.Asws.co.service;

import java.util.List;

import com.Asws.co.domain.Student;

public interface StudentService {
    public Student createStudent(Student obj);
    public List<Student> getAllStudents();
}
