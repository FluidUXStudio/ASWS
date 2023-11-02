package com.ahlesunnat.asws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahlesunnat.asws.domain.Subject;
import com.ahlesunnat.asws.service.impl.SubjectServiceImpl;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    @Autowired
    private SubjectServiceImpl subjectServiceImpl;

    @PostMapping
    public Subject createSubject(@RequestBody Subject subject) {
        // Zone z = new Zone();
        // z.setName(name);
        return subjectServiceImpl.createSubject(subject);
    }

    @GetMapping
    public List<Subject> getAllSubjects() {
        return subjectServiceImpl.getAllSubjects();
    }

    @GetMapping("/{zoneId}")
    public Subject getZoneById(@PathVariable Long subjectId) {
        return subjectServiceImpl.getSubjectById(subjectId);
    }

}
