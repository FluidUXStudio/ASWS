package com.Asws.co.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Asws.co.domain.Events;
import com.Asws.co.domain.TeacherAttendence;
import com.Asws.co.service.Impl.TeacherAttendenceServiceImpl;

@RestController
@RequestMapping("/teacherAttendence")
public class TeacherAttendenceController {


    @Autowired
    private TeacherAttendenceServiceImpl teacherAttendenceServiceImpl;


    @PostMapping({"/createNewAttendence"})
    public TeacherAttendence createNewAttendence(@RequestBody TeacherAttendence obj) {
        TeacherAttendence tc = teacherAttendenceServiceImpl.creatTeacheraAttendence(obj);
        return tc;
    }


    @GetMapping("/getAllAttendence")
    public List<TeacherAttendence> getAllTeacherAttendences(){
        return teacherAttendenceServiceImpl.getAllTeacherAttendences();
    } 

    @PutMapping({"/updateAttendence"})
    public TeacherAttendence updateAttendence(@RequestBody TeacherAttendence obj) throws Exception {
        TeacherAttendence tc = teacherAttendenceServiceImpl.updateAttendence(obj);
        return tc;
    }
    
}
