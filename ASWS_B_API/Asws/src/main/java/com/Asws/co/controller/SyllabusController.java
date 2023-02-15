package com.Asws.co.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Asws.co.domain.Syllabus;
import com.Asws.co.repository.StudentRepository;
import com.Asws.co.service.Impl.SyllabusServiceImpl;

@RestController
@RequestMapping("/syllabus")
public class SyllabusController {

    @Autowired
    private SyllabusServiceImpl serviceImpl;
    
    @Autowired
    private StudentRepository studentRepository;

    @PostMapping({"/createNewSyllabus"})
    public Syllabus createNewSyllabus(@RequestBody Syllabus obj) throws Exception {
        if(!studentRepository.existsById(obj.getStudentId())){

            throw new Exception("Student not Found");

        }

        Syllabus tc = serviceImpl.creatSyllabus(obj);
        return tc;
    }


    @GetMapping("/getAllSyllabus")
    public List<Syllabus> getAllSyllabus(){
        return serviceImpl.getAllSyllabus();
    } 

    @PutMapping({"/updateSyllabus"})
    public Syllabus updateSyllabus(@RequestBody Syllabus obj) throws Exception {
        Syllabus tc = serviceImpl.updateSyllabus(obj);
        return tc;
    }
    
}
