package com.Asws.co.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Asws.co.domain.Events;
import com.Asws.co.response.EventResponse;
import com.Asws.co.service.Impl.EventsServiceImpl;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;


@RestController
@RequestMapping("/event")
public class EventsControlleer {

    
    @Autowired
    private EventsServiceImpl eventsServiceImpl;
    
    
        @GetMapping("/greets")
        public String greString(){
            return "ghdhfod";
        }

    @PostMapping({"/createNewEvent"})
    public Events createNewEvent(@RequestBody Events obj) {
        return eventsServiceImpl.creatEvents(obj);
    }




    @GetMapping("/getAllEvents")
    public List<Events> getAlleEvents(){
        return eventsServiceImpl.getAllEvents();
    }  

    @GetMapping("/search")
	public ResponseEntity<EventResponse> search(@RequestParam Map<String, String> obj){
		EventResponse sdr = eventsServiceImpl.search(obj);
		return ResponseEntity.status(HttpStatus.OK).body(sdr);
	}
    
}
