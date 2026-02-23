package com.ecommerce.authservice.dto;

import com.ecommerce.authservice.model.Role;

public class SignUpResponse {
    private String username;
    private Role role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
