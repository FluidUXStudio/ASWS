package com.ahlesunnat.asws.service;

import java.util.List;

import com.ahlesunnat.asws.domain.Zone;

public interface ZoneService {

    Zone createZone(Zone zone);

    List<Zone> getAllZones();

    Zone getZoneById(Long zoneId);
}
