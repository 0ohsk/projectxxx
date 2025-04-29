package com.project.projectxxx.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String password;
}
