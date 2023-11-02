package com.ahlesunnat.asws.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahlesunnat.asws.domain.Chapter;
import com.ahlesunnat.asws.domain.Student;
import com.ahlesunnat.asws.domain.Subject;
import com.ahlesunnat.asws.repository.StudentRepository;
import com.ahlesunnat.asws.service.impl.ChapterServiceImpl;
import com.ahlesunnat.asws.service.impl.StudentChapterComServiceImpl;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/subjects/{subjectId}/chapters")
public class ChapterController {

    @Autowired
    private ChapterServiceImpl chapterServiceImpl;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentChapterComServiceImpl serviceImpl;
    // @Autowired
    // private ObjectMapper mapper = new ObjectMapper();

    // @PostMapping
    // public Chapter createChapter(@PathVariable Long subjectId, @RequestBody
    // Chapter chapter) throws IOException {
    // // Set the zone ID for the center
    // System.out.println(chapter);
    // System.out.println(subjectId);
    // Chapter d1 = chapter;
    // d1.setSubject(new Subject(subjectId));
    // d1.setName(chapter.getName());
    // return chapterServiceImpl.createChapter(d1);
    // }

    @PostMapping
    @Transactional
    public Chapter createChapter(@PathVariable Long subjectId, @RequestBody Chapter chapter) throws IOException {
        // Set the zone ID for the center

        Chapter d1 = chapter;
        d1.setSubject(new Subject(subjectId));
        d1.setName(chapter.getName());

        // Create the chapter and retrieve its ID
        Chapter createdChapter = chapterServiceImpl.createChapter(d1);
        Long chapterId = createdChapter.getId();

        // Get the list of all students outside the loop
        List<Student> students = studentRepository.findAll();
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            serviceImpl.createStudentMapping(student.getId(), subjectId, chapterId, false);
            System.out.println(student.getId());

        }

        // You can handle the response here if needed

        return createdChapter;
    }

    @PostMapping("/import")
    public void createStudentMappingsForChapters(@PathVariable Long subjectId, @RequestBody List<Chapter> chapters) {
        System.out.println(subjectId);
        for (Chapter chapter : chapters) {
            Chapter d1 = chapter;
            d1.setSubject(new Subject(subjectId));
            d1.setName(chapter.getName());

            Chapter createdChapter = chapterServiceImpl.createChapter(d1);
            Long chapterId = createdChapter.getId();

            List<Student> students = studentRepository.findAll();
            for (Student student : students) {
                serviceImpl.createStudentMapping(student.getId(), subjectId, chapterId, false);
                System.out.println("Mapping created for student ID: " + student.getId());
            }
        }
    }

    @GetMapping
    public List<Chapter> getAllChapter(@PathVariable Long subjectId) {
        return chapterServiceImpl.getAllChaptersInSubject(subjectId);
    }

    // Get a specific center within a zone
    @GetMapping("/{chapterId}")
    public Chapter getChapterById(@PathVariable Long subjectId, @PathVariable Long chapterId) {
        return chapterServiceImpl.getChapterInSubjectById(subjectId, chapterId);
    }

}