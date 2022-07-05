package com.example.Dteam.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Seat {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatId;

    private Long seatNum;

    private String registerDate;

    private boolean canUse;

    private Long time;

    @OneToOne(mappedBy = "seat", cascade = CascadeType.ALL,orphanRemoval = true)
    private Reservation reservation;



}
