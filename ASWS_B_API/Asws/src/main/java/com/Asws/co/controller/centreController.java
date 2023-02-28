package com.Asws.co.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Asws.co.domain.Center;
import com.Asws.co.domain.Notification;
import com.Asws.co.repository.CenterRepository;
import com.Asws.co.repository.NotificationRepository;
import com.Asws.co.response.CenterResponse;
import com.Asws.co.service.Impl.CenterServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("center")
public class centreController {

    private static final Logger logger = LoggerFactory.getLogger(centreController.class);


    @Autowired
    private CenterServiceImpl centerServiceImpl;

    @Autowired
    private CenterRepository centerRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private ObjectMapper mapper = new ObjectMapper();

    @PostMapping("/registerNewCenter")
    public ResponseEntity<Center> createBga(@RequestParam("center") String center, @RequestParam("file") List<MultipartFile> file) throws IOException {
        Center d1 = mapper.readValue(center,Center.class);
        Notification n1 = new Notification();
        // List<byte[]> pics = new ArrayList<>();
        for(MultipartFile fi:file){
            d1.setPhoto(fi.getBytes());
        }
        
        n1.setMessage("create a new Center");
        centerRepository.save(d1);
        n1.setCreatedAt("guioeufg");
        notificationRepository.save(n1);

        return ResponseEntity.status(HttpStatus.OK).body(d1);
    }
    @GetMapping("/search")
	public ResponseEntity<CenterResponse> search(@RequestParam Map<String, String> obj){
		CenterResponse sdr = centerServiceImpl.searchCenterName(obj);
		return ResponseEntity.status(HttpStatus.OK).body(sdr);
	}

    @GetMapping("/getallCenterNames")
    public List<String> getAllcenterNames(){
        logger.info("get All centersNames");
        return centerServiceImpl.getAllCenterNames();
    }  
    

    
}
