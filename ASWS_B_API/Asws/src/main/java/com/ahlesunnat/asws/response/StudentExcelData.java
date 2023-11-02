package com.ahlesunnat.asws.response;

import java.util.Date;


public class StudentExcelData {
    // Fields for student_details
    private  byte[] imageData;
    public StudentExcelData(byte[] imageData) {
        this.imageData = imageData;
    }
    public byte[] getImageData() {
        return imageData;
    }
    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
    private Long teacherId;
    public StudentExcelData(Long teacherId) {
        this.teacherId = teacherId;
    }
    public Long getTeacherId() {
        return teacherId;
    }
    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String placeOfBirth;
    private String schoolName;
    private String email;
    private Long phone;
    private String address;
    private String state;
    private Integer pinCode;
    private String city;
    private Long adharNumber;
    private Date admissionDate;
    private String adharCardImage;

    public String getAdharCardImage() {
        return adharCardImage;
    }

    public void setAdharCardImage(String adharCardImage) {
        this.adharCardImage = adharCardImage;
    }
    public Date getAdmissionDate() {
        return admissionDate;
    }
    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }
    // Fields for sibling_information
    private boolean siblingStuding;
    private String brOrSis;
    private String siblingFullName;
    private Integer sibAge;
    private Integer sibStandard;
    private String siblingSchool;

    // Fields for family_information
    private String parentalStatus;
    private String parentFirstName;
    private String parentLastName;
    private String parentEmail;
    private Long parentPhone;
    private String parentOccupation;
    public StudentExcelData(String firstName, String lastName, String dateOfBirth, String placeOfBirth,
            String schoolName, String email, Long phone, String address, String state, Integer pinCode, String city,
            Long adharNumber, boolean siblingStuding, String brOrSis, String siblingFullName, Integer sibAge,
            Integer sibStandard, String siblingSchool, String parentalStatus, String parentFirstName,
            String parentLastName, String parentEmail, Long parentPhone, String parentOccupation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.placeOfBirth = placeOfBirth;
        this.schoolName = schoolName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.state = state;
        this.pinCode = pinCode;
        this.city = city;
        this.adharNumber = adharNumber;
        this.siblingStuding = siblingStuding;
        this.brOrSis = brOrSis;
        this.siblingFullName = siblingFullName;
        this.sibAge = sibAge;
        this.sibStandard = sibStandard;
        this.siblingSchool = siblingSchool;
        this.parentalStatus = parentalStatus;
        this.parentFirstName = parentFirstName;
        this.parentLastName = parentLastName;
        this.parentEmail = parentEmail;
        this.parentPhone = parentPhone;
        this.parentOccupation = parentOccupation;
    }
    public StudentExcelData() {
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public String getPlaceOfBirth() {
        return placeOfBirth;
    }
    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }
    public String getSchoolName() {
        return schoolName;
    }
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Long getPhone() {
        return phone;
    }
    public void setPhone(Long phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public Integer getPinCode() {
        return pinCode;
    }
    public void setPinCode(Integer pinCode) {
        this.pinCode = pinCode;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public Long getAdharNumber() {
        return adharNumber;
    }
    public void setAdharNumber(Long adharNumber) {
        this.adharNumber = adharNumber;
    }
    public boolean isSiblingStuding() {
        return siblingStuding;
    }
    public void setSiblingStuding(boolean siblingStuding) {
        this.siblingStuding = siblingStuding;
    }
    public String getBrOrSis() {
        return brOrSis;
    }
    public void setBrOrSis(String brOrSis) {
        this.brOrSis = brOrSis;
    }
    public String getSiblingFullName() {
        return siblingFullName;
    }
    public void setSiblingFullName(String siblingFullName) {
        this.siblingFullName = siblingFullName;
    }
    public Integer getSibAge() {
        return sibAge;
    }
    public void setSibAge(Integer sibAge) {
        this.sibAge = sibAge;
    }
    public Integer getSibStandard() {
        return sibStandard;
    }
    public void setSibStandard(Integer sibStandard) {
        this.sibStandard = sibStandard;
    }
    public String getSiblingSchool() {
        return siblingSchool;
    }
    public void setSiblingSchool(String siblingSchool) {
        this.siblingSchool = siblingSchool;
    }
    public String getParentalStatus() {
        return parentalStatus;
    }
    public void setParentalStatus(String parentalStatus) {
        this.parentalStatus = parentalStatus;
    }
    public String getParentFirstName() {
        return parentFirstName;
    }
    public void setParentFirstName(String parentFirstName) {
        this.parentFirstName = parentFirstName;
    }
    public String getParentLastName() {
        return parentLastName;
    }
    public void setParentLastName(String parentLastName) {
        this.parentLastName = parentLastName;
    }
    public String getParentEmail() {
        return parentEmail;
    }
    public void setParentEmail(String parentEmail) {
        this.parentEmail = parentEmail;
    }
    public Long getParentPhone() {
        return parentPhone;
    }
    public void setParentPhone(Long parentPhone) {
        this.parentPhone = parentPhone;
    }
    public String getParentOccupation() {
        return parentOccupation;
    }
    public void setParentOccupation(String parentOccupation) {
        this.parentOccupation = parentOccupation;
    }


  
    // Add getters and setters for all the fields
    // ...
}
