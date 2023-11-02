// package com.ahlesunnat.asws.controller;


// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;

// import com.ahlesunnat.asws.service.impl.StudentChapterComServiceImpl;

// @Controller
// @RequestMapping("/api/studentPerformance")
// public class StudentChapterCompleteController {

//     @Autowired
//     private StudentChapterComServiceImpl serviceImpl;

//     // @PostMapping("/{studentId}/chapters/{chapterId}/complete")
//     // public ResponseEntity<String> markChapterCompletion(
//     // @PathVariable Long studentId,
//     // @PathVariable Long chapterId,
//     // @RequestParam boolean completed) {
//     // serviceImpl.updateStudentChapterCompletion(studentId, chapterId, completed);
//     // return ResponseEntity.ok("Chapter completion status updated.");
//     // }

//     @PostMapping("/{studentId}/subject/{subjectId}/chapters/{chapterId}/complete")
//     public ResponseEntity<String> markStudentChapter(
//             @PathVariable Long studentId,
//             @PathVariable Long chapterId,
//             @PathVariable Long subjectId,
//             @RequestParam boolean completed) {

//         serviceImpl.createStudentMapping(studentId, subjectId, chapterId, completed);
//         return ResponseEntity.ok("Chapter completion status updated.");
//     }

//     @GetMapping
//     public String hello(){
//         return "hello";
//     }
// }
