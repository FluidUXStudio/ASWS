package com.Asws.co.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "student")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student {


    @Id
    @GenericGenerator(name = "STD_id",strategy = "com.Asws.co.config.CustomIdGenerator")
    @GeneratedValue(generator = "STD_id")
    @Column(name = "id")
    private String id;

    @Lob
    private byte[] photo;

    private String zone;
    private String CenterName;

    private String firstName;
    private String lastName;

    private String dateOfBirth;
    private String placeOfBirth;

    private String SchoolName;

    @Column(unique=true)
    private String email;

    private Long phone;

    private String address;

    private String state;

    private Integer pinCode;

    private String city;

    private Long adharNumber;


    private boolean siblingStuding;

    private String brOrSis;

    private String siblingFullName;

    private Integer sibAge;

    private String sibStandard;

    private String siblingSchool;

    private String parentalStatus;

    private String parentFirstName;
    private String parentLastName;


    private String ParentEmail;

    private Long parentPhone;

    private String parentOccupation;

    
}
