package com.epam.project.dto;

public class ImageDTO {
    private int id;
    private String advertImage;

    public int getId() {
        return id;
    }

    public ImageDTO setId(int id) {
        this.id = id;
        return this;
    }

    public String getAdvertImage() {
        return advertImage;
    }

    public ImageDTO setAdvertImage(String advertImage) {
        this.advertImage = advertImage;
        return this;
    }
}
