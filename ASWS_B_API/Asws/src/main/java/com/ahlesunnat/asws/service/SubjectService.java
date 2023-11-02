package com.ahlesunnat.asws.service;

import java.util.List;

import com.ahlesunnat.asws.domain.Subject;

public interface SubjectService {

    Subject createSubject(Subject subject);

    List<Subject> getAllSubjects();

    Subject getSubjectById(Long subjectId);

}
