package com.epam.project.dto;

public class SectionDTO {
    private int id;
    private String name;
    private int totalAdvertsOfSectionNumber;

    public int getId() {
        return id;
    }

    public SectionDTO setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SectionDTO setName(String name) {
        this.name = name;
        return this;
    }

    public int getTotalAdvertsOfSectionNumber() {
        return totalAdvertsOfSectionNumber;
    }

    public SectionDTO setTotalAdvertsOfSectionNumber(int totalAdvertsOfSectionNumber) {
        this.totalAdvertsOfSectionNumber = totalAdvertsOfSectionNumber;
        return this;
    }
}
