package com.ahlesunnat.asws.service;

import java.util.List;

import com.ahlesunnat.asws.domain.Volunteer;

public interface VolunteerService {

    Volunteer createVolunteer(Volunteer volunteer);

    List<Volunteer> getAllVolunteersInCenter(Long zoneId, Long centerId);

    Volunteer getVolunteerInCenterById(Long zoneId, Long centerId, Long volunteerId);

    Volunteer updateVolunteer(Volunteer volunteer);

    void deleteVolunteer(Long zoneId, Long centerId, Long volunteerId);
}
