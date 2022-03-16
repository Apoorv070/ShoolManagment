package com.Service.User.exception;

public class UserNotfound extends RuntimeException{
    private String message;

    public UserNotfound(){
        super();
    }

    public UserNotfound(String message) {
        super(message);
        this.message = message;
    }
}

