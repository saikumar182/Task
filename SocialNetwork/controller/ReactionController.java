package com.example.SocialNetwork.controller;

import com.example.SocialNetwork.dto.EmojiDTO;
import com.example.SocialNetwork.entity.Emoji;
import com.example.SocialNetwork.entity.Post;
import com.example.SocialNetwork.entity.Reaction;
import com.example.SocialNetwork.entity.User;
import com.example.SocialNetwork.exception.PostNotFoundException;
import com.example.SocialNetwork.exception.UserNotFoundException;
import com.example.SocialNetwork.service.PostService;
import com.example.SocialNetwork.service.ReactionService;
import com.example.SocialNetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/reaction")
public class ReactionController {


    @Autowired
    private PostService postService;

    @Autowired

    private UserService userService;

    @Autowired

    private ReactionService reactionService;

    @PostMapping("/{postId}/emojis")
    public ResponseEntity<String> addReaction(
            @RequestParam Long userId,
            @RequestParam Long postId,
            @RequestParam Emoji emoji) {

        try {
            Post post = postService.findById(postId);
            User user = userService.findById(userId);

            postService.addEmojiToPost(post.getId(), user.getId(), emoji);

            return ResponseEntity.ok("Emoji adding successfully");
        } catch (PostNotFoundException | UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error occurred");
        }
        

    }
}
