package com.example.SocialNetwork.service;

import com.example.SocialNetwork.entity.Emoji;
import com.example.SocialNetwork.entity.Post;
import com.example.SocialNetwork.entity.Reaction;
import com.example.SocialNetwork.entity.User;
import com.example.SocialNetwork.exception.PostNotFoundException;
import com.example.SocialNetwork.exception.UserNotFoundException;
import com.example.SocialNetwork.repository.PostRepository;
import com.example.SocialNetwork.repository.ReactionRepository;
import com.example.SocialNetwork.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ReactionService {


    @Autowired
     private PostRepository postRepository;

    @Autowired

    private ReactionRepository reactionRepository;

    @Autowired

    private UserRepository userRepository;


    public Reaction addEmojiToPost(Long postId, Long userId, Emoji emoji)  {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with ID: " + postId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        Reaction reaction = new Reaction();
        reaction.setPost(post);
        reaction.setUser(user);
        reaction.setEmoji(emoji);

        return reactionRepository.save(reaction);
    }

}
