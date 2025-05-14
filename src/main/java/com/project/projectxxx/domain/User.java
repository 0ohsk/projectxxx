package com.project.projectxxx.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class User {
    @Id @GeneratedValue
    @Column(name = "user_id",unique = true)
    private Long id;
    private String name;
    private String password;
    private String role;
    private int number;
    private LocalDateTime createDate;
    private LocalDateTime loginDate;

    @OneToMany(mappedBy = "user")
    private List<Reservation> list = new ArrayList<>();

    @PrePersist
    public void createdDate(){
        this.createDate = LocalDateTime.now();
    }
}
