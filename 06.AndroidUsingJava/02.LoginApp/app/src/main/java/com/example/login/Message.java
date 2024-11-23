package com.example.login;

public class Message {
    private String message;
    private String Phone;

    public Message() {

    }
    public Message(String message, String phone) {
        this.message = message;
        Phone = phone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}