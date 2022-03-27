package com.tripmanagement.asdc.model;

import javax.persistence.*;

@Entity
@Table(name="fuel_economy")
public class FuelEconomy {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)

    @Column(name="trip_history_id")
    private int trip_history_id;

    @Column(name="trip_id")
    private int trip_id;

    @Column(name="kms_travelled")
    private float kms_travelled;

    @Column(name="fuel_consumed")
    private float fuel_consumed;

    @Column(name="fuel_economy")
    private float fuel_economy;

    @Column(name="timestamp")
    private String timestamp;

    @Column(name = "vehicle_id")
    private int vehicle_id;

    public FuelEconomy()
    {
        
    }

    public FuelEconomy(int trip_history_id, int trip_id, float kms_travelled, float fuel_consumed, float fuel_economy, String timestamp, int vehicle_id) {
        this.trip_history_id = trip_history_id;
        this.trip_id = trip_id;
        this.kms_travelled = kms_travelled;
        this.fuel_consumed = fuel_consumed;
        this.fuel_economy = fuel_economy;
        this.timestamp = timestamp;
        this.vehicle_id = vehicle_id;
    }

    public int getTrip_history_id() {
        return trip_history_id;
    }

    public void setTrip_history_id(int trip_history_id) {
        this.trip_history_id = trip_history_id;
    }

    public int getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

    public float getKms_travelled() {
        return kms_travelled;
    }

    public void setKms_travelled(float kms_travelled) {
        this.kms_travelled = kms_travelled;
    }

    public float getFuel_consumed() {
        return fuel_consumed;
    }

    public void setFuel_consumed(float fuel_consumed) {
        this.fuel_consumed = fuel_consumed;
    }

    public float getFuel_economy() {
        return fuel_economy;
    }

    public void setFuel_economy(float fuel_economy) {
        this.fuel_economy = fuel_economy;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    @Override
    public String toString() {
        return "FuelEconomy{" +
                "trip_history_id=" + trip_history_id +
                ", trip_id=" + trip_id +
                ", kms_travelled=" + kms_travelled +
                ", fuel_consumed=" + fuel_consumed +
                ", fuel_economy=" + fuel_economy +
                ", timestamp='" + timestamp + '\'' +
                ", vehicle_id='" + vehicle_id + '\'' +
                '}';
    }
}





