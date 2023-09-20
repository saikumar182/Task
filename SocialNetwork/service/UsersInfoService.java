package com.example.SocialNetwork.service;

import com.example.SocialNetwork.entity.UsersInfo;
import com.example.SocialNetwork.repository.UsersInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersInfoService {

 @Autowired
 private  UsersInfoRepository usersConfigRepository;

 @Autowired
 private  PasswordEncoder encoder;

   public  UsersInfo saveUser(UsersInfo usersInfo){
       usersInfo.setPassword(encoder.encode(usersInfo.getPassword()
       ));

       return usersConfigRepository.save(usersInfo);
   }

}
