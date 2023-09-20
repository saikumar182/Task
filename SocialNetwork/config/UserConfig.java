package com.example.SocialNetwork.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
@Configuration
public class UserConfig {


    private final String CLOUD_NAME="def8qtenp";
    private final String API_KEY="586544423791674";
    private final String API_SECRET="2U0WDJ1LsodiIp9z7_rnlX4W7jA";

    @Bean
    public Cloudinary cloudinary(){
        Map<String,String> config=new HashMap<>();
        config.put("cloud_name",CLOUD_NAME);
        config.put("api_key",API_KEY);
        config.put("api_secret",API_SECRET);
        return new Cloudinary(config);
    }

}
