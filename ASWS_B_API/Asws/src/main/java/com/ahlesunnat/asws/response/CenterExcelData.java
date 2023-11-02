package com.ahlesunnat.asws.response;

public class CenterExcelData {
    // Other existing fields for student, sibling, and family information
    private String centerName;
    private String email;
    private String address;
    private Long phone;
    private String masjidCommiteMemberName;
    private Integer waqtBoardNumber;
    public CenterExcelData(String centerName, String email, String address, Long phone,
            String masjidCommiteMemberName, Integer waqtBoardNumber) {
        this.centerName = centerName;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.masjidCommiteMemberName = masjidCommiteMemberName;
        this.waqtBoardNumber = waqtBoardNumber;
    }
    public CenterExcelData() {
    }
    public String getCenterName() {
        return centerName;
    }
    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Long getPhone() {
        return phone;
    }
    public void setPhone(Long phone) {
        this.phone = phone;
    }
    public String getMasjidCommiteMemberName() {
        return masjidCommiteMemberName;
    }
    public void setMasjidCommiteMemberName(String masjidCommiteMemberName) {
        this.masjidCommiteMemberName = masjidCommiteMemberName;
    }
    public Integer getWaqtBoardNumber() {
        return waqtBoardNumber;
    }
    public void setWaqtBoardNumber(Integer waqtBoardNumber) {
        this.waqtBoardNumber = waqtBoardNumber;
    }

    // Add getters and setters for the new center fields
}
