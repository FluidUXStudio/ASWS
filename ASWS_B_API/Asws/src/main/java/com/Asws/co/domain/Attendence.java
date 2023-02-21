package com.Asws.co.domain;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "attendence")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Attendence {

    @Id
    @GenericGenerator(name = "STD_id",strategy = "com.Asws.co.config.CustomIdGenerator")
    @GeneratedValue(generator = "STD_id")
    @Column(name = "id_attendence")
    private String id;
    
    @Column(name = "name")
    private String name;

    private String studentId;

    
    @Column(name="date")
    private LocalDateTime dateTime;

    @Column(name = "leave")
    private String leave;

    @Column(name = "presentAndAbsent")
    private String presentAndAbsent;
    
}
