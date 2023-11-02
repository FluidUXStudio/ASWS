package com.ahlesunnat.asws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ahlesunnat.asws.domain.Zone;


@Repository
public interface ZoneRepository extends JpaRepository<Zone,Long>{
    
}
