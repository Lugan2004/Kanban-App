package com.KanbanBackend.kanbanApp.User;

import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.ILoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000",
        allowCredentials = "true",
        allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST})
public class SignUpController {

    private final UserService userService;

    @Autowired
    public SignUpController(UserService userService){
        this.userService =userService;
    }

    @GetMapping("/sign-up")
    public ResponseEntity<String> getSignUpUrl(){
        String signUpUrl = "http://localhost:3000/SignUp";
        return ResponseEntity.ok(signUpUrl);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDto registrationDto){
        try{
            User newUser = new User();
            newUser.setUsername(registrationDto.getUsername());
            newUser.setEmail(registrationDto.getEmail());
            newUser.setPassword(registrationDto.getPassword());

            User savedUser = userService.createUser(newUser);
            return  ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Registration failed: " + e.getMessage());
        }
    }
}
// DTO for user registration data
class UserRegistrationDto {
    private String username;
    private String email;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
