package com.Service.School.exception;

public class SchoolNotfound extends RuntimeException{
    private String message;

    public SchoolNotfound(){
        super();
    }

    public SchoolNotfound(String message) {
        super(message);
        this.message = message;
    }
}

