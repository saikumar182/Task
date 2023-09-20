package com.example.SocialNetwork.exception;
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String userId) {
        super("Could not find user with id: " + userId);
    }
}

