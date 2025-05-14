package com.project.projectxxx.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Reservation {
    @Id @GeneratedValue
    @Column(name = "reservation_id")
    private Long id;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private int capacity;

    private int price;

    private int totalPrice;

    @Enumerated(EnumType.STRING)
    private CheckStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;
}
