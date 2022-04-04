package com.tripmanagement.asdc.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="Vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="vehicle_id")
    private int vehicle_id;

    @Column(name="vehicleowner_id")
    private int vehicleowner_id;

    @Column(name="number_plate")
    private String number_plate;

    @Column(name="vehicle_name")
    private String vehicle_name;

    @Column(name="type")
    private String type;

    @Column(name="trips")
    private int trips;    

    @Column(name="kms_driven")
    private float kms_driven;

    @Column(name="available_seats")
    private int available_seats;

    @Column(name="fuel_consumed")
    private float fuel_consumed;

    @Column(name="fuel_economy")
    private float fuel_economy;

    @Column(name = "brand")
    private String brand;

    @Column(name = "fuel_economy_status")
    private String fuel_economy_status;

    public Vehicle() {
    }

    public Vehicle(int vehicle_id, int vehicleowner_id, String number_plate, String vehicle_name, String type, int trips, float kms_driven, int available_seats, float fuel_consumed, float fuel_economy, String brand, String fuel_economy_status) {
        this.vehicle_id = vehicle_id;
        this.vehicleowner_id = vehicleowner_id;
        this.number_plate = number_plate;
        this.vehicle_name = vehicle_name;
        this.type = type;
        this.trips = trips;
        this.kms_driven = kms_driven;
        this.available_seats = available_seats;
        this.fuel_consumed = fuel_consumed;
        this.fuel_economy = fuel_economy;
        this.brand = brand;
        this.fuel_economy_status = fuel_economy_status;
    }

    public int getTrips() {
        return trips;
    }



    public void setTrips(int trips) {
        this.trips = trips;
    }



    public float getFuel_economy() {
        return fuel_economy;
    }



    public void setFuel_economy(float fuel_economy) {
        this.fuel_economy = fuel_economy;
    }



    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public int getVehicleowner_id() {
        return vehicleowner_id;
    }

    public void setVehicleowner_id(int vehicleowner_id) {
        this.vehicleowner_id = vehicleowner_id;
    }

    public String getNumber_plate() {
        return number_plate;
    }

    public void setNumber_plate(String number_plate) {
        this.number_plate = number_plate;
    }

    public String getVehicle_name() {
        return vehicle_name;
    }

    public void setVehicle_name(String vehicle_name) {
        this.vehicle_name = vehicle_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getKms_driven() {
        return kms_driven;
    }

    public void setKms_driven(float kms_driven) {
        this.kms_driven = kms_driven;
    }

    public int getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(int available_seats) {
        this.available_seats = available_seats;
    }

    public float getFuel_consumed() {
        return fuel_consumed;
    }

    public void setFuel_consumed(float fuel_consumed) {
        this.fuel_consumed = fuel_consumed;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getFuel_economy_status() {
        return fuel_economy_status;
    }

    public void setFuel_economy_status(String fuel_economy_status) {
        this.fuel_economy_status = fuel_economy_status;
    }

    @Override
    public String toString() {
        String query1 = "Vehicle{" +
                "vehicle_id=" + vehicle_id;
        String query2 = ", vehicleowner_id=" + vehicleowner_id +
                ", number_plate='" + number_plate + '\'';
        String query3 = ", vehicle_name='" + vehicle_name + '\'' +
                ", type='" + type + '\'';
        String query4 = ", trips=" + trips +
                ", kms_driven=" + kms_driven;
        String query5 = ", available_seats=" + available_seats +
                ", fuel_consumed=" + fuel_consumed;
        String query6 = ", fuel_economy=" + fuel_economy +
                ", brand='" + brand + '\'';
        String query7 = ", fuel_economy_status='" + fuel_economy_status + '\'' +
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





