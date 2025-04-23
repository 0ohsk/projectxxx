package com.project.projectxxx.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class User {
    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    private String password;
    private String name;
    private int number;
    private Date createDate;
    private Date loginDate;

    @OneToMany(mappedBy = "user")
    private List<ReservationList> list = new ArrayList<>();
}
