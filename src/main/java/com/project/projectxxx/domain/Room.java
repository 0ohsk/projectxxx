package com.project.projectxxx.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dType")
public class Room {
    @Id @GeneratedValue
    @Column(name = "room_id")
    private Long id;

    private int roomNumber;

    private int capacity;

    private int price;

    private String feature;

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
