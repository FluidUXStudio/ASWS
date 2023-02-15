package com.Asws.co.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Asws.co.domain.Syllabus;

@Repository
public interface SyllabusRepository  extends JpaRepository<Syllabus,Long>{
    
}
