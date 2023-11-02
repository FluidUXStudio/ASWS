package com.ahlesunnat.asws.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  
    @Lob
    @Column(name = "teacher_image" , columnDefinition = "LONGBLOB")
    private byte[] imageData;
    
    @Convert(converter = JsonbConverter.class)
    @Column(columnDefinition = "json")
    private Map<String, Object> teacher_details;


    @Convert(converter = JsonbConverter.class)
    @Column(columnDefinition = "json")
    private Map<String, Object> education_details;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "center_id")
    private Center center;

    @ManyToOne(fetch = FetchType.EAGER)
    private Zone zone;

   
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TeacherAttendence> teacherAttendences = new ArrayList<>();


    
    // @JsonIgnore
    // @OneToMany(mappedBy = "Student")
    // private List<Student> students = new ArrayList<>();

    // public Teacher(List<Student> students) {
    //     this.students = students;
    // }


    public Teacher(Long teacherId) {
        this.id = teacherId;
    }


    // public List<Student> getStudents() {
    //     return students;
    // }


    // public void setStudents(List<Student> students) {
    //     this.students = students;
    // }


    public Teacher(Long id, byte[] imageData, Map<String, Object> teacher_details,
            Map<String, Object> education_details, Center center, Zone zone,
            List<TeacherAttendence> teacherAttendences) {
        this.id = id;
        this.imageData = imageData;
        this.teacher_details = teacher_details;
        this.education_details = education_details;
        this.center = center;
        this.zone = zone;
        this.teacherAttendences = teacherAttendences;
    }


    public Teacher() {
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


    public Map<String, Object> getTeacher_details() {
        return teacher_details;
    }


    public void setTeacher_details(Map<String, Object> teacher_details) {
        this.teacher_details = teacher_details;
    }


    public Map<String, Object> getEducation_details() {
        return education_details;
    }


    public void setEducation_details(Map<String, Object> education_details) {
        this.education_details = education_details;
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


    public List<TeacherAttendence> getTeacherAttendences() {
        return teacherAttendences;
    }


    public void setTeacherAttendences(List<TeacherAttendence> teacherAttendences) {
        this.teacherAttendences = teacherAttendences;
    }

    // Getters and setters
}
