package com.ahlesunnat.asws.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ahlesunnat.asws.domain.Center;
import com.ahlesunnat.asws.domain.Teacher;
import com.ahlesunnat.asws.domain.Zone;
import com.ahlesunnat.asws.service.impl.TeacherServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/zones/{zoneId}/centers/{centerId}/teachers")
public class TeacherController {

    @Autowired
    private TeacherServiceImpl teacherService;


    
    @Autowired
    private ObjectMapper mapper = new ObjectMapper();

    // Create a teacher within a center
    @PostMapping
    public Teacher createTeacher(@PathVariable Long zoneId, @PathVariable Long centerId,@RequestParam("teacher") String teacher, @RequestParam("file") MultipartFile file) throws IOException{
        // Set the zone ID and center ID for the teacher

        
        return teacherService.createTeacher(teacher,zoneId,centerId,file);
    }

    // @PostMapping("/import")
    // public ResponseEntity<String> importStudentsFromExcel(@PathVariable Long zoneId, @PathVariable Long centerId, @RequestParam("file") MultipartFile excelFile) {
    //     try {
    //         List<Teacher> teachers = teacherService.importTeachersFromExcel(excelFile);

    //         // Now you have a list of Student objects with data from the Excel file
    //         // You can add them to the database or perform any other operation as needed
    //         for (Teacher teacher : teachers) {

    //             Teacher d1 = teacher;
    //             d1.setZone(new Zone(zoneId));
    //             d1.setCenter(new Center(centerId));
    //             d1.setImageData(teacher.getImageData());
    //             teacherService.createTeacher(d1);
    //             // Save the student to the database or perform any other operation
    //             System.out.println(teacher);
    //         }

    //         return new ResponseEntity<>("Data imported successfully", HttpStatus.OK);
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //         return new ResponseEntity<>("Error importing data from Excel", HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }


    // Get all teachers within a center
    @GetMapping
    public List<Teacher> getAllTeachers(@PathVariable Long zoneId, @PathVariable Long centerId) {
        return teacherService.getAllTeachersInCenter(zoneId, centerId);
    }

    // Get a specific teacher within a center
    @GetMapping("/{teacherId}")
    public Teacher getTeacherById(@PathVariable Long zoneId, @PathVariable Long centerId,
            @PathVariable Long teacherId) {
        return teacherService.getTeacherInCenterById(zoneId, centerId, teacherId);
    }

    // Update a teacher within a center
    @PutMapping("/{teacherId}")
    public Teacher updateTeacher(@PathVariable Long zoneId, @PathVariable Long centerId, @PathVariable Long teacherId,
            @RequestBody Teacher updatedTeacher) {
        updatedTeacher.setId(teacherId);
        updatedTeacher.setZone(new Zone(zoneId));
        updatedTeacher.setCenter(new Center(centerId));

        return teacherService.updateTeacher(updatedTeacher);
    }


    // Delete a teacher within a center
    @DeleteMapping("/{teacherId}")
    public void deleteTeacher(@PathVariable Long zoneId, @PathVariable Long centerId, @PathVariable Long teacherId) {
        teacherService.deleteTeacher(zoneId, centerId, teacherId);
    }

}
