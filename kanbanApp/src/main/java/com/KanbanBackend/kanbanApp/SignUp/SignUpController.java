package com.KanbanBackend.kanbanApp.SignUp;

import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.ILoggerFactory;
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

    @GetMapping("/sign-up")
    public ResponseEntity<String> getSignUpUrl(){
        String signUpUrl = "http://localhost:3000/SignUp";
        return ResponseEntity.ok(signUpUrl);
    }
}
