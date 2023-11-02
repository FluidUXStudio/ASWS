package com.ahlesunnat.asws.response;


public class TeacherExcelData {
    private String firstName;
    private String lastName;
    private String email;
    private Long phone;
    private String address;
    private String dateOfBirth;
    private String placeOfBirth;
    private Long adharNumber;
    private String university;
    private String degree;
    private String startDate;
    private String endDate;
    private String city;
    public TeacherExcelData(String firstName, String lastName, String email, Long phone, String address,
            String dateOfBirth, String placeOfBirth, Long adharNumber, String university, String degree,
            String startDate, String endDate, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.placeOfBirth = placeOfBirth;
        this.adharNumber = adharNumber;
        this.university = university;
        this.degree = degree;
        this.startDate = startDate;
        this.endDate = endDate;
        this.city = city;
    }
    public TeacherExcelData() {
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
    public Long getAdharNumber() {
        return adharNumber;
    }
    public void setAdharNumber(Long adharNumber) {
        this.adharNumber = adharNumber;
    }
    public String getUniversity() {
        return university;
    }
    public void setUniversity(String university) {
        this.university = university;
    }
    public String getDegree() {
        return degree;
    }
    public void setDegree(String degree) {
        this.degree = degree;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    // Getters and setters for the above fields
    // ...
}
