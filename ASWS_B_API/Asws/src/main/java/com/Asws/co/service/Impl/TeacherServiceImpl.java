package com.Asws.co.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Asws.co.domain.Teacher;
import com.Asws.co.repository.TeacherRepository;
import com.Asws.co.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService{

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public Teacher creatTeacher(Teacher obj) {

        Teacher tc = teacherRepository.save(obj);

        return tc;
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Optional<Teacher> getTeacherById(Long id){
        return teacherRepository.findById(id);
    }
    
}
