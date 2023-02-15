package com.Asws.co.domain;

import java.sql.Date;
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
@Table(name = "events")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Events {


    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String title;

    private Time selectTime;

    private Date selectDate;

    private String description;

    
    
}
