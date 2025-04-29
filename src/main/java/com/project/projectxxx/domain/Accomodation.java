package com.project.projectxxx.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Accomodation {
    @Id @GeneratedValue
    @Column(name = "accomodation_id")
    private Long id;

    private String name;

    private String address;

    private String location;

    private float rate;

    @OneToMany(mappedBy = "accomodation", cascade = CascadeType.ALL)
    private List<Room> rooms = new ArrayList<>();

    @OneToMany(mappedBy = "accomodation", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();
}
