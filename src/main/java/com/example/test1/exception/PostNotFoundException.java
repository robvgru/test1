package com.example.test1.exception;


public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(Long postId) {
        super("The post with id " + postId + " is not found");
    }
}
