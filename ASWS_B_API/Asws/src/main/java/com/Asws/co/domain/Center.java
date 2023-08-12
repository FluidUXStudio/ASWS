package com.Asws.co.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

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

    @Lob
    private byte[] photo;

    private String centerName;

    private String email;

    private String address;

    private String phone;

    private String masjidCommiteMemberName;

    private Integer waqtBoardNumber;
    
    
}
