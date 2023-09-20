package com.example.SocialNetwork.service;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.SocialNetwork.entity.User;
import com.example.SocialNetwork.exception.InvalidEmailFormatException;
import com.example.SocialNetwork.exception.ResourceNotFoundException;
import com.example.SocialNetwork.repository.FollowRepository;
import com.example.SocialNetwork.repository.PostRepository;
import com.example.SocialNetwork.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private FollowService followService;

    @Autowired
    private FollowRepository followRepository;



    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + userId));
    }

    public User findByUsername(String username) {
        return (User) userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));
    }
    public String addNewUser(MultipartFile file, String username, String firstname, String lastname, String email) throws IOException {
        try {
            String imageUrl = null;
            if (file != null && !file.isEmpty()) {
                Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
                imageUrl = (String) uploadResult.get("secure_url");
            }
            List<String> errors = new ArrayList<>();

            if (!isValidUsername(username)) {
                errors.add("Invalid format.Please provide a valid username.");

            }

            if (!isValidName(firstname)) {
                errors.add("Invalid first name format.Please provide a valid first name.");
            }

            if (!isValidName(lastname)) {
                errors.add("Invalid last name format.Please provide a valid last name.");
            }

            if (!isValidEmail(email)) {
                errors.add("Invalid email format.Please provide a valid email.");
            }
            if (!errors.isEmpty()) {
                return String.join("\n", errors);
            }
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setImageUrl(imageUrl);
            user.setLastName(lastname);
            user.setFirstName(firstname);
            userRepository.save(user);

            return "Profile updated successfully";
        } catch (IOException e) {
            return "Error: Invalid file format or file upload failed. Please check the file and try again.";
        }
    }

    private boolean isValidUsername(String username) {
        return username.matches("^[a-zA-Z0-9_]+$");
    }

    private boolean isValidName(String name) {
        return name.matches("^[a-zA-Z]+$");
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }



    public User updateUserEmail(Long userId, String newEmail) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
        if (newEmail == null) {
            throw new IllegalArgumentException("Email cannot be null");
        } if (!isValidEmail(newEmail)) {
            throw new InvalidEmailFormatException("Invalid email format");
        }

        if (userRepository.existsByEmail(newEmail)) {
            throw new IllegalArgumentException("Email is already in use");
        }

        user.setEmail(newEmail);


        return userRepository.save(user);
    }


    public boolean isValidUserEmail(String email) {
        if (email == null) {
            return false;
        }
        return email.matches(("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"));
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }


    public void setCloudinary(Cloudinary cloudinary) {
    }
}