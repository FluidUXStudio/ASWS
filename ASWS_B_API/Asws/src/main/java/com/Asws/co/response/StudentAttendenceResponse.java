package com.Asws.co.response;

import java.util.List;

import com.Asws.co.domain.Attendence;

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
public class StudentAttendenceResponse {

    private Integer total;
    private List<Attendence> attendences;


    public static StudentAttendenceResponse from(List<Attendence> services) {
        StudentAttendenceResponse sdr = StudentAttendenceResponse.builder()
                .build();
        sdr.setAttendences(services);
        return sdr;
    }

}
