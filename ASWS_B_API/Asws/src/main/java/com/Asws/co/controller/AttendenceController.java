package com.Asws.co.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Asws.co.domain.Attendence;
import com.Asws.co.domain.Notification;
import com.Asws.co.response.StudentAttendenceResponse;
import com.Asws.co.service.Impl.AttendenceServiceImpl;
import com.Asws.co.service.Impl.NotificationServiceImpl;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/attendence")
public class AttendenceController {

    private static final Logger logger = LoggerFactory.getLogger(AttendenceController.class);



    @Autowired
    private AttendenceServiceImpl attendenceServiceImpl;

    @Autowired
    private NotificationServiceImpl notificationServiceImpl;



    @PostMapping({"/createNewAttendence"})
    public List<Attendence> creaAttendence(@org.springframework.web.bind.annotation.RequestBody List<Attendence> obj) {
        logger.info("", obj);
       List<Attendence> a1 = attendenceServiceImpl.creatAttendence(obj);
        return a1;
    }


    @GetMapping("/getallAttendence")
    public List<Attendence> getAllAttendence(){
        return attendenceServiceImpl.getAllAttendence();
    }  


    @GetMapping("/search")
	public ResponseEntity<StudentAttendenceResponse> search(@RequestParam Map<String, String> obj){
		StudentAttendenceResponse sdr = attendenceServiceImpl.searchServiceDetailWithFilter(obj);
		return ResponseEntity.status(HttpStatus.OK).body(sdr);
	}
    
    @GetMapping("/performance")
    public float getPerformance(){
        return attendenceServiceImpl.getPerformance();
    } 
}
