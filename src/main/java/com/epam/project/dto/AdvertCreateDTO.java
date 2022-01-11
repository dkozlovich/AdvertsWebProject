package com.epam.project.dto;

public class AdvertCreateDTO {
    private int sectionId;
    private int userId;
    private String name;
    private String content;
    private double cost;

    public int getSectionId() {
        return sectionId;
    }

    public AdvertCreateDTO setSectionId(int sectionId) {
        this.sectionId = sectionId;
        return this;
    }

    public int getUserId() {
        return userId;
    }

    public AdvertCreateDTO setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public String getName() {
        return name;
    }

    public AdvertCreateDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getContent() {
        return content;
    }

    public AdvertCreateDTO setContent(String content) {
        this.content = content;
        return this;
    }

    public double getCost() {
        return cost;
    }

    public AdvertCreateDTO setCost(double cost) {
        this.cost = cost;
        return this;
    }
}
