package com.example.SocialNetwork.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
//@NoArgsConstructor
@Data
@Table(name = "comments")
@EqualsAndHashCode
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @NotBlank(message = "Content must not be blank")
    private String content;

    public Comment(long l, String s) {
    }

    public Comment() {

    }

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    private List<CommentReplay> replies = new ArrayList<>();





    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public Post getPost() {
        return post;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
    }

    public void setPost(Post post) {
    }

    public void setUser(User user) {
    }


    public void setParentComment(Comment parentComment) {
    }
}
