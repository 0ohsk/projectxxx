package com.project.projectxxx.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Room {
    @Id @GeneratedValue
    @Column(name = "room_id")
    private Long id;

    private int roomNumber;

    private int capacity;

    private int price;

    private String feature;

    @Enumerated(value = EnumType.STRING)
    private UsingType usingType;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private LocalTime startTime;
    private LocalTime endTime;

    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accomodation_id")
    private Accomodation accomodation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id")
    private Host host;

    public void reservation(Reservation reservation){
        this.reservations.add(reservation);
        reservation.setRoom(this);
    }
}
