package com.ahlesunnat.asws.response;

import java.util.Map;



public class StudentResponse {

    private Long id;
    private byte[] imageData;
    private Map<String, Object> student_details;
    private Map<String, Object> sibling_information;
    private Map<String, Object> family_information;
    public StudentResponse(Long id, byte[] imageData, Map<String, Object> student_details,
            Map<String, Object> sibling_information, Map<String, Object> family_information) {
        this.id = id;
        this.imageData = imageData;
        this.student_details = student_details;
        this.sibling_information = sibling_information;
        this.family_information = family_information;
    }
    public StudentResponse() {
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

    
}
