package com.example.SocialNetwork.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;
public class PostDto {

    private Long id;
    private String text;
    private List<CommentDto> comments;

//    public PostDto(Long id, String text, List<CommentDto> comments) {
//        this.id = id;
//        this.text = text;
//        this.comments = comments;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }

    public PostDto() {
    }
}
