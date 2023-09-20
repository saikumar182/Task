package com.example.SocialNetwork.repository;

import com.example.SocialNetwork.entity.Comment;
import com.example.SocialNetwork.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {


    List<Comment> findByPostId(Long postId);

    List<Comment> findByPost(Post post);
}