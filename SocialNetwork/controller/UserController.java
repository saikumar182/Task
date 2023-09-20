package com.example.SocialNetwork.controller;
import com.cloudinary.Cloudinary;
import com.example.SocialNetwork.entity.Follow;
import com.example.SocialNetwork.entity.Post;
import com.example.SocialNetwork.entity.User;
import com.example.SocialNetwork.repository.UserRepository;
import com.example.SocialNetwork.service.FollowService;
import com.example.SocialNetwork.service.UserService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired

    private UserService userService;




    @Autowired
    private FollowService followService;

    public UserController(UserRepository userRepository, Cloudinary cloudinary) {
    }


    @GetMapping("/{userId}/follower-posts")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getFollowerPosts(@PathVariable @NotNull Long userId) {
        User user = userService.findById(userId);
        List<Follow> followerRelations = followService.findByFollowed(user);

        List<User> followers = followerRelations.stream()
                .map(Follow::getFollower).collect(Collectors.toList());

        List<Post> followerPosts = followers.stream()
                .flatMap(follower -> follower.getPost().stream())
                .collect(Collectors.toList());

        if (followerPosts.isEmpty()) {
            return ResponseEntity.ok("No posts available from followers.");
        }

        return ResponseEntity.ok(followerPosts);
    }

    @PostMapping("/addProfile")
    public ResponseEntity<String> newUserWithImage(@RequestParam("imageUrl") MultipartFile file,
                                                   @RequestParam("username") String username,
                                                   @RequestParam("firstname") String firstname,
                                                   @RequestParam("lastname") String lastname,
                                                   @RequestParam("email") String email
    ) throws IOException {
        return ResponseEntity.ok(userService.addNewUser(file, username, firstname, lastname, email));
    }

    @PutMapping("/{userId}/update-email")
    public ResponseEntity<User> updateEmail(
            @PathVariable Long userId,
            @RequestParam String newEmail
    ) {
        User updatedUser = userService.updateUserEmail(userId, newEmail);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/{username}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<User> getUserProfileByUsername(
            @PathVariable @NotBlank String username) {
        User user = userService.findByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/followers/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<User>> getFollowersForUser(@PathVariable @NotBlank String username) {
        User user = userService.findByUsername(username);
        List<User> followers = followService.getFollowersForUser(Optional.ofNullable(user));
        return ResponseEntity.ok(followers);
    }


    public User updateUserEmail(Long userId, String newEmail) {
     return userService.updateUserEmail(userId,newEmail);}

}