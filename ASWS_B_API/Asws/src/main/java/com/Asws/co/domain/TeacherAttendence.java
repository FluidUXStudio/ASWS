package com.Asws.co.domain;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "teacher_attendence")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherAttendence {
    

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String teacherId;

    private String teacherName;

    private Time checkin;

    private Time checkOut;

    private Time workingHours;


}
