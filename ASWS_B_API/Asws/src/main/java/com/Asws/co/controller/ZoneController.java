package com.Asws.co.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Asws.co.domain.Zone;
import com.Asws.co.response.ZoneResponse;
import com.Asws.co.service.Impl.ZoneServiceImpl;

@RestController
@RequestMapping("/zone")
public class ZoneController {

    @Autowired
    private ZoneServiceImpl zoneServiceImpl;

    @PostMapping({"/createNewZone"})
    public Zone registerNewTeacher(@RequestBody Zone obj) {
        return zoneServiceImpl.creatNewZone(obj);
    }

    @GetMapping("/getallZones")
    public List<Zone> getAllZones(){
        return zoneServiceImpl.getAllZones();
    }

    @GetMapping("/search")
	public ResponseEntity<ZoneResponse> search(@RequestParam Map<String, String> obj){
		ZoneResponse sdr = zoneServiceImpl.searchZoneName(obj);
		return ResponseEntity.status(HttpStatus.OK).body(sdr);
	}
    
}
