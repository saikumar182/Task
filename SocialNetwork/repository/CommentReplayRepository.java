package com.example.SocialNetwork.repository;

import com.example.SocialNetwork.entity.CommentReplay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentReplayRepository extends JpaRepository<CommentReplay,Long> {
}
