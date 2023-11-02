package com.ahlesunnat.asws.service;

import java.util.List;

import com.ahlesunnat.asws.domain.Chapter;

public interface ChapterService {

    Chapter createChapter(Chapter chapter);

    List<Chapter> getAllChaptersInSubject(Long subjectId);

    
    Chapter getChapterInSubjectById(Long subjectId, Long chapterId);


}
