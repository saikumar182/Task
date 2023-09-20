package com.example.SocialNetwork.exception;
public class PostNotFoundException extends RuntimeException {

    public PostNotFoundException(Long postId) {
        super("Could not find post with id: " + postId);
    }
}


