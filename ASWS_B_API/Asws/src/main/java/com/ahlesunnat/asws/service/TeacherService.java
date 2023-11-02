package com.ahlesunnat.asws.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ahlesunnat.asws.domain.Teacher;

public interface TeacherService {
    Teacher createTeacher(String teacher,Long zoneId, Long centerId , MultipartFile file);

    List<Teacher> getAllTeachersInCenter(Long zoneId, Long centerId);

    Teacher getTeacherInCenterById(Long zoneId, Long centerId, Long teacherId);

    Teacher updateTeacher(Teacher updatedTeacher);

    void deleteTeacher(Long zoneId, Long centerId, Long teacherId);
}
