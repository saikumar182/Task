package com.example.SocialNetwork.controller;

import com.example.SocialNetwork.entity.User;
import com.example.SocialNetwork.pojo.AuthRequest;
import com.example.SocialNetwork.service.FollowService;
import com.example.SocialNetwork.service.JwtService;
import com.example.SocialNetwork.service.UserService;
import jakarta.validation.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class FollowController {

    @Autowired
    private UserService userService;
    @Autowired
    private FollowService followService;

    @Autowired

    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @GetMapping("/{username}/followers")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PreAuthorize("hasRole('ROLE_ADMIN')")

    public ResponseEntity<List<User>> getFollowersForUser(@PathVariable @NotBlank String username) {
        User user = userService.findByUsername(username);
        List<User> followers = followService.getFollowersForUser(Optional.ofNullable(user));

        return ResponseEntity.ok(followers);
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        }else {
            throw new UsernameNotFoundException("Invalid User");
        }
    }



}
