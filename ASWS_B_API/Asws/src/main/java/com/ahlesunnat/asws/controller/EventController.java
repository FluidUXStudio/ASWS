package com.ahlesunnat.asws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahlesunnat.asws.domain.Event;
import com.ahlesunnat.asws.service.impl.EventServiceImpl;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventServiceImpl eventServiceImpl;

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {

        Event event2 = eventServiceImpl.createEvent(event);

        return new ResponseEntity<>(event2, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {

        List<Event> event2 = eventServiceImpl.getAllEvents();

        return new ResponseEntity<>(event2, HttpStatus.OK);

    }

}
