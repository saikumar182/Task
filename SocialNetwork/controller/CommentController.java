package com.example.SocialNetwork.controller;
import com.example.SocialNetwork.dto.CommentDto;
import com.example.SocialNetwork.dto.ReplyDto;
import com.example.SocialNetwork.entity.*;
import com.example.SocialNetwork.service.CommentReplayService;
import com.example.SocialNetwork.service.CommentService;
import com.example.SocialNetwork.service.PostService;
import com.example.SocialNetwork.service.UserService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

@Autowired

private CommentReplayService commentReplayService;
    @Autowired

    private UserService userService;

    @Autowired
    private PostService postService;
    @PostMapping("/addComment")
    public ResponseEntity<Comment> addCommentToPost(
            @RequestParam @NotNull Long userId,
            @RequestParam @NotNull Long postId,
            @RequestBody @NotBlank String content) {

        User user = userService.findById(userId);
        Post post = postService.findById(postId);

        Comment comment = new Comment();
        comment.setUser(user);
        comment.setPost(post);
        comment.setContent(content);

        Comment createdComment = commentService.createComment(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }

    @GetMapping("/{postId}/comments")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Comment>> getCommentsForPost(@PathVariable  @NotNull Long postId) {

        List<Comment> comments = commentService.getCommentsForPost(postId);
        return ResponseEntity.ok(comments);
        
    }
    @PostMapping("/{commentId}/replies")
    public ResponseEntity<String> replyToComment(@PathVariable Long commentId, @RequestBody CommentDto commentDto) {
        commentService.replyToComment(commentId, commentDto);
        return ResponseEntity.ok("Reply added successfully");
    }


    @PostMapping("/addreplyoncomment")
    public CommentReplay addReply(@RequestBody ReplyDto reply)  {
        return commentReplayService.replyonComment(reply);
    }

}