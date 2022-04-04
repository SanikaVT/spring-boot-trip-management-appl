package com.tripmanagement.asdc.model;

import javax.persistence.*;

@Entity
@Table(name = "Trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trip_id")
    private int trip_id;

    @Column(name = "source")
    private String source;

    @Column(name = "destination")
    private String destination;

    @Column(name = "vehicle_id")
    private int vehicle_id;

    @Column(name = "estimated_kms")
    private float estimated_kms;

    @Column(name = "kms_travelled")
    private float kms_travelled;

    @Column(name = "available_seats")
    private int available_seats;

    @Column(name = "start_time")
    private String start_time;

    @Column(name = "end_time")
    private String end_time;

    @Column(name = "seats_remaining")
    private int seats_remaining;

    @Column(name = "cost")
    private float cost;

    @Column(name = "vehicle_owner_id")
    private int vehicle_owner_id;

    public Trip(int trip_id, String source, String destination, int vehicle_id, float estimated_kms,
            float kms_travelled, int available_seats, String start_time, String end_time, int seats_remaining,
            float cost, int vehicle_owner_id) {
        this.trip_id = trip_id;
        this.source = source;
        this.destination = destination;
        this.vehicle_id = vehicle_id;
        this.estimated_kms = estimated_kms;
        this.kms_travelled = kms_travelled;
        this.available_seats = available_seats;
        this.start_time = start_time;
        this.end_time = end_time;
        this.seats_remaining = seats_remaining;
        this.cost = cost;
        this.vehicle_owner_id = vehicle_owner_id;
    }

    public Trip() {
    }

    public int getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public float getEstimated_kms() {
        return estimated_kms;
    }

    public void setEstimated_kms(float estimated_kms) {
        this.estimated_kms = estimated_kms;
    }

    public float getKms_travelled() {
        return kms_travelled;
    }

    public void setKms_travelled(float kms_travelled) {
        this.kms_travelled = kms_travelled;
    }

    public int getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(int available_seats) {
        this.available_seats = available_seats;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public int getSeats_remaining() {
        return seats_remaining;
    }

    public void setSeats_remaining(int seats_remaining) {
        this.seats_remaining = seats_remaining;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getVehicle_owner_id() {
        return vehicle_owner_id;
    }

    public void setVehicle_owner_id(int vehicle_owner_id) {
        this.vehicle_owner_id = vehicle_owner_id;
    }

    @Override
    public String toString() {
        String query1 = "Trip{" +
                "trip_id=" + trip_id;
        String query2 = ", source='" + source + '\'' +
                ", destination='" + destination + '\'';
        String query3 = ", vehicle_id=" + vehicle_id +
                ", estimated_kms=" + estimated_kms;
        String query4 = ", kms_travelled=" + kms_travelled +
                ", available_seats=" + available_seats;
        String query5 = ", start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'';
        String query6 = ", seats_remaining=" + seats_remaining +
                ", cost=" + cost;
        String query7 = ", vehicle_owner_id=" + vehicle_owner_id +
                '}';
        return query1 +
                query2 +
                query3 +
                query4 +
                query5 +
                query6 +
                query7;
    }
}
