package com.ahlesunnat.asws.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ahlesunnat.asws.domain.Student;
import com.ahlesunnat.asws.domain.StudentChapterCompletion;
import com.ahlesunnat.asws.response.ChapterResponse;
import com.ahlesunnat.asws.response.StudentChapterPerformance;
import com.ahlesunnat.asws.service.impl.StudentChapterComServiceImpl;

@Controller
@RequestMapping("/api/studentPerformance")
public class StudentPerformance {

    @Autowired
    private StudentChapterComServiceImpl serviceImpl;

    // @PostMapping("/{studentId}/chapters/{chapterId}/complete")
    // public ResponseEntity<String> markChapterCompletion(
    // @PathVariable Long studentId,
    // @PathVariable Long chapterId,
    // @RequestParam boolean completed) {
    // serviceImpl.updateStudentChapterCompletion(studentId, chapterId, completed);
    // return ResponseEntity.ok("Chapter completion status updated.");
    // }

    @PostMapping("/{studentId}/subject/{subjectId}/chapters/{chapterId}/complete")
    public ResponseEntity<String> markStudentChapter(
            @PathVariable Long studentId,
            @PathVariable Long chapterId,
            @PathVariable Long subjectId,
            @RequestParam boolean completed) {

        serviceImpl.createStudentMapping(studentId, subjectId, chapterId, completed);
        return ResponseEntity.ok("Chapter completion status updated.");
    }

    @PutMapping("/{studentId}/subject/{subjectId}/chapters/{chapterId}/complete")
    public ResponseEntity<String> updateStudentChapter(
            @PathVariable Long studentId,
            @PathVariable Long chapterId,
            @PathVariable Long subjectId,
            @RequestParam boolean completed) {

        serviceImpl.updateStudentMapping(studentId, subjectId, chapterId, completed);
        return ResponseEntity.ok("Chapter completion status updated.");
    }

    // @GetMapping
    // @ResponseBody
    // public String hello(){
    // String s = "hi";
    // return s;
    // }

    @GetMapping
    @ResponseBody
    public List<StudentChapterCompletion> getAllPerformance() {
        return serviceImpl.getAllChaptersPerformance();
    }

    @GetMapping("/int")
    @ResponseBody
    public int getAllPerformancess() {
        List<StudentChapterCompletion> st = serviceImpl.getAllChaptersPerformance();
        return st.size();
    }

    // @GetMapping("/student/{studentId}/subject/{subjectId}")
    // @ResponseBody
    // public List<ChapterResponse> getStudentChapterCompletionByStudentAndSubject(
    //         @PathVariable Long studentId,
    //         @PathVariable Long subjectId) {

    //      List<ChapterResponse> chapters = new ArrayList<>();
    //       for(StudentChapterCompletion sc : serviceImpl.findByStudentIdAndSubjectId(studentId, subjectId)){

    //         ChapterResponse chapterResponse = new ChapterResponse();
    //         chapterResponse.setChapterName(sc.getChapter().getName());
    //         chapterResponse.setIsCompleted(sc.isCompleted());
    //         System.out.println(chapterResponse);
    //         chapters.addAll(chapters);

    //       }      
            
    //     return chapters;
    // }

    @GetMapping("/student/{studentId}/subject/{subjectId}")
    @ResponseBody
    public List<ChapterResponse> getStudentChapterCompletionByStudentAndSubject(
            @PathVariable Long studentId,
            @PathVariable Long subjectId) {
    
        List<ChapterResponse> chapters = new ArrayList<>();
        
        for (StudentChapterCompletion sc : serviceImpl.findByStudentIdAndSubjectId(studentId, subjectId)) {
            ChapterResponse chapterResponse = new ChapterResponse();
            chapterResponse.setChapterName(sc.getChapter().getName());
            chapterResponse.setIsCompleted(sc.isCompleted());
            chapterResponse.setId(sc.getId());
            System.out.println(chapterResponse);
            chapters.add(chapterResponse); // Use add to add elements to the list
        }
        
        return chapters;
    }
    


    @PostMapping("/calculate")
    @ResponseBody
    public List<StudentChapterPerformance> calculateChapterPerformance(@RequestBody List<Student> students) {
        return serviceImpl.calculateChapterPerformance(students);
    }
}
