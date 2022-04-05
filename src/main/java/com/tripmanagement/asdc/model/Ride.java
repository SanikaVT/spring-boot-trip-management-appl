package com.tripmanagement.asdc.model;

//POJO class to store ride details
public class Ride {
    private Trip trip;
    private int vehicle_id;
    private String numberPlate;
    private float fuel_economy;
    private String vehicleOwnerName;
    private int vehicleOwnerId;
    private String phone;
    private float cost;
    private int availableSeats;

    public Ride(Trip trip, int vehicle_id, String numberPlate, float fuel_economy,
                String vehicleOwnerName, int vehicleOwnerId, String phone, float cost, int availableSeats) {
        this.trip = trip;
        this.vehicle_id = vehicle_id;
        this.numberPlate = numberPlate;
        this.fuel_economy = fuel_economy;
        this.vehicleOwnerName = vehicleOwnerName;
        this.vehicleOwnerId = vehicleOwnerId;
        this.phone = phone;
        this.cost = cost;
        this.availableSeats = availableSeats;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public int getVehicleOwnerId() {
        return vehicleOwnerId;
    }

    public void setVehicleOwnerId(int vehicleOwnerId) {
        this.vehicleOwnerId = vehicleOwnerId;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }
    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }
    public float getFuel_economy() {
        return fuel_economy;
    }
    public void setFuel_economy(float fuel_economy) {
        this.fuel_economy = fuel_economy;
    }
    public String getVehicleOwnerName() {
        return vehicleOwnerName;
    }
    public void setVehicleOwnerName(String vehicleOwnerName) {
        this.vehicleOwnerName = vehicleOwnerName;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public float getCost() {
        return cost;
    }
    public void setCost(float cost) {
        this.cost = cost;
    }

    public Ride() {
    }
}
