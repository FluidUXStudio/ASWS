package com.Asws.co.response;

import java.util.List;

import com.Asws.co.domain.Events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventResponse {
    
    private Integer total;
    private List<Events> events;

    public static EventResponse from(List<Events> events) {
        EventResponse sdr = EventResponse.builder()
                .build();
        sdr.setEvents(events);
        return sdr;
    }

}
