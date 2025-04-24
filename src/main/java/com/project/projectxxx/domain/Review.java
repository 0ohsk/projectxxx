package com.project.projectxxx.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Review {
    @Id @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    private String text;

    private float rate;

    private Date date;

    private int hierarchy;

    private int order;

    private int groupNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accomodation_id")
    private Accomodation accomodation;
}
