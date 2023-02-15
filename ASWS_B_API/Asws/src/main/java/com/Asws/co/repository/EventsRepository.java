package com.Asws.co.repository;

import org.springframework.stereotype.Repository;

import com.Asws.co.domain.Events;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EventsRepository extends JpaRepository<Events,Long>{
    
}
