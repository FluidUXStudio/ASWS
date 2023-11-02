package com.ahlesunnat.asws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ahlesunnat.asws.domain.StudentChapterCompletion;

@Repository
public interface StudentChapterComRepository extends JpaRepository<StudentChapterCompletion, Long> {

    List<StudentChapterCompletion> findByStudentIdAndSubjectId(Long studentId, Long subjectId);

    StudentChapterCompletion findByStudentIdAndSubjectIdAndChapterId(Long studentId, Long subjectId, Long StudentChapterCompletionId);


    List<StudentChapterCompletion> findByStudentIdAndIsCompleted(Long studentId, boolean b);

    double countBySubjectId(Long subjectId);

    List<StudentChapterCompletion> findByStudentIdAndSubjectId(Long id, boolean b);

}
