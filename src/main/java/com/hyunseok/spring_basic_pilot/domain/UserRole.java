package com.hyunseok.spring_basic_pilot.domain;

public enum UserRole {
    ROLE_ADMIN("관리자"),
    ROLE_USER("일반 사용자");
    
    private final String description;
    
    UserRole(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getAuthority() {
        return this.name();  // "ROLE_ADMIN", "ROLE_USER"
    }
}
