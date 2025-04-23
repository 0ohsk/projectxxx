package com.project.projectxxx;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="helloController",description="helloController test입니다")
@RestController
@RequiredArgsConstructor
public class HelloController {
    @Operation(summary = "helloController입니다" , description = "test입니다")
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
