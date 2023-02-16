package com.Asws.co.response;

import java.sql.Date;
import java.util.List;

import com.Asws.co.domain.Attendence;

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
public class StudentAttnPerformanceResponse {


    private Integer total;
    private Date startDate;
    private Date enDate;
    private List<Attendence> attendences;


    public static StudentAttnPerformanceResponse from(List<Attendence> services) {
        StudentAttnPerformanceResponse sdr = StudentAttnPerformanceResponse.builder()
                .build();
        sdr.setAttendences(services);
        return sdr;
    
}
}
