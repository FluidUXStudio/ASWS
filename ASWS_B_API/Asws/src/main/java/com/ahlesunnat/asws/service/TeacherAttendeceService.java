package com.ahlesunnat.asws.service;

import java.util.List;

import com.ahlesunnat.asws.domain.TeacherAttendence;

public interface TeacherAttendeceService {
    public void recordCheckin(Long teacherId);
    public void recordCheckout(Long teacherId);
    public List<TeacherAttendence> getAttendanceRecords();
    
}
