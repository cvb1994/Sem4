package com.example.caovuongbach_project;

public class FeedBack {
    private int id;
    private  String name;
    private String mail;
    private String Content;
    private String gender;
    private boolean mailReceive;

    public boolean isMailReceive() {
        return mailReceive;
    }

    public void setMailReceive(boolean mailReceive) {
        this.mailReceive = mailReceive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
