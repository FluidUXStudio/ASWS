package com.Asws.co.service;

import java.util.List;

import com.Asws.co.domain.TeacherAttendence;

public interface TeacherAttendenceService {


    public TeacherAttendence creatTeacheraAttendence(TeacherAttendence obj);

    public List<TeacherAttendence> getAllTeacherAttendences();


    public TeacherAttendence updateAttendence(TeacherAttendence obj) throws Exception;
    
}
