package com.Asws.co.service;

import java.util.List;

import com.Asws.co.domain.Events;

public interface EventService {


    public Events creatEvents(Events obj);

    public List<Events> getAllEvents();
    
}
