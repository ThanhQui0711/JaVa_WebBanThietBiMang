package com.mycompany.dto;

import org.springframework.http.HttpStatus;

public class Result<T> {

    private Boolean isError;
    private String message;
    private T data;

    public Result() {}

    public Result(Boolean isError, String message, T data) {
        this.isError = isError;
        this.message = message;
        this.data = data;
    }

    public Boolean getError() {
        return isError;
    }

    public void setError(Boolean error) {
        isError = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
