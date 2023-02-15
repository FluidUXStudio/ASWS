package com.Asws.co.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.Asws.co.domain.Syllabus;
import com.Asws.co.repository.SyllabusRepository;
import com.Asws.co.service.SyllabusService;

@Service
public class SyllabusServiceImpl implements SyllabusService{

    @Autowired
    private SyllabusRepository syllabusRepository;

    @Override
    public Syllabus creatSyllabus(Syllabus obj) {
        Syllabus sd = syllabusRepository.save(obj);
        
        return sd;
    }

    @Override
    public List<Syllabus> getAllSyllabus() {
        return syllabusRepository.findAll();
    }

    @Override
    public Syllabus updateSyllabus(Syllabus obj) throws Exception {
        if (!syllabusRepository.existsById(obj.getId())){
			throw new Exception("idnotfound");
		}
		Syllabus sd = syllabusRepository.save(obj);
		return sd;
    }
    
}
