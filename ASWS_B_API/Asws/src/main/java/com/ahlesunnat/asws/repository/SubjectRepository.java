package com.ahlesunnat.asws.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ahlesunnat.asws.domain.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long>{

    
}
