package com.ahlesunnat.asws.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahlesunnat.asws.domain.JwtRequest;
import com.ahlesunnat.asws.domain.JwtResponse;
import com.ahlesunnat.asws.service.JwtService;

@RestController
public class JwtController {

    private static final Logger logger = LoggerFactory.getLogger(JwtController.class);



    @Autowired
    private JwtService jwtService;

    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        logger.info("creating JWT token");
        return jwtService.createJwtToken(jwtRequest);
    }
    
}