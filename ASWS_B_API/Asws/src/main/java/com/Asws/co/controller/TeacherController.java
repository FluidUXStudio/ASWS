package com.Asws.co.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Asws.co.domain.Teacher;
import com.Asws.co.repository.TeacherRepository;
import com.Asws.co.service.Impl.EmailServiceImpl;
import com.Asws.co.service.Impl.TeacherServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private static final Logger logger = LoggerFactory.getLogger(TeacherController.class);

    @Autowired
    private TeacherServiceImpl teacherServiceImpl;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ObjectMapper mapper = new ObjectMapper();


    @Autowired
    private EmailServiceImpl emailServiceImpl;


    @PostMapping({"/registerNewTeacher"})
    public ResponseEntity<Teacher> createBga(@RequestParam("teacher") String teacher, @RequestParam("file") MultipartFile file) throws IOException, MessagingException {
        Teacher d1 = mapper.readValue(teacher,Teacher.class);
        d1.setPhoto(file.getBytes());
        teacherRepository.save(d1);
        emailServiceImpl.sendEmail(d1.getEmail().toString(), "password","create Password");
        return ResponseEntity.status(HttpStatus.OK).body(d1);
    }

    @GetMapping("/getallTeachers")
    public List<Teacher> getAllStudents(){
        logger.info("get All teachers");
        return teacherServiceImpl.getAllTeachers();
    } 

    @GetMapping("/teacher/{id}")
	public ResponseEntity<Teacher> getteacher(@PathVariable Long id) {
		logger.info("Request to get teacher. Id: "+id);
		Optional<Teacher> odp = teacherServiceImpl.getTeacherById(id);
		if(odp.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(odp.get());
		}
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

    
}
