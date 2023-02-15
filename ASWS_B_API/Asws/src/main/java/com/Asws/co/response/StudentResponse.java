package com.Asws.co.response;

import java.util.List;

import com.Asws.co.domain.Student;

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
public class StudentResponse {


    private Integer total;
    private List<Student> students;
  
    public static StudentResponse from(List<Student> students) {
        StudentResponse sdr = StudentResponse
        .builder().build();
        sdr.setStudents(students);
        return sdr;
    }
    
}
