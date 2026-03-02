package dev.jvsmaior.blog_api.entity;

public enum UserRole {

    ADMIN("admin"),
    USER("user");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    String getRole(){
        return role;
    }
}
