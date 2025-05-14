package com.project.projectxxx.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.projectxxx.domain.User;
import com.project.projectxxx.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.PrePersist;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "UserController", description = "UserController입니다")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService loginService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/users/new")
    public String save(){
        return "new User";
    }
    @Operation(summary = "회원가입", description = "회원가입")
    @PostMapping("/users/new")
    public Result<UserDTO> saveMember(@RequestBody @Valid CreateUserRequest request){
        User user = new User();
        user.setName(request.getName());
        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        user.setRole("ROLE_ADMIN");
        Long id = loginService.join(user);
        UserDTO userDTO = new UserDTO(id, user.getName(), user.getPassword(), user.getCreateDate());
        return new Result<>(userDTO);
    }
    @Data
    static class CreateUserRequest{
        private String name;
        private String password;
    }
    @AllArgsConstructor
    @Data
    static class Result<T>{
        private T data;
    }
    @Data
    @AllArgsConstructor
    static class UserDTO{
        private Long id;
        private String name;
        private String password;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime createDate;
    }
}
