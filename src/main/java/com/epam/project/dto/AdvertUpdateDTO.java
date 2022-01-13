package com.epam.project.dto;

public class AdvertUpdateDTO {
    private int id;
    private int sectionId;
    private String name;
    private String content;
    private double cost;

    public int getId() {
        return id;
    }

    public AdvertUpdateDTO setId(int id) {
        this.id = id;
        return this;
    }

    public int getSectionId() {
        return sectionId;
    }

    public AdvertUpdateDTO setSectionId(int sectionId) {
        this.sectionId = sectionId;
        return this;
    }

    public String getName() {
        return name;
    }

    public AdvertUpdateDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getContent() {
        return content;
    }

    public AdvertUpdateDTO setContent(String content) {
        this.content = content;
        return this;
    }

    public double getCost() {
        return cost;
    }

    public AdvertUpdateDTO setCost(double cost) {
        this.cost = cost;
        return this;
    }
}
