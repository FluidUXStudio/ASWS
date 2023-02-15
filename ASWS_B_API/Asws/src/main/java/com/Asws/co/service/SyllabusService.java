package com.Asws.co.service;

import java.util.List;

import com.Asws.co.domain.Syllabus;

public interface SyllabusService {


    public Syllabus creatSyllabus(Syllabus obj) throws Exception ;

    public List<Syllabus> getAllSyllabus();


    public Syllabus updateSyllabus(Syllabus obj) throws Exception;
    
    
}
