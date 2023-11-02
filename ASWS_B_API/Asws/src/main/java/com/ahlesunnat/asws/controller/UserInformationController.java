package com.ahlesunnat.asws.controller;



import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ahlesunnat.asws.domain.Teacher;
import com.ahlesunnat.asws.repository.StudentRepository;
import com.ahlesunnat.asws.repository.TeacherRepository;


@RestController
@RequestMapping("/api")
public class UserInformationController {


    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    // @Autowired
    // private UserRepository userRepository;
    
    // @GetMapping("/teacherProfile")
    // public ResponseEntity<Teacher> findByEmail(@RequestParam("email") String email) {
    //     List<Teacher> teachers = teacherRepository.findAll();

        
    //     Teacher teacher = teacherRepository.findByEmail(email);
    //     if (teacher != null) {
    //         return ResponseEntity.ok(teacher);
    //     } else {
    //         return ResponseEntity.notFound().build();
    //     }
    // }


    @GetMapping("/teacherProfile")
    public ResponseEntity<List<Teacher>> getTeachersByEmail(@RequestParam String email) {
        List<Teacher> teachers = teacherRepository.findAll();
        List<Teacher> filteredTeachers = teachers.stream()
                .filter(teacher -> doesTeacherDetailsContainEmail(teacher, email))
                .collect(Collectors.toList());
        
        if (!filteredTeachers.isEmpty()) {
            return ResponseEntity.ok(filteredTeachers);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private boolean doesTeacherDetailsContainEmail(Teacher teacher, String email) {
        return Optional.ofNullable(teacher.getTeacher_details())
                .map(details -> details.get("email"))
                .map(String::valueOf)
                .map(email::equals)
                .orElse(false);
    }
    // @GetMapping("/teacherProfile")
    // public ResponseEntity<List<Teacher>> findByEmail() {

    //     List<Teacher> s = teacherRepository.findAll();

    //      return ResponseEntity.ok(s);
    // }

    
}
