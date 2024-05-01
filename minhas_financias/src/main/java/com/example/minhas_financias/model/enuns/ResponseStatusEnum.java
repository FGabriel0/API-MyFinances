package com.example.minhas_financias.model.enuns;

import lombok.Getter;

@Getter
public enum ResponseStatusEnum {

    SUCCESS("success"), 
    ERROR("danger"), 
    INFO("info"), 
    WARNING("warning");

    private String description;

    private ResponseStatusEnum(String description) {
        this.description = description;
    }
}
