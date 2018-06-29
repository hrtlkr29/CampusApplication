package com.example.pc.campusapplication;

public class Event {
    private String name;
    private String date;
    private String address;
    private String description;
    private String time;
    private int thumbnail;

    public Event() {
        this.setName("");
        this.setDate("");
        this.setAddress("");
        this.setDescription("");
        this.setThumbnail(0);
        this.setTime("");
    }

    public Event(String name, String date, String address, String description, String time, int thumbnail) {
        this.name = name;
        this.date = date;
        this.address = address;
        this.description = description;
        this.time = time;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
