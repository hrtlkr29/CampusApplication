package com.example.pc.campusapplication;

import android.net.Uri;

public class Event {
    private String id;
    private String name;
    private String date;
    private String address;
    private String description;
    private String time;
    private String thumbnail;
    private String imageUri;

    public Event() {
        this.setId("");
        this.setName("");
        this.setDate("");
        this.setAddress("");
        this.setDescription("");
        this.setThumbnail("");
        this.setTime("");
        this.setImageUri(null);
    }

    public Event(String id, String name, String date, String address, String description, String time, String thumbnail, String uri) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.address = address;
        this.description = description;
        this.time = time;
        this.thumbnail = thumbnail;
        this.imageUri = uri;
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

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
