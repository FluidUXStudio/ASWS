package com.ahlesunnat.asws.service;

import java.util.List;

import com.ahlesunnat.asws.domain.Center;

public interface CenterService {
    Center createCenter(Center center);

    List<Center> getAllCentersInZone(Long zoneId);

    Center getCenterInZoneById(Long zoneId, Long centerId);

}
