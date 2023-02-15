package com.Asws.co.response;

import java.util.List;

import com.Asws.co.domain.demo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class demoResponse {
    


    private Integer total;
    private List<demo> demos;
  
    public static demoResponse from(List<demo> services) {
        demoResponse sdr = demoResponse.builder()
                .build();
        sdr.setDemos(services);
        return sdr;
    }
}
