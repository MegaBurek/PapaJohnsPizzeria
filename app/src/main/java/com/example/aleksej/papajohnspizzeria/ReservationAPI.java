package com.example.aleksej.papajohnspizzeria;


import java.util.ArrayList;

public class ReservationAPI {

    public static ArrayList<Reservation> getReservations(){
        ArrayList<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation(1,5,"Mark Jones", "12.05.2018", "12.30"));
        reservations.add(new Reservation(2,2,"Mark Jones", "12.05.2018", "12.30"));
        reservations.add(new Reservation(3,3,"Sam White", "12.05.2018", "12.30"));
        reservations.add(new Reservation(4,10,"Mark Jones", "12.05.2018", "12.30"));
        reservations.add(new Reservation(5,12,"Sam White", "12.05.2018", "12.30"));
        reservations.add(new Reservation(6,1,"Mark Jones", "12.05.2018", "12.30"));
        reservations.add(new Reservation(7,4,"Sam White", "12.05.2018", "12.30"));
        reservations.add(new Reservation(8,9,"Mark Jones", "12.05.2018", "12.30"));

        return reservations;
    }
}
