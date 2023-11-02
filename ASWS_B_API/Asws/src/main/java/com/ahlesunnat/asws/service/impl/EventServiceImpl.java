package com.ahlesunnat.asws.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahlesunnat.asws.domain.Event;
import com.ahlesunnat.asws.repository.EventRepository;
import com.ahlesunnat.asws.service.EventService;


@Service
public class EventServiceImpl implements EventService{


    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event createEvent(Event event) {
       Event event2 = eventRepository.save(event);
       return event2;

    }

    @Override
    public List<Event> getAllEvents() {
      return eventRepository.findAll();
    }
    
}
