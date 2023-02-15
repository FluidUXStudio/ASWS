package com.Asws.co.service;

import java.util.List;

import com.Asws.co.domain.Zone;

public interface ZoneService {
    

    public Zone creatNewZone(Zone obj);

    public List<Zone> getAllZones();
}
