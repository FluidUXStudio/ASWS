package com.Asws.co.service.Impl;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Asws.co.domain.Events;
import com.Asws.co.repository.EventsRepository;
import com.Asws.co.response.EventResponse;
import com.Asws.co.service.EventService;

@Service
public class EventsServiceImpl implements EventService{


    @Autowired
    private EventsRepository eventsRepository;

    @Override
    public Events creatEvents(Events obj) {

        Events ev = eventsRepository.save(obj);
        return ev;
    }

    @Override
    public List<Events> getAllEvents() {
        return eventsRepository.findAll();
    }

    public EventResponse search(Map<String, String> obj ) {
		
	    EventResponse resp = EventResponse.builder().build();

		List<Events> list = eventsRepository.findAll();

		if (obj.size() == 0) {
            resp.setEvents(list);
			resp.setTotal(list.size());
			return resp;
		}

		List<Events> list2 = list;
       
        if (obj.containsKey("title")) {
            list2 = list2.stream().filter(sd -> ((String) sd.getTitle()).equalsIgnoreCase(obj.get("title")))
                .collect(Collectors.toList());
        }
        if (obj.containsKey("selectTime")) {
            list2 = list2.stream().filter(sd -> ((Time) sd.getSelectTime()).equals(obj.get("selectTime")))
                .collect(Collectors.toList());
        }
        if (obj.containsKey("selectDate")) {
            list2 = list2.stream().filter(sd -> ((Date) sd.getSelectDate()).equals(obj.get("selectDate")))
                .collect(Collectors.toList());
        }
        if (obj.containsKey("description")) {
            list2 = list2.stream().filter(sd -> ((String) sd.getDescription()).equalsIgnoreCase(obj.get("description")))
                .collect(Collectors.toList());
        }
        
		resp.setEvents(list2);
		resp.setTotal(list2.size());
		return resp;
	}

    
    
}
