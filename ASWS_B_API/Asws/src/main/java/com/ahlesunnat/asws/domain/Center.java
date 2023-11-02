package com.ahlesunnat.asws.domain;

import java.util.List;
import java.util.Map;

import com.ahlesunnat.asws.config.JsonbConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Center {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
        
    @Convert(converter = JsonbConverter.class)
    @Column(columnDefinition = "json")
    private Map<String, Object> center_information;
    
    @Lob
    @Column(name = "center_images" , columnDefinition = "LONGBLOB")
    private List<byte[]> images;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "zone_id")
    private Zone zone;

    @JsonIgnore
    @OneToMany(mappedBy = "center", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Student> students;

    @JsonIgnore
    @OneToMany(mappedBy = "center", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Teacher> teachers;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "center_volunteer", joinColumns = @JoinColumn(name = "center_id"), inverseJoinColumns = @JoinColumn(name = "volunteer_id"))
    private List<Volunteer> volunteers;

    public Center() {
    }

    public Center(Long id, Map<String, Object> center_information, List<byte[]> images, Zone zone,
            List<Student> students, List<Teacher> teachers, List<Volunteer> volunteers) {
        this.id = id;
        this.center_information = center_information;
        this.images = images;
        this.zone = zone;
        this.students = students;
        this.teachers = teachers;
        this.volunteers = volunteers;
    }

    public Center(Long id, Zone zone) {
        this.id = id;
        this.zone = zone;
    }

    public Center(Long id) {
        this.id = id;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<String, Object> getCenter_information() {
        return center_information;
    }

    public void setCenter_information(Map<String, Object> center_information) {
        this.center_information = center_information;
    }

    public List<byte[]> getImages() {
        return images;
    }

    public void setImages(List<byte[]> images) {
        this.images = images;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Volunteer> getVolunteers() {
        return volunteers;
    }

    public void setVolunteers(List<Volunteer> volunteers) {
        this.volunteers = volunteers;
    }

    // Getters and setters
}