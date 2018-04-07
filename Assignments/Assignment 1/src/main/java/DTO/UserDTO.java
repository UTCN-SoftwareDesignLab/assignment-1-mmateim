package DTO;

import model.Role;

import java.util.List;

public class UserDTO {
    private String username;
    private String roles;

    public UserDTO(String username, String roles) {
        this.username = username;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public String getRoles() {
        return roles;
    }
}
