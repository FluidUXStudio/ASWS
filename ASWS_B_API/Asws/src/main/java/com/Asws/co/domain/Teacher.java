package com.Asws.co.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "teacher")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Lob
    private byte[] photo;

   
    private String firstName;

    private String lastName;

    // @Column(unique=true)
    private String email;

   
    private Long phone;

    private String address;

    private String dateOfBirth;

    private String placeOfBirth;

    private Long adharNumber;

    //Education


    private String university;

    private String degree;

    private String startDate;

    private String endDate;

    private String city;
}
