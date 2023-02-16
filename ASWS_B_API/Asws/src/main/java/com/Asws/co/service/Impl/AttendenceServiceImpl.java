package com.Asws.co.service.Impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
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
            list2 = list2.stream().filter(sd -> ((String)sd.getDate().toString()).equals(obj.get("date")))
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

    public float getPerformance(){
        List<String> n1 = new ArrayList<>();
        List<Attendence> l1 = attendenceRepository.findAll();
        for(Attendence i :l1){
            n1.add(i.getPresentAndAbsent());
        }

        List<Attendence> absent = l1
                                .stream()
                                .filter(sd -> ((String) sd.getPresentAndAbsent()).equalsIgnoreCase("absent"))
                .collect(Collectors.toList());
        List<Attendence> present = l1
                                .stream()
                                .filter(sd -> ((String) sd.getPresentAndAbsent()).equalsIgnoreCase("present"))
                .collect(Collectors.toList());

        List<Attendence> leave = l1
                .stream()
                .filter(sd -> ((String) sd.getPresentAndAbsent()).equalsIgnoreCase("leave"))
.collect(Collectors.toList());

        int ab = absent.size();
        int pb = present.size();
        int lb = leave.size();

     float   performance =(float) ab/ l1.size();



        
        // int present=n1.size();
        return performance;
    }

    public float getDAtes(LocalDate d1, LocalDate d2){

        List<String> n1 = new ArrayList<>();
        List<Attendence> per = attendenceRepository.findByDateBetween(d1, d2);

        List<Attendence> l1 = attendenceRepository.findAll();
        for(Attendence i :l1){
            n1.add(i.getPresentAndAbsent());
        }

        // Duration timeElapsed = Duration.between(d1,d2);

        

    List<Attendence> present = l1
                    .stream()
                    .filter(sd -> ((String) sd.getPresentAndAbsent()).equalsIgnoreCase("present"))
                        .collect(Collectors.toList());

                        float   performance =(float) present.size() /6;
                        // float   performance = 506/6;
        return performance;
    }
    // } 

        public static float  getNumPErformance(float a , float b){

            float performance = a / b;

            return performance;
        }


   
    public static void main(String[] args) {
        float rest = 54 / 7f;
        LocalDate d = LocalDate.of(2002,11,23);
        LocalDate d2 =LocalDate.of(2003,11,23);
         dateRange = LocalDateRange.of( start , stop )
        Duration timeElapsed = Duration.between(d,d2);
        long diff = Duration.between(d,d2).toDays();


        

        System.out.println(timeElapsed.toDays());
    // float percentage;
    //   float total_marks;
    //   float scored;
    //   Scanner sc = new Scanner(System.in);
    //   System.out.println("Enter your marks ::");
    //   scored = sc.nextFloat();

    //   System.out.println("Enter total marks ::");
    //   total_marks = sc.nextFloat();

    //   percentage = (float)((scored / total_marks) * 100);
   }
        
    }

    

