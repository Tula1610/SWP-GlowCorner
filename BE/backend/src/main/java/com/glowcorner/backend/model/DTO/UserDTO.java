package com.glowcorner.backend.model.DTO;



public class UserDTO {
    private int userID;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String skinType;
    private int loyalPoints;
    private int roleID;

    //Constructor
    public UserDTO(String fullName, String email, String phone, String address, String skinType, int loyalPoints, int roleID) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.skinType = skinType;
        this.loyalPoints = loyalPoints;
        this.roleID = roleID;
    }

    public UserDTO() {
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSkinType() {
        return skinType;
    }

    public void setSkinType(String skinType) {
        this.skinType = skinType;
    }

    public int getLoyalPoints() {
        return loyalPoints;
    }

    public void setLoyalPoints(int loyalPoints) {
        this.loyalPoints = loyalPoints;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }
}
