package com.example.koun.domain;


import javax.management.relation.Role;

public enum RoleType {
    USER("USER"), ADMIN("ADMIN");

    private String name;

    private RoleType(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }


}
