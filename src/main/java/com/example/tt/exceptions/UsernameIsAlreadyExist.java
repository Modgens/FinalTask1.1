package com.example.tt.exceptions;

public class UsernameIsAlreadyExist extends RuntimeException {

    public UsernameIsAlreadyExist(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
