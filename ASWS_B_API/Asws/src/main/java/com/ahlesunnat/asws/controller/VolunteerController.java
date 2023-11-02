package com.ahlesunnat.asws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ahlesunnat.asws.domain.Center;
import com.ahlesunnat.asws.domain.Volunteer;
import com.ahlesunnat.asws.domain.Zone;
import com.ahlesunnat.asws.service.VolunteerService;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/zones/{zoneId}/centers/{centerId}/volunteers")
public class VolunteerController {

    @Autowired
    private VolunteerService volunteerService;

    // Create a volunteer within a center
    @PostMapping
    public Volunteer createVolunteer(@PathVariable Long zoneId, @PathVariable Long centerId,
            @RequestBody Volunteer volunteer) {
        // Set the zone ID and center ID for the volunteer
            volunteer.setZone(new Zone(zoneId));
            volunteer.setCenter(new Center(centerId));
            return volunteerService.createVolunteer(volunteer);
    }

    // Get all volunteers within a center
    @GetMapping
    public List<Volunteer> getAllVolunteers(@PathVariable Long zoneId, @PathVariable Long centerId) {
        return volunteerService.getAllVolunteersInCenter(zoneId, centerId);
    }

    // Get a specific volunteer within a center
    @GetMapping("/{volunteerId}")
    public Volunteer getVolunteerById(@PathVariable Long zoneId, @PathVariable Long centerId,
            @PathVariable Long volunteerId) {
        return volunteerService.getVolunteerInCenterById(zoneId, centerId, volunteerId);
    }

    // Update a volunteer within a center
    @PutMapping("/{volunteerId}")
    public Volunteer updateVolunteer(@PathVariable Long zoneId, @PathVariable Long centerId,
            @PathVariable Long volunteerId, @RequestBody Volunteer updatedVolunteer) {
        updatedVolunteer.setId(volunteerId);
        updatedVolunteer.setZone(new Zone(zoneId));
        updatedVolunteer.setCenter(new Center(centerId));

        return volunteerService.updateVolunteer(updatedVolunteer);
    }

    // Delete a volunteer within a center
    @DeleteMapping("/{volunteerId}")
    public void deleteVolunteer(@PathVariable Long zoneId, @PathVariable Long centerId,
            @PathVariable Long volunteerId) {
        volunteerService.deleteVolunteer(zoneId, centerId, volunteerId);
    }

}
