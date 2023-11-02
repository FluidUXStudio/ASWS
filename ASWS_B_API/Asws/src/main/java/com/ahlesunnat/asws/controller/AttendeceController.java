package com.ahlesunnat.asws.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahlesunnat.asws.domain.Attendence;
import com.ahlesunnat.asws.domain.Student;
import com.ahlesunnat.asws.repository.StudentRepository;
import com.ahlesunnat.asws.service.impl.AttendenceServiceImpl;

@RestController
@RequestMapping("/api/students/attendance")
public class AttendeceController {

    @Autowired
    private AttendenceServiceImpl attendenceService;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/{studentId}")
    public ResponseEntity<List<Attendence>> getAllAttendance(@PathVariable Long studentId) {
        List<Attendence> attendanceList = attendenceService.getAllAttendancesByStudentId(studentId);
        return ResponseEntity.ok(attendanceList);
    }

    @PostMapping
    public ResponseEntity<List<Attendence>> createAttendance(@RequestBody List<Attendence> attendance) {
        List<Attendence> createdAttendance = new ArrayList<>();

        for (Attendence a : attendance) {
            Student student = a.getStudent();
            if (student != null) {
                Long studentId = student.getId();

                // Check if the student exists
                Optional<Student> studentOptional = studentRepository.findById(studentId);
                if (studentOptional.isPresent()) {
                    // Create a new attendance record and set the student
                    Attendence newAttendance = new Attendence();
                    newAttendance.setStudent(student);
                    newAttendance.setAttendanceDate(a.getAttendanceDate());
                    newAttendance.setStatus(a.getStatus());

                    // Set other attributes of newAttendance from 'a' as needed
                    // newAttendance.setDate(a.getDate()); // Example

                    createdAttendance.add(newAttendance);
                } else {
                    System.out.println("Student with ID " + studentId + " not found");
                }
            } else {
                System.out.println("Student is null for an Attendence object");
            }
        }
        System.out.println("attandence success full");
        // Create the attendance records in the service
        attendenceService.createAttendance(createdAttendance);

        return ResponseEntity.ok(createdAttendance);
    }
    @GetMapping("/summary")
    public Map<String, Object> getAttendanceSummary(
            @RequestParam(name = "startDate") String startDate) {
        // Parse the startDate string to LocalDate (you can use a date format that suits your application)
        LocalDate parsedStartDate = LocalDate.parse(startDate);

        // Call the service to get the attendance summary
        Map<String, Object> attendanceSummary = attendenceService.getAttendanceSummary(parsedStartDate);

        // Return the attendance summary as a JSON response
        return attendanceSummary;
    }

    // @PostMapping
    // public List<Attendence> createAttendance(@RequestBody List<Attendence>
    // attendance) {
    // List<Attendence> createdAttendance = new ArrayList<>();

    // for (Attendence a : attendance) {
    // Long studentId = a.getStudent().getId();

    // // Check if the student exists
    // Optional<Student> studentOptional = studentRepository.findById(studentId);
    // if (studentOptional.isPresent()) {
    // // Create a new attendance record and set the student
    // Attendence newAttendance = new Attendence();
    // newAttendance.setStudent(studentOptional.get());
    // // Set other attributes of newAttendance from 'a' as needed
    // // newAttendance.setDate(a.getDate()); // Example

    // createdAttendance.add(newAttendance);
    // } else {
    // System.out.println("Student with ID " + studentId + " not found");
    // }
    // }

    // // Create the attendance records in the service
    // attendenceService.createAttendance(createdAttendance);

    // return createdAttendance;
    // }

    // @GetMapping("/analyze")
    // public ResponseEntity<Map<String, Object>> analyzeAttendance(@PathVariable
    // Long studentId,
    // @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    // LocalDate startDate,
    // @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    // LocalDate endDate) {
    // Map<String, Object> analysisResult =
    // attendenceService.analyzeAttendance(studentId, startDate, endDate);
    // return ResponseEntity.ok(analysisResult);
    // }

    @GetMapping("/date/{attendanceDate}")
    @ResponseBody
    public List<Attendence> getAttendancesByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate attendanceDate) {
        return attendenceService.getAttendancesByDate(attendanceDate);
    }

}
