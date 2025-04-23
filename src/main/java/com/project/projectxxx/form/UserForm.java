package com.project.projectxxx.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Date;

@Data
public class UserForm {
    @NotBlank
    private Long id;
    @NotBlank
    private String password;
    @NotEmpty(message = "회원 이름은 필수입니다")
    private String name;

    private String number;
    @NotBlank
    private Date createDate;
    @NotBlank
    private Date loginDate;
}
