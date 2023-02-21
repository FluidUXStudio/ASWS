package com.Asws.co.controller;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Asws.co.domain.Attendence;
import com.Asws.co.domain.Notification;
import com.Asws.co.domain.Student;
import com.Asws.co.repository.AttendenceRepository;
import com.Asws.co.repository.StudentRepository;
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
    private StudentRepository studentRepository;
    @Autowired
    private AttendenceRepository attendanceRepository;
    @Autowired
    private NotificationServiceImpl notificationServiceImpl;

    private Map<LocalDate, List<String>> attendanceRecords = new HashMap<>();



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
	public ResponseEntity<StudentAttendenceResponse> search(@RequestParam Map<String, Object> obj){
		StudentAttendenceResponse sdr = attendenceServiceImpl.searchServiceDetailWithFilter(obj);
		return ResponseEntity.status(HttpStatus.OK).body(sdr);
	}
    
    // @GetMapping("/performance")
    // public float getPerformance(@RequestParam  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate d1, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate d2){
    //     return attendenceServiceImpl.getDAtes(d1, d2);
    // }

    @GetMapping("/average")
    public double getAverageAttendanceByStatus(@RequestParam("status") String status, @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")  LocalDateTime startDate, @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")  LocalDateTime endDate) {
        return attendenceServiceImpl.Averageperformance(status, startDate, endDate);
    }
  
    // @GetMapping("/average")
    // public ResponseEntity<?> getAverageAttendance(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,@RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
    //                                               @RequestParam("type") String type) {
    //     List<Student> students = studentRepository.findAll();
    //     // List<Attendence> attendanceList = attendanceRepository.findByDateTimeBetween(
    //     //         getStartDate(startDate, type),
    //     //         getEndDate(endDate, type)
    //     // );
    //     List<Attendence> attendanceList = attendanceRepository.findAll();
    //     // Calculate the attendance percentages for each student
    //     Map<String, Double> attendancePercentages = new HashMap<>();
    //     for (Student student : students) {
    //         long presentCount = attendanceList.stream()
    //         .filter(sd -> ((String) sd.getPresentAndAbsent()).equalsIgnoreCase("present")).count();
    //         long absentCount = attendanceList.stream()
    //         .filter(sd -> ((String) sd.getPresentAndAbsent()).equalsIgnoreCase("absent")).count();
    //         long leaveCount = attendanceList.stream()
    //         .filter(sd -> ((String) sd.getPresentAndAbsent()).equalsIgnoreCase("leave")).count();
    //         long totalCount = presentCount + absentCount + leaveCount;
    //         double attendancePercentage = totalCount == 0 ? 0 : presentCount / (double) totalCount * 100;
    //         attendancePercentages.put(student.getId(), attendancePercentage);
    //     }
    //     System.out.println(attendancePercentages.get("present"));

    //     // Calculate the average attendance percentage across all students
    //     double averageAttendance = attendancePercentages.values().stream().mapToDouble(Double::doubleValue).average().orElse(0.0);

    //     return ResponseEntity.ok(averageAttendance);
    // }

    // private LocalDateTime getStartDate(LocalDate date, String type) {
    //     switch (type) {
    //         case "day":
    //             return date.atStartOfDay();
    //         case "week":
    //             return date.with(DayOfWeek.MONDAY).atStartOfDay();
    //         case "month":
    //             return date.withDayOfMonth(1).atStartOfDay();
    //         case "year":
    //             return date.withDayOfYear(1).atStartOfDay();
    //         default:
    //             throw new IllegalArgumentException("Invalid date type: " + type);
    //     }
    // }

    // private LocalDateTime getEndDate(LocalDate date, String type) {
    //     switch (type) {
    //         case "day":
    //             return date.atTime(LocalTime.MAX);
    //         case "week":
    //             return date.with(DayOfWeek.SUNDAY).atTime(LocalTime.MAX);
    //         case "month":
    //             return date.withDayOfMonth(date.lengthOfMonth()).atTime(LocalTime.MAX);
    //         case "year":
    //             return date.withDayOfYear(date.lengthOfYear()).atTime(LocalTime.MAX);
    //         default:
    //             throw new IllegalArgumentException("Invalid date type: " + type);
    //     }
    // }

}
