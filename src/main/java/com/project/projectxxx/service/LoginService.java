package com.project.projectxxx.service;

import com.project.projectxxx.domain.User;
import com.project.projectxxx.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;

    public User login(String name){
        return userRepository.findByName(name)
                .orElse(null);
    }
    @Transactional
    public void updateLoginDate(User user){
        userRepository.save(user);  //변경감지로 loginUpdate 갱신
    }
}
