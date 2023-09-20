package com.example.SocialNetwork.repository;

import com.example.SocialNetwork.dto.ReplyDto;
import com.example.SocialNetwork.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<Object> findByUsername(String username);
    
    boolean existsByEmail(String newEmail);

}
