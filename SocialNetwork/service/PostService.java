package com.example.SocialNetwork.service;

import com.example.SocialNetwork.entity.*;
import com.example.SocialNetwork.exception.ResourceNotFoundException;
import com.example.SocialNetwork.repository.LikeRepository;
import com.example.SocialNetwork.repository.PostRepository;
import com.example.SocialNetwork.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post findById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));
    }

    public List<Post> getPostsByUser(User user) {
        return postRepository.findByUser(user);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public void likePost(Long userId, Long postId) {
        User user = getUserById(userId);
        Post post = getPostById(postId);

        if (hasUserLikedPost(user, post)) {
            throw new IllegalArgumentException("User has already liked this post");
        }

        saveLike(user, post);
    }

    private User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public Post getPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));
    }

    private boolean hasUserLikedPost(User user, Post post) {
        return likeRepository.existsByUserAndPost(Optional.ofNullable(user), post);
    }

    private void saveLike(User user, Post post) {
        Like like = new Like();
        like.setUser(user);
        like.setPost(post);
        likeRepository.save(like);
    }





    public Post addEmojiToPost(Long postId, Long userId, Emoji emoji) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with ID: " + postId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        Reaction reaction = new Reaction();
        reaction.setPost(post);
        reaction.setUser(user);
        reaction.setEmoji(emoji);

        post.addReaction(reaction);

        return postRepository.save(post);
    }


}
