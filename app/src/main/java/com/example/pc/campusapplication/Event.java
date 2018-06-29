package com.example.pc.campusapplication;

public class Event {
    private String id;
    private String name;
    private String date;
    private String address;
    private String description;
    private String time;
    private String thumbnail;

    public Event() {
        this.setId("");
        this.setName("");
        this.setDate("");
        this.setAddress("");
        this.setDescription("");
        this.setThumbnail("");
        this.setTime("");
    }

    public Event(String id, String name, String date, String address, String description, String time, String thumbnail) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.address = address;
        this.description = description;
        this.time = time;
        this.thumbnail = thumbnail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
