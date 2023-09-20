package com.example.SocialNetwork.controller;
import com.example.SocialNetwork.entity.UsersInfo;
import com.example.SocialNetwork.service.UsersInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UsersInfoController {
    @Autowired
    private UsersInfoService usersInfoService;

    @PostMapping("/save")
    public String saveUser(@RequestBody UsersInfo usersInfo) {
        usersInfoService.saveUser(usersInfo);
        return "user saved";
    }



}