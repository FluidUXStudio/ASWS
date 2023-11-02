package com.ahlesunnat.asws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ahlesunnat.asws.domain.Chapter;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter,Long>{

    
    List<Chapter> findBySubjectId(Long subjectId);

    Chapter findBySubjectIdAndId(Long subjectId, Long chapterId);
    
}
