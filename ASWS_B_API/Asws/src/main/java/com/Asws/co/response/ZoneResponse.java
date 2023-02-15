package com.Asws.co.response;

import java.util.List;

import com.Asws.co.domain.Zone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ZoneResponse {
    

    private Integer total;
    private List<Zone> zones;

    public static ZoneResponse from(List<Zone> zones) {
        ZoneResponse sdr = ZoneResponse.builder()
                .build();
        sdr.setZones(zones);
        return sdr;
    }

}
