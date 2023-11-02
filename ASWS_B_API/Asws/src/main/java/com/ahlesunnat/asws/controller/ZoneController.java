// package com.ahlesunnat.asws.controller;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.ahlesunnat.asws.domain.Zone;
// import com.ahlesunnat.asws.service.impl.ZoneServiceImpl;



// @RestController
// @RequestMapping("/api/zones")
// public class ZoneController {


//     @Autowired
//     private ZoneServiceImpl zoneService;

//     @PostMapping
//     public Zone createZone(@RequestBody Zone zone) {
//         return zoneService.createZone(zone);
//     }

//     // @PostMapping
//     // public Zone createZones(@RequestBody Zone zone) {
//     //     return zoneRepository.save(zone);
//     // }

//     @GetMapping
//     public List<Zone> getAllZones() {
//         return zoneService.getAllZones();
//     }

//     @GetMapping("/{zoneId}")
//     public Zone getZoneById(@PathVariable Long zoneId) {
//         return zoneService.getZoneById(zoneId);
//     }

// }
