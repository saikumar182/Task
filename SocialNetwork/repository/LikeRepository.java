package com.example.SocialNetwork.repository;

import com.example.SocialNetwork.entity.Like;
import com.example.SocialNetwork.entity.Post;
import com.example.SocialNetwork.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository  extends JpaRepository<Like,Long> {

    List<Like> findByUserAndPost(User user, Post post);

    List<Like> findByPost(Post post);

    boolean existsByUserAndPost(Optional<Object> user, Post post);
}
