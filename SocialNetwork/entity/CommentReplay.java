package com.example.SocialNetwork.entity;

import jakarta.persistence.*;
@Entity
@Table(name="commentreplay")
public class CommentReplay {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "comment_id")
        private Comment comment;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;

        @Column(name = "content")
        private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setReply(String reply) {
    }

    public void setComment_id(long commentId) {
    }

    public void setUser_id(long userId) {
    }

    public void save(CommentReplay replyoncomment) {
    }
}


