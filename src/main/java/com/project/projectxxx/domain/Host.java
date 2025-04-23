package com.project.projectxxx.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Host {
    @Id @GeneratedValue
    @Column(name = "host_id")
    private Long id;
    private String password;
    private String name;
    private String phoneNumber;
}
