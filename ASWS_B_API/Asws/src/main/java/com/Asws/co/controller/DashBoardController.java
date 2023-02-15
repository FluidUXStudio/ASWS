package com.Asws.co.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Asws.co.service.Impl.DashBoardServicecImpl;

@RestController
@RequestMapping("/dashboard")
public class DashBoardController {


    @Autowired
    private DashBoardServicecImpl dashBoardServicecImpl;


    @GetMapping("/allDetails")
    public Map<String,Object> getallDetails(){

        return dashBoardServicecImpl.getAllTotalDetails();
        
    }
    
}
