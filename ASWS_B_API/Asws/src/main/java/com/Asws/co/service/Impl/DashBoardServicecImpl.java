package com.Asws.co.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Asws.co.domain.Center;
import com.Asws.co.domain.Student;
import com.Asws.co.domain.Teacher;
import com.Asws.co.repository.CenterRepository;
import com.Asws.co.repository.StudentRepository;
import com.Asws.co.repository.TeacherRepository;


@Service
public class DashBoardServicecImpl {
    
    @Autowired
    private StudentRepository studentRepository;

    
    @Autowired
    private CenterRepository centerRepository;

    
    @Autowired
    private TeacherRepository teacherRepository;

    public Map<String,Object> getAllTotalDetails(){

        Map<String,Object> dashboard = new HashMap<>();

        List<Teacher> teacher = teacherRepository.findAll();
        List<Student> student = studentRepository.findAll();
        List<Center> center = centerRepository.findAll();

        dashboard.put("AllTeacher",teacher.size());
        dashboard.put("AllStudents",student.size());
        dashboard.put("AllCenters",center.size());

        return dashboard;
    }
}
