package com.ahlesunnat.asws.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahlesunnat.asws.domain.Chapter;
import com.ahlesunnat.asws.domain.Student;
import com.ahlesunnat.asws.domain.StudentChapterCompletion;
import com.ahlesunnat.asws.domain.Subject;
import com.ahlesunnat.asws.repository.ChapterRepository;
import com.ahlesunnat.asws.repository.StudentChapterComRepository;
import com.ahlesunnat.asws.repository.StudentRepository;
import com.ahlesunnat.asws.repository.SubjectRepository;
import com.ahlesunnat.asws.response.StudentChapterPerformance;
import com.ahlesunnat.asws.service.StudentChapterComService;

@Service
public class StudentChapterComServiceImpl implements StudentChapterComService {

    @Autowired
    private StudentChapterComRepository studentChapterComRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ChapterRepository chapterRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public StudentChapterCompletion createStudentChapterCompletion(StudentChapterCompletion studentChapterCompletion) {

        return studentChapterComRepository.save(studentChapterCompletion);

    }

    public void updateStudentChapterCompletion(Long studentId, Long chapterId, boolean completed) {
        Student student = studentRepository.findById(studentId).orElse(null);
        Chapter chapter = chapterRepository.findById(chapterId).orElse(null);

        // StudentChapterCompletion completion =
        // studentChapterComRepository.findByStudentAndChapter(student, chapter);
        // System.out.println(completion);
        // if (completion == null) {
        // completion = new StudentChapterCompletion(student, chapter, completed);
        // } else {
        // completion.setCompleted(completed);
        // }
        StudentChapterCompletion st = new StudentChapterCompletion();
        st.setChapter(chapter);
        st.setStudent(student);
        st.setCompleted(completed);

        studentChapterComRepository.save(st);
    }

    public StudentChapterCompletion createStudentMapping(Long studentId, Long subjectId, Long chapterId,
            Boolean complete) {

        Student student = studentRepository.findById(studentId).orElse(null);
        Chapter chapter = chapterRepository.findById(chapterId).orElse(null);
        Subject subject = subjectRepository.findById(subjectId).orElse(null);

        StudentChapterCompletion studentMapping = new StudentChapterCompletion();
        studentMapping.setStudent(student);
        studentMapping.setSubject(subject);
        studentMapping.setChapter(chapter);
        studentMapping.setCompleted(complete); // Set completed status as needed
        return studentChapterComRepository.save(studentMapping);
    }

    public StudentChapterCompletion updateStudentMapping(Long studentId, Long subjectId, Long chapterId,
            Boolean complete) {
                
        // StudentChapterCompletion existingMapping = studentChapterComRepository
        //         .findByStudentIdAndSubjectIdAndChapterId(39L, 7L, 1688L);
        
                // StudentChapterCompletion subj = studentChapterComRepository
                // .findByStudentIdAndSubjectIdAndChapterId(studentId, subjectId);

                StudentChapterCompletion existingMapping = studentChapterComRepository.findById(chapterId).orElse(null);

        System.out.println(existingMapping);
        if (existingMapping != null) {
            existingMapping.setCompleted(complete);
            return studentChapterComRepository.save(existingMapping); // Update the
            // existing mapping
        } else {
            // Handle the case where the mapping doesn't exist
            throw new RuntimeException("StudentChapterCompletion mapping not found");
        }
    }

    // public ResponseEntity<String> updateStudentMapping(
    // Long studentId, Long subjectId, Long chapterId, Boolean complete) {

    // StudentChapterCompletion existingMapping = studentChapterComRepository
    // .findByStudentIdAndSubjectIdAndChapterId(studentId, subjectId, chapterId);

    // if (existingMapping != null) {
    // existingMapping.setCompleted(complete);
    // studentChapterComRepository.save(existingMapping); // Update the existing
    // mapping
    // return ResponseEntity.ok("Chapter completion status updated.");
    // } else {
    // return ResponseEntity.badRequest().body("StudentChapterCompletion mapping not
    // found");
    // }
    // }

    public List<StudentChapterCompletion> getAllChaptersPerformance() {

        return studentChapterComRepository.findAll();

    }

    public List<StudentChapterCompletion> findByStudentIdAndSubjectId(Long studentId, Long subjectId) {
        return studentChapterComRepository.findByStudentIdAndSubjectId(studentId, subjectId);
    }

    // public double calculateChapterPerformance() {
    // List<StudentChapterCompletion> chapters =
    // studentChapterComRepository.findAll();
    // int totalChapters = chapters.size();
    // int completedChapters = 0;

    // for (StudentChapterCompletion chapter : chapters) {
    // if (chapter.isCompleted()) {
    // completedChapters++;
    // }
    // }

    // if (totalChapters == 0) {
    // return 0.0; // Avoid division by zero
    // }

    // return (double) completedChapters / totalChapters;
    // }

    // public List<StudentChapterPerformance> calculateChapterPerformance(List<Student> students) {
    //     // 1. Fetch all chapters for the given subjectId
    //     List<StudentChapterCompletion> chapters = studentChapterComRepository.findAll();
    //     List<StudentChapterPerformance> performances = new ArrayList<>();
    //     // 2. Fetch completed chapters for the given studentId and subjectId

    //     for (Student s : students) {
    //         List<StudentChapterCompletion> completedChapters = studentChapterComRepository
    //                 .findByStudentIdAndIsCompleted(s.getId(), true);

    //         System.out.println(completedChapters);
    //         double totalChapters = chapters.size();
    //         double completedChapterCount = completedChapters.size();
    //         System.out.println(completedChapterCount +"completed chapter");

    //         double performance = (completedChapterCount / totalChapters) * 100.0;

    //         // Round the result to two decimal places
    //         DecimalFormat df = new DecimalFormat("#.##");
    //         double roundedPerformance = Double.parseDouble(df.format(performance));
    //         StudentChapterPerformance studentPerformance = new StudentChapterPerformance();
    //         studentPerformance.setStudentName(s.getStudent_details());
    //         studentPerformance.setPerformance(roundedPerformance);

    //         // Add to the list of performances
    //         performances.add(studentPerformance);
    //     }
    //     System.out.println(performances + "performance");
        // 3. Calculate performance as (completed chapters / total chapters)

        // }
        // public List<StudentChapterPerformance> calculateChapterPerformance(Long
        // subjectId) {
        // // 1. Fetch all students who completed chapters for the given subjectId
        // List<Student> students =
        // studentRepository.findBySubjectIdAndCompletedChapters(subjectId);

        // List<StudentChapterPerformance> performances = new ArrayList<>();

        // for (Student student : students) {
        // // 2. Fetch completed chapters for the student and subject
        // List<StudentChapterCompletion> completedChapters =
        // studentChapterComRepository
        // .findByStudentIdAndSubjectIdAndIsCompleted(student.getId(), subjectId, true);

        // // 3. Calculate performance as (completed chapters / total chapters) and
        // store it along with the name
        // double totalChapters =
        // studentChapterComRepository.countBySubjectId(subjectId);
        // double completedChapterCount = completedChapters.size();

        // double performance = (completedChapterCount / totalChapters) * 100.0;

        // // Round the result to two decimal places
        // DecimalFormat df = new DecimalFormat("#.##");
        // double roundedPerformance = Double.parseDouble(df.format(performance));

        // // Create a StudentChapterPerformance object with name and performance
        // StudentChapterPerformance studentPerformance = new
        // StudentChapterPerformance();
        // studentPerformance.setStudentName(student.getName());
        // studentPerformance.setPerformance(roundedPerformance);

        // // Add to the list of performances
        // performances.add(studentPerformance);
        // }

    //     return performances;
    // }

    public List<StudentChapterPerformance> calculateChapterPerformance(List<Student> students) {
        // 1. Fetch all chapters for the given subjectId
        List<Chapter> chapters = chapterRepository.findAll();
        List<StudentChapterPerformance> performances = new ArrayList<>();
    
        for (Student s : students) {
            List<StudentChapterCompletion> completedChapters = studentChapterComRepository
                    .findByStudentIdAndIsCompleted(s.getId(), true);
    
            double totalChapters = chapters.size();
            double completedChapterCount = completedChapters.size();
    
            double performance = (completedChapterCount / totalChapters) * 100.0;
    
            // Round the result to two decimal places
            DecimalFormat df = new DecimalFormat("#.##");
            double roundedPerformance = Double.parseDouble(df.format(performance));
            StudentChapterPerformance studentPerformance = new StudentChapterPerformance();
            studentPerformance.setStudentName(s.getStudent_details());
            studentPerformance.setPerformance(roundedPerformance);
    
            // Add to the list of performances
            performances.add(studentPerformance);
        }
    
        // Print the performances list
        System.out.println(performances);
    
        return performances;
    }
    

}
