package com.epam.project.model;

import java.awt.image.BufferedImage;

public class Image {
    private int id;
    private int advertId;
    private BufferedImage advertImage;

    public int getId() {
        return id;
    }

    public Image setId(int id) {
        this.id = id;
        return this;
    }

    public int getAdvertId() {
        return advertId;
    }

    public Image setAdvertId(int advertId) {
        this.advertId = advertId;
        return this;
    }

    public BufferedImage getAdvertImage() {
        return advertImage;
    }

    public Image setAdvertImage(BufferedImage advertImage) {
        this.advertImage = advertImage;
        return this;
    }
}
