package com.Asws.co.response;

import java.util.List;

import com.Asws.co.domain.Center;

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
public class CenterResponse {

    private Integer total;
    private List<Center> centers;

    public static CenterResponse from(List<Center> centers) {
        CenterResponse sdr = CenterResponse.builder()
                .build();
        sdr.setCenters(centers);
        return sdr;
    }

    
}
