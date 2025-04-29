package com.project.projectxxx.service;

import com.project.projectxxx.domain.User;
import com.project.projectxxx.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public Long join(User user){
        validateDuplicateUser(user);
        userRepository.save(user);
        return user.getId();
    }
    private void validateDuplicateUser(User user){
        boolean isUser = userRepository.existsByName(user.getName());
        if (isUser){
            throw new IllegalArgumentException("이미 존재하는 회원입니다");
        }
    }

}
