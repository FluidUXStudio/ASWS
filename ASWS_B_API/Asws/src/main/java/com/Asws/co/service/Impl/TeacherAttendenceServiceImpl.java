package com.Asws.co.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.Asws.co.domain.TeacherAttendence;
import com.Asws.co.repository.TeacherAttendenceRepository;
import com.Asws.co.service.TeacherAttendenceService;

public class TeacherAttendenceServiceImpl implements TeacherAttendenceService{

    @Autowired
    private TeacherAttendenceRepository teacherAttendenceRepository;

    @Override
    public TeacherAttendence creatTeacheraAttendence(TeacherAttendence obj) {
        TeacherAttendence tc = teacherAttendenceRepository.save(obj);
        
        return tc;
    }

    @Override
    public List<TeacherAttendence> getAllTeacherAttendences() {
        return teacherAttendenceRepository.findAll();
    }

    @Override
    public TeacherAttendence updateAttendence(TeacherAttendence obj) throws Exception {
		if (!teacherAttendenceRepository.existsById(obj.getId())) {
			throw new Exception("idnotfound");
		}
		TeacherAttendence sd = teacherAttendenceRepository.save(obj);
		return sd;
	}
    
}
