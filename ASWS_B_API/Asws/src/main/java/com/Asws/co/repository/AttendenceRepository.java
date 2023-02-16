package com.Asws.co.repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Asws.co.domain.Attendence;


@Repository
public interface AttendenceRepository extends JpaRepository<Attendence,String>{

    List<Attendence> findByDateBetween(LocalDate startDate,LocalDate enDate);
    
}
