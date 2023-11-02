package com.ahlesunnat.asws.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahlesunnat.asws.domain.Volunteer;
import com.ahlesunnat.asws.repository.VolunteerRepository;
import com.ahlesunnat.asws.service.VolunteerService;


@Service
public class VolunteerServiceImpl implements VolunteerService {

   
    @Autowired
    private VolunteerRepository volunteerRepository;

    @Override
    public Volunteer createVolunteer(Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }

    @Override
    public List<Volunteer> getAllVolunteersInCenter(Long zoneId, Long centerId) {
        return volunteerRepository.findAllByZoneIdAndCenterId(zoneId, centerId);
    }

    @Override
    public Volunteer getVolunteerInCenterById(Long zoneId, Long centerId, Long volunteerId) {
        return volunteerRepository.findByZoneIdAndCenterIdAndId(zoneId, centerId, volunteerId);
    }

    @Override
    public Volunteer updateVolunteer(Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }

    @Override
    public void deleteVolunteer(Long zoneId, Long centerId, Long volunteerId) {
        volunteerRepository.deleteByZoneIdAndCenterIdAndId(zoneId, centerId, volunteerId);
    }

}
