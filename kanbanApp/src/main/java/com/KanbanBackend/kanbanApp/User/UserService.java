package com.KanbanBackend.kanbanApp.User;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Transactional
    public User createUser(User user){

        if (userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("Email already exists");
        }
        if (userRepository.existsByUsername(user.getUsername())){
            throw new RuntimeException("Username already exists");
        }

        return userRepository.save(user);
    }

    public User getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User not found"));
    }

}
