package com.Asws.co.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Asws.co.domain.Center;

@Repository
public interface CenterRepository extends JpaRepository<Center,Long>{

}
