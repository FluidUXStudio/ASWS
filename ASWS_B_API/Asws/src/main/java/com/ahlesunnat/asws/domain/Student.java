package com.ahlesunnat.asws.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ahlesunnat.asws.config.JsonbConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Lob
    @Column(name = "image_data",columnDefinition = "LONGBLOB")
    private byte[] imageData;
    
    @Convert(converter = JsonbConverter.class)
    @Column(columnDefinition = "json")
    private Map<String, Object> student_details;

    @Convert(converter = JsonbConverter.class)
    @Column(columnDefinition = "json")
    private Map<String, Object> sibling_information;


    @Convert(converter = JsonbConverter.class)
    @Column(columnDefinition = "json")
    private Map<String, Object> family_information;

    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "center_id")
    @JsonIgnore
    private Center center;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Zone zone;

    @JsonIgnore
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Attendence> attendanceRecords;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;


    public Student(Teacher teacher) {
        this.teacher = teacher;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "admissionDate")
    private Date admissionDate;

    // @ManyToMany
    // @JoinTable(name = "student_completed_chapters", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "chapter_id"))
    // private Set<Chapter> completedChapters = new HashSet<>();

   
    public Student(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public Student() {
    }

    public Student(Long id, byte[] imageData, Map<String, Object> student_details,
            Map<String, Object> sibling_information, Map<String, Object> family_information, Center center, Zone zone,
            List<Attendence> attendanceRecords, Set<Chapter> completedChapters) {
        this.id = id;
        this.imageData = imageData;
        this.student_details = student_details;
        this.sibling_information = sibling_information;
        this.family_information = family_information;
        this.center = center;
        this.zone = zone;
    }

    public Student(Long id2) {
        this.id = id2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public Map<String, Object> getStudent_details() {
        return student_details;
    }

    public void setStudent_details(Map<String, Object> student_details) {
        this.student_details = student_details;
    }

    public Map<String, Object> getSibling_information() {
        return sibling_information;
    }

    public void setSibling_information(Map<String, Object> sibling_information) {
        this.sibling_information = sibling_information;
    }

    public Map<String, Object> getFamily_information() {
        return family_information;
    }

    public void setFamily_information(Map<String, Object> family_information) {
        this.family_information = family_information;
    }

    public Center getCenter() {
        return center;
    }

    public void setCenter(Center center) {
        this.center = center;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }


   
    // Getters and setters
}