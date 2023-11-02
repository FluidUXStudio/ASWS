package com.ahlesunnat.asws.service;

import java.util.List;

import com.ahlesunnat.asws.domain.Event;

public interface EventService {

    Event createEvent(Event event);
    List<Event> getAllEvents();
    
}
