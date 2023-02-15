package com.Asws.co.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "demo_images")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class demoImages {



    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id_imagem;

    
    private String name;

    private String type;

    @Lob
    private byte[] imagem;


    

    @ManyToOne
    @JoinColumn(name = "id_demo", referencedColumnName = "id_demo")
    private demo demo;



    public demoImages(String name, String type, byte[] imagem) {
        this.name = name;
        this.type = type;
        this.imagem = imagem;
    }
    
}
