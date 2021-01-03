package com.example.chat_1.Model;

public class User {
    private String id,UserName,UserPhone,status;

    public User(String id, String userName, String userPhone,String status) {
        this.id = id;
        this.UserName = userName;
        this.UserPhone = userPhone;
        this.status=status;
    }

    public User() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        this.UserName = userName;
    }

    public String getUserPhone() {
        return UserPhone;
    }

    public void setUserPhone(String userPhone) {
        this.UserPhone = userPhone;
    }
}
