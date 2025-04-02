package com.project.projectxxx.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Hello {
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
