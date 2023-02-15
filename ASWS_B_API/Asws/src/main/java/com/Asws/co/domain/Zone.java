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
@Table(name = "zone")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Zone {
    


    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String ZoneName;



}
