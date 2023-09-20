package com.example.SocialNetwork.repository;

import com.example.SocialNetwork.entity.UsersInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersInfoRepository extends JpaRepository<UsersInfo,Long> {


  public Optional<UsersInfo> findByUsername(String username);
}
