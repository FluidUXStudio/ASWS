package com.Asws.co.service.Impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Asws.co.domain.Attendence;
import com.Asws.co.domain.Notification;
import com.Asws.co.domain.Student;
import com.Asws.co.repository.AttendenceRepository;
import com.Asws.co.repository.StudentRepository;
import com.Asws.co.response.StudentAttendenceResponse;
import com.Asws.co.response.StudentAttnPerformanceResponse;
import com.Asws.co.service.AttendenceService;

@Service
public class AttendenceServiceImpl implements AttendenceService{

    @Autowired
    private AttendenceRepository attendenceRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public List<Attendence> creatAttendence(List<Attendence> obj) {

        for(Attendence a:obj){
            Attendence ad = attendenceRepository.save(a);
        }
        return obj;
    }

    @Override
    public List<Attendence> getAllAttendence() {
        return attendenceRepository.findAll();
    }



    public StudentAttendenceResponse searchServiceDetailWithFilter(Map<String, Object> obj ) {
		
	    StudentAttendenceResponse resp = StudentAttendenceResponse.builder().build();

		List<Attendence> list = attendenceRepository.findAll();

		if (obj.size() == 0) {
            resp.setAttendences(list);
			resp.setTotal(list.size());
			return resp;
		}


		List<Attendence> list2 = list;
        
        if (obj.containsKey("id")) {
            list2 = list2.stream().filter(sd -> ((String) sd.getId()).equalsIgnoreCase((String) obj.get("id")))
                .collect(Collectors.toList());
        }
        if (obj.containsKey("name")) {
            list2 = list2.stream().filter(sd -> ((String) sd.getName()).equalsIgnoreCase((String) obj.get("name")))
                .collect(Collectors.toList());
        }
        if (obj.containsKey("date")) {
            list2 = list2.stream().filter(sd -> ((String)sd.getDateTime().toString()).equals(obj.get("date")))
                .collect(Collectors.toList());
        }
        // if (obj.containsKey("date")) {
        //     list2 = list2.stream().filter(sd -> ((Date) sd.getDate()).equals((obj.get("date")))
        //         .collect(Collectors.toList());
        // }
        if (obj.containsKey("leave")) {
            list2 = list2.stream().filter(sd -> ((String) sd.getLeave()).equalsIgnoreCase((String) obj.get("leave")))
                .collect(Collectors.toList());
        }
        if (obj.containsKey("presentAndAbsent")) {
            list2 = list2.stream().filter(sd -> ((String) sd.getPresentAndAbsent()).equalsIgnoreCase((String) obj.get("presentAndAbsent")))
                .collect(Collectors.toList());
        }
		
		resp.setAttendences(list2);
		resp.setTotal(list2.size());
        // resp.setPerformance(studentRepository.count());
		return resp;
	}

    public double Averageperformance(String status ,  LocalDateTime startDate, LocalDateTime endDate) {
        List<Attendence> attendenceList= attendenceRepository.findByDateTimeBetween(startDate,endDate);
        List<Long> n1 = new ArrayList<>();
     
        double attendancePercentage = 0;
        for (Attendence student : attendenceList) {
            long presentCount = attendenceList.stream()
            .filter(sd -> ((String) sd.getPresentAndAbsent()).equalsIgnoreCase("present")).count();
            long absentCount = attendenceList.stream()
            .filter(sd -> ((String) sd.getPresentAndAbsent()).equalsIgnoreCase("absent")).count();
            long leaveCount = attendenceList.stream()
            .filter(sd -> ((String) sd.getPresentAndAbsent()).equalsIgnoreCase("leave")).count();
            long totalCount = presentCount + absentCount + leaveCount;
       
            if(status.equals("present")){
                attendancePercentage =  presentCount / (double) totalCount * 100;
            }else if(status.equals("absent")){
                attendancePercentage =  absentCount / (double) totalCount * 100;
            }else if(status.equals("leave")){
                attendancePercentage =  leaveCount / (double) totalCount * 100;
            }
           
        }
        return attendancePercentage;
    }

   
 
        
    }

    

