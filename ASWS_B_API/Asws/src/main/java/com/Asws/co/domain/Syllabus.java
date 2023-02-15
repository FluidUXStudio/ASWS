package com.Asws.co.domain;

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
@Table(name="syllabus")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Syllabus {


    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String studentName;
    @Column(name = "studentId" ,unique = true)
    private String StudentId;

    private String page1;

    private String page2;

    private String page3;

    private String page4;

    private String page5;

    private String page6;

    private String page7;

    private String page8;

    private String page9;

    private String page10;



    
}
