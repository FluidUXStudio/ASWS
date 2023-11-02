package com.ahlesunnat.asws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ahlesunnat.asws.domain.Event;


@Repository
public interface EventRepository extends JpaRepository<Event,Long>{
    
}
