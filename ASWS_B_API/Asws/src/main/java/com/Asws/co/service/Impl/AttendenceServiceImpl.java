package com.Asws.co.service.Impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Asws.co.domain.Attendence;
import com.Asws.co.domain.Notification;
import com.Asws.co.repository.AttendenceRepository;
import com.Asws.co.repository.StudentRepository;
import com.Asws.co.response.StudentAttendenceResponse;
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



    public StudentAttendenceResponse searchServiceDetailWithFilter(Map<String, String> obj ) {
		
	    StudentAttendenceResponse resp = StudentAttendenceResponse.builder().build();

		List<Attendence> list = attendenceRepository.findAll();

		if (obj.size() == 0) {
            resp.setAttendences(list);
			resp.setTotal(list.size());
			return resp;
		}

		List<Attendence> list2 = list;
        if (obj.containsKey("id")) {
            list2 = list2.stream().filter(sd -> ((String) sd.getId()).equalsIgnoreCase(obj.get("id")))
                .collect(Collectors.toList());
        }
        if (obj.containsKey("name")) {
            list2 = list2.stream().filter(sd -> ((String) sd.getName()).equalsIgnoreCase(obj.get("name")))
                .collect(Collectors.toList());
        }
        if (obj.containsKey("date")) {
            list2 = list2.stream().filter(sd -> ((String) sd.getDate()).equalsIgnoreCase(obj.get("date")))
                .collect(Collectors.toList());
        }
        if (obj.containsKey("leave")) {
            list2 = list2.stream().filter(sd -> ((String) sd.getLeave()).equalsIgnoreCase(obj.get("leave")))
                .collect(Collectors.toList());
        }
        if (obj.containsKey("presentAndAbsent")) {
            list2 = list2.stream().filter(sd -> ((String) sd.getPresentAndAbsent()).equalsIgnoreCase(obj.get("presentAndAbsent")))
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

    public static float getPerformances(long totalstd,int totalAbsent){

     float performance =(totalAbsent /totalstd) * 100;
        return performance;
    }


    public List<Integer> getOverAllPErformance(){

        List<Attendence> ls = attendenceRepository.findAll();
        List<Integer> ars = new ArrayList<>();
        int present = 0;
        int Absent = 0;

       

        for(Attendence a :ls){
        //   ars.add(a.getDate().length(), a);
            a.getPresentAndAbsent().length();

            if (a.getPresentAndAbsent() == "present") {
                present = a.getPresentAndAbsent().length();
                ars.add(present);
                System.out.println(present);

            //   boolean sd =  a.getPresentAndAbsent().contains("present");
            //   System.out.println(sd);
            //   ars.add(sd);
            }else if(a.getPresentAndAbsent() == "absent"){
                Absent = a.getPresentAndAbsent().length();
                System.out.println(Absent);
                ars.add(Absent);
            }
        }
        return ars;
    }

    public static void main(String[] args) {
        float rest = 54 / 7f;

        System.out.println(rest);
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

    

