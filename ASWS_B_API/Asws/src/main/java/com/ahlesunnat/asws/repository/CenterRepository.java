package com.ahlesunnat.asws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ahlesunnat.asws.domain.Center;


@Repository
public interface CenterRepository extends JpaRepository<Center, Long> {

    List<Center> findByZoneId(Long zoneId);

    Center findByZoneIdAndId(Long zoneId, Long centerId);

}
