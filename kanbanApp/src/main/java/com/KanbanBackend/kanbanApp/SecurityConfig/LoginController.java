package com.KanbanBackend.kanbanApp.SecurityConfig;

import com.KanbanBackend.kanbanApp.User.User;
import com.KanbanBackend.kanbanApp.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000",
        allowCredentials = "true",
        allowedHeaders = "*",
        methods = {RequestMethod.GET,RequestMethod.POST})
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController( UserService userService){
        this.userService = userService;
    }
    @PostMapping("/Login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
        try{
            User user = userService.validateLogin(loginDto.getEmail(), loginDto.getPassword());
            Map<String, Object> response = new HashMap<>();
            response.put("id",user.getId());
            response.put("username", user.getUsername());
            response.put("email", user.getEmail());

            return ResponseEntity.ok(response);

        } catch (Exception e){
            return  ResponseEntity.status((HttpStatus.UNAUTHORIZED))
                    .body("Login failed: " + e.getMessage());

        }
    }

}
