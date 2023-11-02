package com.ahlesunnat.asws.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ahlesunnat.asws.domain.Center;
import com.ahlesunnat.asws.domain.Zone;
import com.ahlesunnat.asws.service.impl.CenterServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/zones/{zoneId}/centers")
public class CenterController {

    @Autowired
    private CenterServiceImpl centerService;

    @Autowired
    private ObjectMapper mapper = new ObjectMapper();

    // Create a center within a zone
    @PostMapping
    public Center createCenter(@PathVariable Long zoneId, @RequestParam("center") String center,
            @RequestParam("file") List<MultipartFile> file) throws IOException {
        // Set the zone ID for the center

        Center d1 = mapper.readValue(center, Center.class);
        d1.setZone(new Zone(zoneId));

        List<byte[]> images = new ArrayList<>();

        // Iterate over the list of MultipartFile objects
        for (MultipartFile fil : file) {
            // Convert the MultipartFile to a byte array
            byte[] imageData = fil.getBytes();
            // Add the byte array to the list of images
            images.add(imageData);
        }

        // Set the list of images to the Center entity
        d1.setImages(images);

        return centerService.createCenter(d1);
    }

    @PostMapping("/import")
    public ResponseEntity<String> importStudentsFromExcel(@PathVariable Long zoneId,
            @RequestParam("file") MultipartFile excelFile) throws Exception {
        try {
            List<Center> centers = centerService.importCentersFromExcel(excelFile);

            // Now you have a list of Student objects with data from the Excel file
            // You can add them to the database or perform any other operation as needed
            for (Center center : centers) {

                List<byte[]> images = center.getImages();

                // Iterate over the list of MultipartFile objects

                Center d1 = center;
                d1.setZone(new Zone(zoneId));
                d1.setImages(images);
                centerService.createCenter(d1);
                // Save the student to the database or perform any other operation
                System.out.println(d1);
            }

            return new ResponseEntity<>("Data imported successfully", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error importing data from Excel", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all centers within a zone
    @GetMapping
    public List<Center> getAllCenters(@PathVariable Long zoneId) {
        return centerService.getAllCentersInZone(zoneId);
    }

    // Get a specific center within a zone
    @GetMapping("/{centerId}")
    public Center getCenterById(@PathVariable Long zoneId, @PathVariable Long centerId) {
        return centerService.getCenterInZoneById(zoneId, centerId);
    }

}
