package com.Asws.co.controller;

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

import com.Asws.co.domain.Center;
import com.Asws.co.response.CenterResponse;
import com.Asws.co.service.Impl.CenterServiceImpl;


@RestController
@RequestMapping("center")
public class centreController {

    private static final Logger logger = LoggerFactory.getLogger(centreController.class);


    @Autowired
    private CenterServiceImpl centerServiceImpl;

    @PostMapping({"/registerNewCentre"})
    public Center registerNewTeacher(@RequestBody Center obj) {
        logger.info("register new Center");
        return centerServiceImpl.creatTeacher(obj);
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
