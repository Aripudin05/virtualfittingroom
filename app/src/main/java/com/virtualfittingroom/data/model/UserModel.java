package com.virtualfittingroom.data.model;

public class UserModel {
    private String name, mail, avatar;

    public UserModel(String name, String mail, String avatar) {
        this.name = name;
        this.mail = mail;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
