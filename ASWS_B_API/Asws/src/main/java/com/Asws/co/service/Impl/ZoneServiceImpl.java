package com.Asws.co.service.Impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Asws.co.domain.Zone;
import com.Asws.co.repository.ZoneRepository;
import com.Asws.co.response.ZoneResponse;
import com.Asws.co.service.ZoneService;

@Service
public class ZoneServiceImpl implements ZoneService{


    @Autowired
    private ZoneRepository zoneRepository;

    @Override
    public Zone creatNewZone(Zone obj) {

        Zone zn = zoneRepository.save(obj);
        return zn;
    }

    @Override
    public List<Zone> getAllZones() {
        return zoneRepository.findAll();
    }


    public ZoneResponse searchZoneName(Map<String, String> obj ) {
		
	    ZoneResponse resp = ZoneResponse.builder().build();

		List<Zone> list = zoneRepository.findAll();
		if (obj.size() == 0) {
            resp.setZones(list);
			resp.setTotal(list.size());
			return resp;
		}

		List<Zone> list2 = list;
       
        if (obj.containsKey("zoneName")) {
            list2 = list2.stream().filter(sd -> ((String) sd.getZoneName()).equalsIgnoreCase(obj.get("zoneName")))
                .collect(Collectors.toList());
        }
        
		resp.setZones(list2);
		resp.setTotal(list2.size());
		return resp;
	}
    
}
