package com.example.Dteam.Controller;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class ReservationForm {

    private Date reservationDate;
    private Long reservationId;
    private Long reservationUserId;
    private Long reservationSeatNum;
}
