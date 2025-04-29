package com.project.projectxxx.controller;

import com.project.projectxxx.domain.User;
import com.project.projectxxx.dto.UserDTO;
import com.project.projectxxx.service.UserService;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService loginService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/save/new")
    public String save(){
        return "login";
    }
    @PostMapping("/users/new")
    public UserDTO saveMember(@RequestBody @Valid CreateUserRequest request){
        User user = new User();
        user.setName(request.getName());
        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        user.setRole("ROLE_ADMIN");
        Long id = loginService.join(user);
        return new UserDTO(id, user.getName(), user.getPassword());
    }
    @Data
    static class CreateUserRequest{
        private String name;
        private String password;
    }
}
