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
@Table(name = "center")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Center {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    private String centerName;

    private String email;

    private String address;

    private String phone;

    private String masjidCommiteMemberName;

    private Integer waqtBoardNumber;
    
}
