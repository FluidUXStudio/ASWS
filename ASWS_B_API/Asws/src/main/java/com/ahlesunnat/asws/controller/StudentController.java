package com.ahlesunnat.asws.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ahlesunnat.asws.domain.Center;
import com.ahlesunnat.asws.domain.Student;
import com.ahlesunnat.asws.domain.Teacher;
import com.ahlesunnat.asws.domain.Zone;
import com.ahlesunnat.asws.repository.StudentRepository;
import com.ahlesunnat.asws.repository.TeacherRepository;
import com.ahlesunnat.asws.repository.TestingRepository;
import com.ahlesunnat.asws.response.StudentResponse;
import com.ahlesunnat.asws.service.impl.StudentServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/zones/{zoneId}/centers/{centerId}/students")
public class StudentController {

    private final StudentRepository studentRepository;

    @Autowired
    private final TeacherRepository teacherRepository;

    @Autowired
    private TestingRepository repository;

    public StudentController(StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @Autowired
    private StudentServiceImpl studentService;

    // @Autowired
    // private StudentRepository studentRepository;

    @Autowired
    private ObjectMapper mapper = new ObjectMapper();

    // Create a student within a center
    @PostMapping("/teachers/{teacherId}")
    public Student createStudent(@PathVariable Long zoneId, @PathVariable Long centerId, @PathVariable Long teacherId,
            @RequestParam("student") String student, @RequestParam("file") MultipartFile file) throws IOException {
        // Set the zone ID and center ID for the student

        Student d1 = mapper.readValue(student, Student.class);

        d1.setZone(new Zone(zoneId));
        d1.setTeacher(new Teacher(teacherId));
        d1.setCenter(new Center(centerId));
        d1.setImageData(file.getBytes());

        return studentService.createStudent(d1);
    }

    // @PostMapping("/import")
    // public ResponseEntity<String> importStudentsFromExcel(@PathVariable Long
    // zoneId, @PathVariable Long centerId,
    // @RequestParam("file") MultipartFile excelFile) {
    // try {
    // List<Student> students = studentService.importStudentsFromExcel(excelFile);

    // // Now you have a list of Student objects with data from the Excel file
    // // You can add them to the database or perform any other operation as needed
    // for (Student student : students) {

    // DecimalFormat decimalFormat = new DecimalFormat("#.#");

    // // Format the number to remove trailing zeros
    // String formattedNumber = decimalFormat.format(student.getTeacher().getId());

    // // Parse the formatted string back to a double (if needed)
    // double result = Double.parseDouble(formattedNumber);

    // Student d1 = student;
    // Optional<Teacher> teacher = teacherRepository.findById((long)result);
    // System.out.println(teacher.get().getId());

    // d1.setZone(new Zone(zoneId));
    // d1.setCenter(new Center(centerId));
    // d1.setImageData(student.getImageData());
    // d1.setTeacher(new Teacher(teacher.get().getId()));
    // studentService.createStudent(d1);
    // }

    // return new ResponseEntity<>("Data imported successfully", HttpStatus.OK);
    // } catch (IOException e) {
    // e.printStackTrace();
    // return new ResponseEntity<>("Error importing data from Excel",
    // HttpStatus.INTERNAL_SERVER_ERROR);
    // }
    // }
    @PostMapping("/import")
    public ResponseEntity<String> importStudentsFromExcel(@PathVariable Long zoneId, @PathVariable Long centerId,
            @RequestParam("file") MultipartFile excelFile) {
        try {
            List<Student> students = studentService.importStudentsFromExcel(excelFile);

            // Now you have a list of Student objects with data from the Excel file
            // You can add them to the database or perform any other operation as needed
            for (Student student : students) {
                Student d1 = student;

                // // Perform your formatting logic to update the teacher information
                // double teacherId = d1.getTeacher().getId();
                // DecimalFormat decimalFormat = new DecimalFormat("#.#");
                // String formattedTeacherId = decimalFormat.format(teacherId);
                // long parsedTeacherId = Long.parseLong(formattedTeacherId);

                // Retrieve the Teacher object from the database based on the parsed teacherId

                Optional<Teacher> teacher = teacherRepository.findById(d1.getTeacher().getId());
                Teacher foundTeacher = teacher.orElseThrow();

                if (teacher.isPresent()) {
                    d1.setTeacher(foundTeacher);
                } else {
                    // Handle the case where the teacher is not found
                    // You can log an error or take appropriate action
                }

                // Set other properties and save the Student object
                d1.setZone(new Zone(zoneId));
                d1.setCenter(new Center(centerId));
                d1.setImageData(student.getImageData());
                studentService.createStudent(d1);
            }

            return new ResponseEntity<>("Data imported successfully", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error importing data from Excel", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // public ResponseEntity<Student> createBga(@RequestParam("student") String
    // student, @RequestParam("file") MultipartFile file) throws IOException {
    // Student d1 = mapper.readValue(student,Student.class);
    // Notification n1 = new Notification();
    // d1.setPhoto(file.getBytes());
    // n1.setMessage("create a new student");
    // studentRepository.save(d1);
    // n1.setCreatedAt("guioeufg");
    // notificationRepository.save(n1);

    // return ResponseEntity.status(HttpStatus.OK).body(d1);
    // }

    // Get all students within a center
    // @GetMapping
    // public ResponseEntity<List<Student>> getAllStudents(@PathVariable Long
    // zoneId, @PathVariable Long centerId) {
    // List<Student> s1 =studentService.getAllStudentsInCenter(zoneId, centerId);
    // for(Student s : s1){
    // System.out.println(s.);
    // }
    // return ResponseEntity.status(HttpStatus.OK).body(s1);
    // }

    // @GetMapping
    // public ResponseEntity<List<Student>> getAllStudents() {
    // List<Student> s1 = studentRepository.findAll();
    // return ResponseEntity.status(HttpStatus.OK).body(s1);
    // }

    @GetMapping
    public List<StudentResponse> getAllStudents(@PathVariable Long zoneId, @PathVariable Long centerId)
            throws IOException {

        // Return the list of students as JSON response
        return studentService.getAllStudentsInCenter(zoneId, centerId);
    }

    @GetMapping("/teachers/{teacherId}")
    public List<StudentResponse> getAllStudentsInTeacher(@PathVariable Long zoneId, @PathVariable Long centerId,
            @PathVariable Long teacherId) throws IOException {

        List<StudentResponse> students = studentService.getAllStudentsInCenterAndTeacher(zoneId, centerId, teacherId);
        System.out.println(students.size());
        // Return the list of students as JSON response
        return students;
    }

    // Get a specific student within a center
    @GetMapping("/{studentId}")
    public Student getStudentById(@PathVariable Long zoneId, @PathVariable Long centerId,
            @PathVariable Long studentId) {
        return studentService.getStudentInCenterById(zoneId, centerId, studentId);
    }

    // Update a student within a center
    @PutMapping("/{studentId}")
    public Student updateStudent(@PathVariable Long zoneId, @PathVariable Long centerId, @PathVariable Long studentId,
            @RequestBody Student updatedStudent) {
        updatedStudent.setId(studentId);
        updatedStudent.setZone(new Zone(zoneId));
        updatedStudent.setCenter(new Center(centerId));

        return studentService.updateStudent(updatedStudent);
    }

    // @DeleteMapping("/{studentId}")
    // public void deleteStudent(@PathVariable Long zoneId, @PathVariable Long
    // centerId, @PathVariable Long studentId) {
    // studentService.deleteStudent(zoneId, centerId, studentId);
    // }
    @GetMapping("/addingStudent")
    public void addMultipleStudents() throws IOException {
        String filePath = "C:\\Users\\umran.moosa\\Downloads\\asws-pics\\Abdul Quddus.jfif";
        Path path = Paths.get(filePath);

        // Read the file content into a byte array
        byte[] fileBytes = Files.readAllBytes(path);

        // Create the common student details
        Map<String, Object> studentDetails = new HashMap<>();
        studentDetails.put("city", "Sample City");
        studentDetails.put("email", "sample@email.com");
        studentDetails.put("phone", 1234567890L);
        studentDetails.put("state", "NY");
        studentDetails.put("gender", "Male");
        studentDetails.put("address", "123 Main St");
        studentDetails.put("pinCode", 10001);
        studentDetails.put("lastName", "Doe");
        studentDetails.put("firstName", "John");
        studentDetails.put("schoolName", "Sample School");
        studentDetails.put("adharNumber", 1234567890L);
        studentDetails.put("dateOfBirth", "1995-05-15");

        // Create the sibling_information map
        Map<String, Object> siblingInformation = new HashMap<>();
        siblingInformation.put("sibAge", 28);
        siblingInformation.put("brOrSis", "Brother");
        siblingInformation.put("sibStandard", "Grade 8");
        siblingInformation.put("siblingSchool", "Sibling School");
        siblingInformation.put("siblingStuding", true);
        siblingInformation.put("siblingFullName", "Jane Doe");

        // Create the family_information map
        Map<String, Object> familyInformation = new HashMap<>();
        familyInformation.put("email", "family@email.com");
        familyInformation.put("phone", 9876543210L);
        familyInformation.put("fatherName", "Michael Doe");
        familyInformation.put("motherName", "Jennifer Doe");
        familyInformation.put("parentOccupation", "Parental Occupation");
        familyInformation.put("educationalQualification", "Parental Qualification");

        // Create the Student object
        for (int i = 1; i <= 300; i++) {
            Student student = new Student();
            student.setStudent_details(studentDetails);
            student.setSibling_information(siblingInformation);
            student.setFamily_information(familyInformation);
            student.setImageData(fileBytes);
            student.setTeacher(new Teacher((long) 2));
            student.setZone(new Zone((long) 1));

            student.setCenter(new Center((long) 1));

            System.out.println("Creating Student " + i);
            studentService.createStudent(student);
        }
        System.out.println("Finished creating students");
    }

}
