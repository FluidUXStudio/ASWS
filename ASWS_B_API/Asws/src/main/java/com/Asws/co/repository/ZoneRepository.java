package com.Asws.co.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Asws.co.domain.Zone;

@Repository
public interface ZoneRepository extends JpaRepository<Zone,Long>{
    
}
