package com.example.SocialNetwork.exception;

public class CommentNotFoundException extends RuntimeException{

    public CommentNotFoundException(String commentId) {
        super("Could not find post with id: " + commentId);
    }

}
