package com.ahlesunnat.asws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ahlesunnat.asws.domain.TeacherAttendence;
import com.ahlesunnat.asws.service.impl.TeacherAttendenceServiceImpl;


@RestController
@RequestMapping("/api/teacher")
public class TeacherAttendenceController {

    @Autowired
    private TeacherAttendenceServiceImpl attendanceService;
    
    @PostMapping("/checkin")
    public ResponseEntity<String> recordCheckin(@RequestParam Long teacherId) {
        attendanceService.recordCheckin(teacherId);
        return ResponseEntity.ok("Check-in recorded successfully.");
    }

    @PostMapping("/checkout")
    public ResponseEntity<String> recordCheckout(@RequestParam Long teacherId) {
        attendanceService.recordCheckout(teacherId);
        return ResponseEntity.ok("Check-out recorded successfully.");
    }

    @GetMapping("/attendance")
    public ResponseEntity<List<TeacherAttendence>> getAttendance() {
        List<TeacherAttendence> attendance = attendanceService.getAttendanceRecords();
        return ResponseEntity.ok(attendance);
    }
}
