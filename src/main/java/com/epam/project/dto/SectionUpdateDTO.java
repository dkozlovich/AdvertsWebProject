package com.epam.project.dto;

public class SectionUpdateDTO {
    private String name;
    private int id;

    public String getName() {
        return name;
    }

    public SectionUpdateDTO setName(String name) {
        this.name = name;
        return this;
    }

    public int getId() {
        return id;
    }

    public SectionUpdateDTO setId(int id) {
        this.id = id;
        return this;
    }
}
