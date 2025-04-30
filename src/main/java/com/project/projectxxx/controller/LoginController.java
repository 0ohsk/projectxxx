package com.project.projectxxx.controller;

import com.project.projectxxx.domain.User;
import com.project.projectxxx.dto.UserDTO;
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

@Tag(name = "LoginController", description = "LoginController입니다")
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Operation(summary = "로그인", description = "로그인기능입니다")
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> login(@RequestBody LoginRequest loginrequest, HttpSession session){
        User user = loginService.login(loginrequest.getName());
        if (user == null){
            ApiResponse<?> fail = new ApiResponse<>(false, "아이디가 존재하지 않습니다", null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(fail);
        }
        if(!bCryptPasswordEncoder.matches(loginrequest.getPassword(), user.getPassword())){
            ApiResponse<?> fail = new ApiResponse<>(false,"비밀번호가 틀렸습니다",null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(fail);
        }
        session.setAttribute("LOGIN_USER",user);
        UserDTO userDTO = new UserDTO(user.getId(), user.getName(), user.getPassword());
        ApiResponse<UserDTO> success = new ApiResponse<>(true,"로그인 성공",userDTO);
        return ResponseEntity.ok(success);
    }
    @Data
    static class LoginRequest {
        private String name;
        private String password;
    }
    @Data
    @AllArgsConstructor
    static class ApiResponse<T>{
        private boolean success;
        private String message;
        private T data;
    }
}
