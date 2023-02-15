package com.Asws.co.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Asws.co.domain.Notification;
import com.Asws.co.domain.Student;
import com.Asws.co.repository.StudentRepository;
import com.Asws.co.response.StudentResponse;
import com.Asws.co.service.Impl.StudentServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/student")
public class StudentController {


    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentServiceImpl studentServiceImpl;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private com.Asws.co.repository.NotificationRepository notificationRepository;
    
    @Autowired
    private ObjectMapper mapper = new ObjectMapper();

    @PostMapping("/registerNewStudent")
    public ResponseEntity<Student> createBga(@RequestParam("student") String student, @RequestParam("file") MultipartFile file) throws IOException {
        Student d1 = mapper.readValue(student,Student.class);
        Notification n1 = new Notification();
        d1.setPhoto(file.getBytes());
        n1.setMessage("create a new student");
        studentRepository.save(d1);
        n1.setCreatedAt("guioeufg");
        notificationRepository.save(n1);

        return ResponseEntity.status(HttpStatus.OK).body(d1);
    }

    @GetMapping("/getallStudent")
    public List<Student> getAllStudents(){
        logger.info("get All Student");
        return studentServiceImpl.getAllStudents();
    }  


    @GetMapping("/search")
	public ResponseEntity<StudentResponse> search(@RequestParam Map<String, String> obj){
		StudentResponse sdr = studentServiceImpl.searchServiceDetailWithFilter(obj);
		return ResponseEntity.status(HttpStatus.OK).body(sdr);
	}
}
