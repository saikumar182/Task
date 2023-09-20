package com.example.SocialNetwork.controller;
import com.example.SocialNetwork.dto.EmojiDTO;
import com.example.SocialNetwork.entity.Emoji;
import com.example.SocialNetwork.entity.Post;
import com.example.SocialNetwork.entity.Reaction;
import com.example.SocialNetwork.entity.User;
import com.example.SocialNetwork.service.LikeService;
import com.example.SocialNetwork.service.PostService;
import com.example.SocialNetwork.service.UserService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private LikeService likeService;

    @Autowired
    private UserService userService;

    @PostMapping("/createPost")
    public ResponseEntity<Post> addPost(
            @RequestParam  @NotNull Long userId,
            @RequestBody  @NotBlank String content) {

        User user = userService.findById(userId);
        Post post = new Post();
        post.setUser(user);
        post.setContent(content);

        Post createdPost = postService.createPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> allPosts = postService.getAllPosts();
        return ResponseEntity.ok(allPosts);

    }


}
