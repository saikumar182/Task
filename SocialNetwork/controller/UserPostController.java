package com.example.SocialNetwork.controller;

import com.example.SocialNetwork.dto.CommentDto;
import com.example.SocialNetwork.dto.PostDto;
import com.example.SocialNetwork.entity.Comment;
import com.example.SocialNetwork.entity.Post;
import com.example.SocialNetwork.entity.User;
import com.example.SocialNetwork.service.CommentService;
import com.example.SocialNetwork.service.PostService;
import com.example.SocialNetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/user-posts")
public class UserPostController {
    @Autowired
    private  UserService userService;
    @Autowired
    private  PostService postService;
    @Autowired
    private  CommentService commentService;




    @GetMapping("/{userId}")
    public <CommentDTO> ResponseEntity<List<PostDto>> getUserPosts(@PathVariable Long userId) {
        User user = userService.findById(userId);

        List<Post> posts = postService.getPostsByUser(user);

        List<PostDto> postDTOs = new ArrayList<>();
        for (Post post : posts) {
            List<Comment> comments = commentService.getCommentsByPost(post);
            List<CommentDTO> commentDTOs = (List<CommentDTO>) mapCommentsToDTOs(comments);

            PostDto postDTO = new PostDto();
            postDTO.setId(post.getId());
//            postDTO.setText(post.getText());
            postDTO.setComments((List<CommentDto>) commentDTOs);

            postDTOs.add(postDTO);

        }

        return ResponseEntity.ok(postDTOs);
    }

        private List<CommentDto> mapCommentsToDTOs(List<Comment> comments) {
            List<CommentDto> commentDTOs = new ArrayList<>();
            for (Comment comment : comments) {
                CommentDto  commentDTO = new CommentDto();
                commentDTO.setId(comment.getId());
                commentDTO.setContent(comment.getContent());
                commentDTOs.add(commentDTO);
            }
            return commentDTOs;
        }
    }




