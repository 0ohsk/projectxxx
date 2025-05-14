package com.project.projectxxx.controller;

import com.project.projectxxx.domain.User;
import com.project.projectxxx.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Tag(name = "LoginController", description = "LoginController입니다")
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Operation(summary = "로그인", description = "로그인기능입니다")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginrequest, HttpSession session){
        User user = loginService.login(loginrequest.getName());
        if (user == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        if(!bCryptPasswordEncoder.matches(loginrequest.getPassword(), user.getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        user.setLoginDate(LocalDateTime.now());
        loginService.updateLoginDate(user);
        session.setAttribute("LOGIN_USER",user);
        UserLoginDTO userLoginDTO = new UserLoginDTO(user.getId(), user.getName(), user.getPassword(),user.getLoginDate());
        return ResponseEntity.ok(userLoginDTO);
    }
    @Data
    static class LoginRequest {
        private String name;
        private String password;
    }
    @Data
    @AllArgsConstructor
    static class UserLoginDTO{
        private Long id;
        private String name;
        private String password;
        private LocalDateTime loginDate;
    }
}
