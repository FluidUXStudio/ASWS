package com.ahlesunnat.asws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ahlesunnat.asws.domain.Volunteer;


@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {

    List<Volunteer> findAllByZoneIdAndCenterId(Long zoneId, Long centerId);

    Volunteer findByZoneIdAndCenterIdAndId(Long zoneId, Long centerId, Long volunteerId);

    void deleteByZoneIdAndCenterIdAndId(Long zoneId, Long centerId, Long volunteerId);

}
