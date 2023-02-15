package com.Asws.co.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "demo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class demo {

    @Id
    @GenericGenerator(name = "STD_id",strategy = "com.Asws.co.config.CustomIdGenerator")
    @GeneratedValue(generator = "STD_id")
    @Column(name = "id_demo")
    private String id;

    @Column(name = "name")
    private String name;

    // @Column(name = "center")
    // private CenterResponse center;

    @Column(name = "image")
    @Lob
    private byte[] image;


    @OneToMany(mappedBy = "demo", cascade = CascadeType.ALL)
    private List<demoImages> imagens;

    // @Convert(converter = CustomHashMapConverter.class)
    // @Column(columnDefinition = "json")
    // private Map<String, Object> view_json;


}
