package com.example.SocialNetwork.service;

import com.example.SocialNetwork.entity.Follow;
import com.example.SocialNetwork.entity.User;
import com.example.SocialNetwork.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FollowService {


    @Autowired
    private FollowRepository followRepository;

//    @Autowired
//    public void FollowController(UserService userService) {
//        this.userService = userService;
//    }

    @Autowired
    public FollowService(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }



    public List<Follow> findByFollower(User follower) {
        return followRepository.findByFollower(follower);
    }
    public List<Follow> findByFollowed(User user) {
        return followRepository.findByFollowed(Optional.ofNullable(user));
    }
    public List<User> getFollowersForUser(Optional<Object> user) {
        List<Follow> follows = followRepository.findByFollowed(user);
        return follows.stream().map(Follow::getFollower).collect(Collectors.toList());
    }

}
