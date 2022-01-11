package com.epam.project.dto;

public class UserDTO {

    private int id;

    private String username;

    private String password;

    private boolean isAdmin;

    public int getId() {
        return id;
    }

    public UserDTO setId(int id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public UserDTO setAdmin(boolean admin) {
        isAdmin = admin;
        return this;
    }
}
