package com.example.SocialNetwork.service;

import com.example.SocialNetwork.config.UserInfoUserDetails;
import com.example.SocialNetwork.entity.UsersInfo;
import com.example.SocialNetwork.repository.UsersInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersInfoRepository usersInfoRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsersInfo> usersInfo= usersInfoRepository.findByUsername(username);
        return usersInfo.map(UserInfoUserDetails::new).orElseThrow(()->new UsernameNotFoundException("user not found"));
    }
}
