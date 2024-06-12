package com.example.minhas_financias.model.enuns;
public enum UseRoles {

	ADMIN("admin"),
    USER("user");

    private String role;

    UseRoles(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}

