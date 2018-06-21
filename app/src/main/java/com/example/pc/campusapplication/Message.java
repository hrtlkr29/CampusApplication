package com.example.pc.campusapplication;

public class Message {

    private String username;
    private String text;

    public Message(){
        this.setUsername("");
        this.setText("");
    }

    public Message(String username, String text) {
        this.setUsername(username);
        this.setText(text);
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
