package com.ahlesunnat.asws.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahlesunnat.asws.domain.Chapter;
import com.ahlesunnat.asws.repository.ChapterRepository;
import com.ahlesunnat.asws.service.ChapterService;


@Service
public class ChapterServiceImpl implements ChapterService{

    @Autowired
    private ChapterRepository chapterRepository;

    @Autowired
    private StudentChapterComServiceImpl serviceImpl;

    @Override
    public Chapter createChapter(Chapter chapter) {
        return chapterRepository.save(chapter);
    }

    @Override
    public List<Chapter> getAllChaptersInSubject(Long subjectId) {
       return chapterRepository.findBySubjectId(subjectId);
    }

    @Override
    public Chapter getChapterInSubjectById(Long subjectId, Long chapterId) {
        // TODO Auto-generated method stub
        return chapterRepository.findBySubjectIdAndId(subjectId, chapterId);
    }

    
}
