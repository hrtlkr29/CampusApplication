package com.example.pc.campusapplication;

public class AcademicCourse {
    private int imageView;

    public AcademicCourse(int imageView) {
        this.setImageView(imageView);
    }

    public AcademicCourse() {
        this.setImageView(0);
    }


    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }
}
