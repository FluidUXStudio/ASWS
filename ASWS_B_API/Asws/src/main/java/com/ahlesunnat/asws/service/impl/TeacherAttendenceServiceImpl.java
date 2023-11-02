package com.ahlesunnat.asws.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahlesunnat.asws.domain.Teacher;
import com.ahlesunnat.asws.domain.TeacherAttendence;
import com.ahlesunnat.asws.repository.TeacherAttendeceRepository;
import com.ahlesunnat.asws.repository.TeacherRepository;
import com.ahlesunnat.asws.service.TeacherAttendeceService;


@Service
public class TeacherAttendenceServiceImpl implements TeacherAttendeceService{

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private TeacherAttendeceRepository attendeceRepository;

    @Override
    public void recordCheckin(Long teacherId) {
        
        Teacher Teacher = teacherRepository.findById(teacherId).orElseThrow();
        TeacherAttendence attendance = new TeacherAttendence();
        attendance.setTeacher(Teacher);
        attendance.setCheckInTime(LocalDateTime.now());
        attendeceRepository.save(attendance);
    }

    @Override
    public void recordCheckout(Long teacherId) {
        Teacher employee = teacherRepository.findById(teacherId).orElseThrow();
        TeacherAttendence attendance = attendeceRepository.findByTeacherAndCheckOutTimeIsNull(employee);
        if (attendance != null) {
            attendance.setCheckOutTime(LocalDateTime.now());
            attendeceRepository.save(attendance);
        }
    }

    @Override
    public List<TeacherAttendence> getAttendanceRecords() {
        return attendeceRepository.findAll();
    }
    
}
