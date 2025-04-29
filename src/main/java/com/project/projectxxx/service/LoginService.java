package com.project.projectxxx.service;

import com.project.projectxxx.domain.User;
import com.project.projectxxx.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;

    public User login(String name){
        return userRepository.findByName(name)
                .orElse(null);
    }
}
