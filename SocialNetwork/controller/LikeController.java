package com.example.SocialNetwork.controller;

import com.example.SocialNetwork.entity.Like;
//import com.example.SocialNetwork.entity.LikeRequest;
import com.example.SocialNetwork.entity.Post;
import com.example.SocialNetwork.entity.User;
import com.example.SocialNetwork.service.LikeService;
import com.example.SocialNetwork.service.PostService;
import com.example.SocialNetwork.service.UserService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class LikeController {

    @Autowired
    private LikeService likeService;
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @PostMapping("/like")
    public ResponseEntity<String> likePost(
            @RequestParam  @NotNull Long userId,
            @RequestParam  @NotNull Long postId) {
        try {
            likeService.likePost(userId, postId);
            return ResponseEntity.ok("Post liked");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}



