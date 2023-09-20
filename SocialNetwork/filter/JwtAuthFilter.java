package com.example.SocialNetwork.filter;
import com.example.SocialNetwork.service.JwtService;
import com.example.SocialNetwork.service.UserInfoUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserInfoUserDetailsService userInfoUserDetailsService;
    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
             String token =null;
            String username= null;
        String header = request.getHeader("Authorization");

        if(null != header && header.startsWith("Bearer ")) {

          token = header.substring(7);


         username= jwtService.extractUsername(token);

        }

        if(null!= username && SecurityContextHolder.getContext().getAuthentication()==null){

     UserDetails userDetails =userInfoUserDetailsService.loadUserByUsername(username);

     if (jwtService.validateToken(token,userDetails));

            UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
            userToken.setDetails(new WebAuthenticationDetails(request));

            SecurityContextHolder.getContext().setAuthentication(userToken);

        }
        filterChain.doFilter(request,response);

        }


    }

