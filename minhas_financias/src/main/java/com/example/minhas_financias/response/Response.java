package com.example.minhas_financias.response;


import com.example.minhas_financias.model.enuns.ResponseStatusEnum;

import lombok.Data;

@Data
public class Response<T> {

    private T data;
    private String status;
    private String message;

    public Response() {
    	super();
    }

    public Response(ResponseStatusEnum status, T data) {
        this.status = status.getDescription();
        this.data = data;
    }

    public Response(ResponseStatusEnum status, T data, String message) {
        this.status = status.getDescription();
        this.data = data;
        this.message = message;
    }

    public Response(ResponseStatusEnum status, String message) {
        this.status = status.getDescription();
        this.message = message;
    }

    public void setStatus(ResponseStatusEnum status) {
        this.status = status.getDescription();
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
